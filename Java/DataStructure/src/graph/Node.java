package graph;

import java.util.LinkedList;

public class Node {
	int data;
	LinkedList<Node> adjacent;
	boolean  marked;
	
	public Node(int data) {
		this.data = data;
		this.marked = false;
		adjacent = new LinkedList<Node>();
	}
}
