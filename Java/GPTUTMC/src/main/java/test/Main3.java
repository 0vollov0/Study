package test;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import GmlParsing.IndoorFeatures;
import GmlParsing.Pos;
import GmlParsing.MultiLayeredGraph.StateMember;
import GmlParsing.MultiLayeredGraph.TransitionMember;
import dijkstra.DijkstraShortPath;
import dijkstra.Edge;
import dijkstra.Graph;
import dijkstra.Vertex;

public class Main3 {

	public static void main(String[] args) throws JAXBException, IOException {
		// TODO Auto-generated method stub
		IndoorFeatures indoorFeatures = unmarshall();

		List<StateMember> stateMembers = indoorFeatures.getMultiLayeredGraph().getMultiLayeredGraph().getSpaceLayers()
				.getSpaceLayerMember().get(1).getSpaceLayer().getNodes().getStateMember();

		List<TransitionMember> transitionMembers = indoorFeatures.getMultiLayeredGraph().getMultiLayeredGraph()
				.getSpaceLayers().getSpaceLayerMember().get(1).getSpaceLayer().getEdges().getTransitionMember();

		List<Vertex> nodes = new ArrayList<Vertex>();
		List<Edge> edges = new ArrayList<Edge>();

		for (StateMember stateMember : stateMembers) {
			nodes.add(new Vertex(stateMember));
		}

		for (TransitionMember transitionMember : transitionMembers) {
			String firstStateId = transitionMember.getTransition().getConnects().get(0).getHref().replace("#", "");
			String secondStateId = transitionMember.getTransition().getConnects().get(1).getHref().replace("#", "");
			Vertex firstNode = null;
			Vertex secondNode = null;

			for (Vertex node : nodes) {
				if (node.getStateId().equals(firstStateId))
					firstNode = node;
				if (node.getStateId().equals(secondStateId))
					secondNode = node;
			}

			List<Pos> pos = transitionMember.getTransition().getGeometry().getLineString().getPos();

			double weight = getDistance(pos.get(0).getX(), pos.get(0).getY(), pos.get(1).getX(), pos.get(1).getY());

			edges.add(new Edge(transitionMember.getTransition().getId(), firstNode, secondNode, weight));
		}

		Graph graph = new Graph(nodes, edges);
		DijkstraShortPath dijkstra = new DijkstraShortPath(graph);

		Vertex start = null;
		Vertex end = null;

		for (Vertex node : nodes) {
			if (node.getStateId().equals("S85")) {
				start = node;
			}
			if (node.getStateId().equals("S100")) {
				end = node;
			}
		}

		dijkstra.execute(start);
		LinkedList<Vertex> path = dijkstra.getPath(end);

//		assertNotNull(path);
//	    assertTrue(path.size() > 0);

		for (Vertex vertex : path) {
			System.out.println(vertex);
		}

		dijkstra.execute(end);
		LinkedList<Vertex> path2 = dijkstra.getPath(start);

		for (Vertex vertex : path2) {
			System.out.println(vertex);
		}
	}

	public static IndoorFeatures unmarshall() throws JAXBException, IOException {
		JAXBContext context = JAXBContext.newInstance(IndoorFeatures.class);
		return (IndoorFeatures) context.createUnmarshaller().unmarshal(new FileReader("./sample-3D.gml"));
	}

//	public static void addLane(String laneId, int sourceLocNo, int destLocNo, int duration) {
//		Edge lane = new Edge(laneId, nodes.get(sourceLocNo), nodes.get(destLocNo), duration);
//		edges.add(lane);
//	}

	public static double getDistance(double x1, double y1, double x2, double y2) {
		return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
	}

}
