package tree;

public class BinaryTree {
	private Node root;

	public BinaryTree() {
		root = null;
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	public Node find(int data) {
		Node currentNode = root;

		while (currentNode != null) {
			if (data == currentNode.getData()) {
				return currentNode;
			} else if (data < currentNode.getData()) {
				currentNode = currentNode.getLeft();
			} else {
				currentNode = currentNode.getRight();
			}
		}

		return null;
	}

	public void insert(int data) {
		Node newNode = new Node(data);

		if (root == null) {
			root = newNode;
			return;
		}

		Node currentNode = root;

		while (true) {
			if (data < currentNode.getData()) {
				if (currentNode.getLeft() == null) {
					currentNode.setLeft(newNode);
					break;
				} else {
					currentNode = currentNode.getLeft();
				}
			} else {
				if (currentNode.getRight() == null) {
					currentNode.setRight(newNode);
					break;
				} else {
					currentNode = currentNode.getRight();
				}
			}
		}
	}

	public boolean remove(int data) {
		Node parentNode = root;
		Node currentNode = root;
		boolean isLeftChild = false;

		while (currentNode.getData() != data) {
			parentNode = currentNode;
			if (currentNode.getData() > data) {
				isLeftChild = true;
				currentNode = currentNode.getLeft();
			} else {
				isLeftChild = false;
				currentNode = currentNode.getRight();
			}
			if (currentNode == null) {
				return false;
			}
		}

		if (currentNode.getLeft() == null && currentNode.getRight() == null) {
			if (currentNode == root) {
				root = null;
			}
			if (isLeftChild) {
				parentNode.setLeft(null);
			}else {
				parentNode.setRight(null);
			}
		} else if (currentNode.getRight() == null) {
			if (currentNode == root) {
				root = currentNode.getLeft();
			}else if (isLeftChild) {
				parentNode.setLeft(currentNode.getLeft());
			}else {
				parentNode.setRight(currentNode.getLeft());
			}
		} else if (currentNode.getLeft() == null) {
			if (currentNode == root) {
				root = currentNode.getRight();
			}else if (isLeftChild) {
				parentNode.setLeft(currentNode.getRight());
			}else {
				parentNode.setRight(currentNode.getRight());
			}
		} else if (currentNode.getLeft() != null && currentNode.getRight() != null) {
			Node minNode = getMinRightNode(currentNode);
			if (currentNode == root) {
				root = minNode;
			}else if (isLeftChild) {
				parentNode.setLeft(minNode);
			}else {
				parentNode.setRight(minNode);
			}
			minNode.setLeft(currentNode.getLeft());
		}
		return false;
	}
	
	public Node getMinRightNode(Node deleleNode){
        Node minNode =null;
        Node minNodeParent =null;
        Node currentNode = deleleNode.getRight();
        while(currentNode!=null){
        	minNodeParent = minNode;
        	minNode = currentNode;
        	currentNode = currentNode.getLeft();
        }
        if(minNode!=deleleNode.getRight()){
        	minNodeParent.setLeft(minNode.getRight());
        	minNode.setRight(deleleNode.getRight());
        }
        return minNode;
    }

}
