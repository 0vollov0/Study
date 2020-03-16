package test;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;

public class Main2 {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		WKTReader wktRdr = new WKTReader();
		String wktA = "POLYGON((40 100, 40 20, 120 20, 120 100, 40 100))";
		String wktB = "LINESTRING(20 80, 80 60, 100 140)";
		String wktC = "POLYGON((40 100, 40 20, 120 20, 120 100, 40 100))";
		Geometry A = wktRdr.read(wktA);
		Geometry B = wktRdr.read(wktB);
		Geometry C = wktRdr.read(wktC);
		Geometry D = A.intersection(B);
		Geometry E = A.intersection(C);
		
		System.out.println(A.covers(B));
	}

}
