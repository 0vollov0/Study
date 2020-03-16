package test;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import GmlParsing.IndoorFeatures;
import GmlParsing.Pos;

public class Main {

	public static void main(String[] args) throws JAXBException, IOException {
		IndoorFeatures indoorFeatures = unmarshall();
		
		List<RequiredConvertInfo> requiredConvertInfos = new ArrayList<RequiredConvertInfo>();
		setRequiredConvertInfo(indoorFeatures, requiredConvertInfos);

		for (RequiredConvertInfo requiredConvertInfo : requiredConvertInfos) {
			System.out.println(requiredConvertInfo);
		}

	}

	public static IndoorFeatures unmarshall() throws JAXBException, IOException {
		JAXBContext context = JAXBContext.newInstance(IndoorFeatures.class);
		return (IndoorFeatures) context.createUnmarshaller().unmarshal(new FileReader("./indoorFeatures.gml"));
	}

	public static void setRequiredConvertInfo(IndoorFeatures indoorFeatures,
			List<RequiredConvertInfo> requiredConvertInfos) {
		List<Pos> posList = indoorFeatures.getPrimalSpaceFeatures().getPrimalSpaceFeatures().getCellSpaceMember().get(0)
				.getCellSpace().getCellSpaceGeometry().getGeometry3d().getSolid().getExterior().getShell()
				.getSurfaceMembers().get(0).getPolygon().getExterior().getPos();

		Pos bottomLeftPos = getBottomLeftPos(indoorFeatures);
		Pos topRightPos = getTopRightPos(indoorFeatures);
		
		//System.out.println(bottomLeftPos.getVector());
		//System.out.println(topRightPos.getVector());

		//int imageWidth = 364;
		int imageHeight = 637;

		double bottomLeftLongitude = 129.082466;
		double bottomLeftLatitude = 35.235380;
		
		double bottomLeftPixelX = 10;
		double bottomLeftPixelY = 85;

		double topRightLongitude = 129.082864;
		double topRightLatitude = 35.235448;
		//double topRightPixelX = 309;
		//double topRightPixelY = 23;

		double ratioLongitude = 0;
		double ratioLatitude = 0;
		double ratioPixelX = bottomLeftPixelX / bottomLeftPos.getX();
		double ratioPixelY = (imageHeight - bottomLeftPixelY) / bottomLeftPos.getY();
		

		if (bottomLeftLongitude < topRightLongitude) {
			ratioLongitude = (topRightLongitude - bottomLeftLongitude) / (topRightPos.getX() - bottomLeftPos.getX());
		} else {
			ratioLongitude = (bottomLeftLongitude - topRightLongitude) / (bottomLeftPos.getX() - topRightPos.getX());
		}

		if (bottomLeftLatitude < topRightLatitude) {
			ratioLatitude = (topRightLatitude - bottomLeftLatitude) / (topRightPos.getY() - bottomLeftPos.getY());
		} else {
			ratioLatitude = (bottomLeftLatitude - topRightLatitude) / (bottomLeftPos.getY() - topRightPos.getY());
		}
		System.out.println(ratioLatitude);
		ratioLatitude = 0.00000235357;
		double baseLongitude = bottomLeftLongitude < topRightLongitude ? bottomLeftLongitude : topRightLongitude;
		double baseLatitude = bottomLeftLatitude < topRightLatitude ? bottomLeftLatitude : topRightLatitude;
		
		//System.out.println(ratioLongitude);
		//System.out.println(baseLongitude);
		
		System.out.println(ratioLatitude);
		System.out.println(baseLatitude);

		double basePosX = bottomLeftPos.getX() < topRightPos.getX() ? bottomLeftPos.getX() : topRightPos.getX();
		double basePosY = bottomLeftPos.getY() < topRightPos.getY() ? bottomLeftPos.getY() : topRightPos.getY();
		
		System.out.println(basePosY);
		
		for (Pos pos : posList) {
			double longitude = baseLongitude + ratioLongitude * (pos.getX() - basePosX);
			double latitude = baseLatitude + ratioLatitude * (pos.getY() - basePosY);
			double pixelX = ratioPixelX * pos.getX();
			double pixelY = imageHeight - (ratioPixelY * pos.getY());
			
			//System.out.println(pos.getY() +"::" + latitude);
			
			requiredConvertInfos.add(new RequiredConvertInfo(longitude, latitude, pixelX, pixelY));
		}

	}

	public static Pos getBottomLeftPos(IndoorFeatures indoorFeatures) {
		List<Pos> pos = indoorFeatures.getPrimalSpaceFeatures().getPrimalSpaceFeatures().getCellSpaceMember().get(0)
				.getCellSpace().getCellSpaceGeometry().getGeometry3d().getSolid().getExterior().getShell()
				.getSurfaceMembers().get(0).getPolygon().getExterior().getPos();
		Double minPosX = null;
		Double minPosY = null;

		for (Pos element : pos) {
			if (minPosX == null)
				minPosX = element.getX();
			if (minPosX > element.getX())
				minPosX = element.getX();
		}

		int index = 0;
		int answer = 0;
		for (Pos element : pos) {
			if (minPosX == element.getX()) {
				if (minPosY == null) {
					minPosY = element.getY();
					answer = index;
					continue;
				}
				if (minPosY > element.getY()) {
					minPosY = element.getY();
					answer = index;
				}
			}
			index++;
		}

		return pos.get(answer);
	}

	public static Pos getTopRightPos(IndoorFeatures indoorFeatures) {
		List<Pos> pos = indoorFeatures.getPrimalSpaceFeatures().getPrimalSpaceFeatures().getCellSpaceMember().get(0)
				.getCellSpace().getCellSpaceGeometry().getGeometry3d().getSolid().getExterior().getShell()
				.getSurfaceMembers().get(0).getPolygon().getExterior().getPos();
		Double maxPosX = null;
		Double PosY = null;

		for (Pos element : pos) {
			if (maxPosX == null)
				maxPosX = element.getX();
			if (maxPosX < element.getX())
				maxPosX = element.getX();
		}

		int index = 0;
		int answer = 0;
		for (Pos element : pos) {
			if (maxPosX == element.getX()) {
				if (PosY == null) {
					PosY = element.getY();
					answer = index;
					continue;
				}
				if (PosY < element.getY()) {
					PosY = element.getY();
					answer = index;
				}
			}
			index++;
		}

		return pos.get(answer);
	}

}
