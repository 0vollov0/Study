package test;

import java.io.FileReader;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.json.JSONObject;

import GmlParsing.IndoorFeatures;
import dijkstra.Dijkstra;

public class Main4 {

	public static void main(String[] args) throws JAXBException, IOException {
		// TODO Auto-generated method stub
		IndoorFeatures indoorFeatures = unmarshall();
		
		indoorFeatures.getMultiLayeredGraph().getMultiLayeredGraph().getSpaceLayers().getSpaceLayerMember().get(1).getSpaceLayer().setDijkstras();
		Dijkstra dijkstra = indoorFeatures.getMultiLayeredGraph().getMultiLayeredGraph().getSpaceLayers().getSpaceLayerMember().get(1).getSpaceLayer().getDijkstras();
		dijkstra.setStartEnd("S57", "S69");
		JSONObject jsonObject = dijkstra.getResult();
		
		System.out.println(jsonObject);
		
		System.out.println(indoorFeatures.getMultiLayeredGraph().getMultiLayeredGraph().getSpaceLayers().getSpaceLayerMember().get(0).getSpaceLayer().getStateDistance("S31", "S33"));
		
		
		//System.out.println(jsonObject.toString());
		//System.out.println(indoorFeatures.getMultiLayeredGraph().getMultiLayeredGraph().getSpaceLayers().getDijkstras());
		
		//indoorFeatures.getMultiLayeredGraph().getMultiLayeredGraph().getSpaceLayers().getDijkstras().get(1).setStartEnd("S12", "S38");
		//indoorFeatures.getMultiLayeredGraph().getMultiLayeredGraph().getSpaceLayers().getDijkstras().get(1).printResult();
	}
	
	public static IndoorFeatures unmarshall() throws JAXBException, IOException {
		JAXBContext context = JAXBContext.newInstance(IndoorFeatures.class);
		return (IndoorFeatures) context.createUnmarshaller().unmarshal(new FileReader("./sample-3D.gml"));
	}

}
