import java.util.Vector;

class TreeNode {
	String label;
	TreeNode parent;
	Vector<TreeNode> children;

	TreeNode(String label) {
		this.label = label;
		children = new Vector<>();
	}

	TreeNode addChild(String child) {
		TreeNode childNode = new TreeNode(child);
		childNode.parent = this;
		this.children.add(childNode);
		return childNode;
	}

	void printLabels(TreeNode root) {
		System.out.println(root.label);

		for (int i = 0; i < root.children.size(); i++) {
			printLabels(root.children.get(i));
		}
	}

	int height(TreeNode root) {
		int h = 0;

		for (int i = 0; i < root.children.size(); i++) {
			h = Math.max(h, 1 + height(root.children.get(i)));
		}

		return h;
	}
}

public class Main {

	public static void main(String[] args) {
		TreeNode root = new TreeNode("root");
		
		TreeNode node1 = root.addChild("node1");
		TreeNode node2 = root.addChild("node2");
		TreeNode node3 = root.addChild("node3");
		
		TreeNode node4 = node1.addChild("node4");
		TreeNode node5 = node1.addChild("node5");
		
		TreeNode node6 = node3.addChild("node6");
		
		root.printLabels(root);
		System.out.println(root.height(root));

	}

}
