package week04;

public class BinaryTree {

	Node root;

	public void addNode(String name, int key) {

		Node newNode = new Node(name, key);

		if (root == null) {
			root = newNode;
		} else if (root != null) {
			Node focusNode = root;
			Node parent;
			while (true) {

				parent = focusNode;

				if (key < focusNode.key) {
					focusNode = focusNode.leftChild;
					if (focusNode == null) {
						parent.leftChild = newNode;
						return;
					}
				} else {
					focusNode = focusNode.rightChild;
					if (focusNode == null) {
						parent.rightChild = newNode;
						return;
					}

				}
			}

		}

	}
	
	
	public Node findNode(int key){
		
		Node focusNode = root;
		
		while (focusNode.key != key) {
			
			if (key < focusNode.key) {
				focusNode = focusNode.leftChild;
			} else {
				focusNode = focusNode.rightChild;
			}
			
			
			if (focusNode == null) {
				return null;
			}
		}
		
		
		
		return focusNode;
	}
	
	public void inOrderTraverseTree(Node focusNode) {
		
		if (focusNode != null) {
			
			inOrderTraverseTree(focusNode.leftChild);
			System.out.println(focusNode);
			inOrderTraverseTree(focusNode.rightChild);
			
			
			
		}
		
	}
	
	
	public boolean remove(int key) {
		
		Node focusNode = root;
		Node parent = root;
		
		boolean isAtLeftChild = true;
		
		//find it
		
		while (focusNode.key != key) {
			
			parent = focusNode;
			
			if (key < focusNode.key) {
				isAtLeftChild = true;
				focusNode = focusNode.leftChild;
			} else {
				isAtLeftChild = false;
				focusNode = focusNode.rightChild;
			}
			
			if (focusNode == null) {
				return false;
			}
		}
		
		//if Node doesn't have children - delete it
		
		if (focusNode.leftChild == null && focusNode.rightChild == null) {
			
			if (focusNode == root) {
				root = null;
			} else if (isAtLeftChild) {
				parent.leftChild = null;
			} else if (isAtLeftChild = false) {
				parent.rightChild = null;
			}
		
		}
		
		//If no right child
		
		else if (focusNode.rightChild == null) {
			
			if (focusNode == root) {
				root = focusNode.leftChild;
			} else if (isAtLeftChild) {
				parent.leftChild = focusNode.leftChild;
			} else {
				parent.rightChild = focusNode.leftChild;
			}
			
		}
		
		//If no left child
		
		else if (focusNode.leftChild == null) {
			
			if (focusNode == root) {
				root = focusNode.rightChild;
			} else if (isAtLeftChild) {
				parent.leftChild = focusNode.rightChild;
			} else {
				parent.rightChild = focusNode.rightChild;
			}
		}
		
		//Two children
		
		else {
			
			Node replacement = getReplacementNode(focusNode);
			
			if (focusNode == root) {
				root = replacement;
			}
			
			else if (isAtLeftChild) {
				parent.leftChild = replacement;
			}
			
			else 
				parent.rightChild = replacement;
			
			replacement.leftChild = focusNode.leftChild;
			
		}
		
		
		return true;
	}
	
	public Node getReplacementNode(Node replacedNode) {
		
		Node replacementParent = replacedNode;
		Node replacement = replacedNode;
		Node focusNode = replacedNode.rightChild;
		
		while (focusNode != null) {
			
			replacementParent = replacement;
			replacement = focusNode;
			focusNode = focusNode.leftChild;
			
		}
		
		if (replacement != replacedNode.rightChild) {
			replacementParent.leftChild = replacement.rightChild;
			replacement.rightChild = replacedNode.rightChild;
		}
		
		
		
		return replacement;
	}
	
	
	public static void main(String[] args) {
		
		BinaryTree theTree = new BinaryTree();
		theTree.addNode("Vency", 1);
		theTree.addNode("Marchela", 2);
		theTree.addNode("Plamen", 3);
		
		//System.out.println(theTree.findNode(3));
		
		theTree.inOrderTraverseTree(theTree.root);
		
		theTree.remove(1);
		System.out.println("-----The tree after removal------");
		
		theTree.inOrderTraverseTree(theTree.root);
		
		
	}
	
	
	
	
	
	
}

class Node {
	int key;
	String name;
	Node leftChild;
	Node rightChild;

	public Node(String name, int key) {
		this.name = name;
		this.key = key;
	}

	public String toString() {
		return name + " has the key " + key;
	}

}
