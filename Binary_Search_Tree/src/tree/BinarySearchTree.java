package tree;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree {

	Node root;
	private List<Node> list = new ArrayList<Node>();

	public List<Node> getArrayList() {
		return list;
	}

	public BinarySearchTree() {
		root = null;
	}

//+------------ INSERT NODE ------------+
	public void insertRootNode(int key, String name, Double price, int quantity) {
		root = insertNode(root, key, name, price, quantity);
	}

	private Node insertNode(Node root, int key, String name, Double price, int quantity) {

		// INSERT
		if (root == null) {
			System.out.println("INSERTED - key: " + key + "\n");
			root = new Node(key, name, price, quantity);
			return root;
		}
		// LEFT
		if (key < root.key) {
			System.out.println("Go LEFT  - root.key: " + root.key + ", key: " + key);
			root.left = insertNode(root.left, key, name, price, quantity);
		}
		// RIGHT
		if (key > root.key) {
			System.out.println("Go RIGHT - root.key: " + root.key + ", key: " + key);
			root.right = insertNode(root.right, key, name, price, quantity);
		}
		return root;
	}

//+------------ DELETE NODE ------------+
	public void deleteRootNode(int key) {
		root = deleteNode(root, key);
	}

	private Node deleteNode(Node root, int key) {
		if (root == null) {
			return root;
		}
		if (key < root.key) {
			root.left = deleteNode(root.left, key);
		} else if (key > root.key) {
			root.right = deleteNode(root.right, key);
		} else {
			// If the node is with only one child or no child
			if (root.left == null)
				return root.right;
			else if (root.right == null)
				return root.left;
			// If the node has two children
			root.key = minNode(root.right);
			root.right = deleteNode(root.right, root.key);
		}
		return root;
	}

//+------------ Minimum Value For Node ------------+
	private int minNode(Node root) {
		int minValue = root.key;
		while (root.left != null) {
			minValue = root.left.key;
			root = root.left;
		}
		return minValue;
	}

//+------------ UPDATE NODE ------------+
	public void updateRootNode(int key, String name, Double price, int quantity) {
		if (root != null) {
			updateNode(root, key, name, price, quantity);
		} else {
			System.out.println("\nError: root Node doesnt exist");
		}
	}

	private void updateNode(Node root, int key, String name, Double price, int quantity) {
		// FOUND UPDATE
		if (key == root.key) {
			root.setName(name);
			root.setPrice(price);
			root.setQuantity(quantity);
		}
		// LEFT
		if (key < root.key) {
			if (root.left != null) {
				updateNode(root.left, key, name, price, quantity);
			} else {
				System.out.println("Last Key: " + root.key + " Value: " + key + " NOT FOUND");
			}
		}
		// RIGHT
		if (key > root.key) {
			if (root.right != null) {
				updateNode(root.right, key, name, price, quantity);
			} else {
				System.out.println("Last Key: " + root.key + " Value: " + key + " NOT FOUND");
			}
		}
	}

//+------------ SET INORDER NODE------------+
	public void setInorder() {
		list.clear();
		setInorderArrayList(root, list);
	}

	private List<Node> setInorderArrayList(Node root, List<Node> list) {
		if (root != null) {
			setInorderArrayList(root.left, list);
			list.add(root);
			setInorderArrayList(root.right, list);
		}
		return list;
	}

//+------------ SEARCH NODE ARRAYLIST------------+
	public void searchRootNode(int value) {
		if (root != null) {
			try {
				list.clear();
				searchNode(root, value, list);
			} catch (NullPointerException e) {
				System.out.println("Object is empty");
			}
		} else {
			System.out.println("\nError: root Node doesnt exist");
		}
	}

	private List<Node> searchNode(Node root, int value, List<Node> list) {
		// FOUND
		if (value == root.getKey()) {
			list.add(root);
			return list;
		}
		// LEFT
		if (value < root.getKey()) {
			if (root.left != null) {
				return searchNode(root.left, value, list);
			}
		}
		// RIGHT
		if (value > root.getKey()) {
			if (root.right != null) {
				return searchNode(root.right, value, list);
			}
		}
		System.out.println("Last Key: " + root.getKey() + " Value: " + value + " NOT FOUND");
		return list;
	}

	public class Node {
		private int key;
		private String name;
		private Double price;
		private int quantity;

		private Node left;
		private Node right;

		public Node(int key, String name, Double price, int quantity) {
			this.key = key;
			this.name = name;
			this.price = price;
			this.quantity = quantity;
			this.left = null;
			this.right = null;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getKey() {
			return key;
		}

		public void setKey(int key) {
			this.key = key;
		}

		public Double getPrice() {
			return price;
		}

		public void setPrice(Double price) {
			this.price = price;
		}

		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
	}

}
