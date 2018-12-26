package somes.selfImproving.hr.preorderTraversal;

/**
 * Created by anme on 17.04.17.
 */
public class Some {
    public static void main(String[] args) {
        Node n1 = Node.builder().data(1).build();
        Node n4 = Node.builder().data(4).build();
        Node n5 = Node.builder().data(5).left(n1).right(n4).build();

        Node n6 = Node.builder().data(6).build();
        Node n2 = Node.builder().data(2).left(n6).build();

        Node root = Node.builder().data(3).left(n5).right(n2).build();

        printNode(root);

        System.out.println("---");
        System.out.println(height(root));

    }

    public static void printNode(Node node) {
        if(node.left != null) printNode(node.left);
        if(node.right != null) printNode(node.right);
        System.out.print(node.data + " ");
    }

    public static int height(Node node) {
        if(node.left == null && node.right == null) return 0;
        int leftHeight = node.left != null ? height(node.left) : 0;
        int rightHeight = node.right != null ? height(node.right) : 0;
        return 1 + ((leftHeight>rightHeight) ? leftHeight : rightHeight);
    }
}
