package somes.selfImproving.hr.preorderTraversal;

/**
 * Created by anme on 17.04.17.
 */

public class Node {
    int data;
    Node left;
    Node right;

    private Node(int data, Node left, Node right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public static <T> ExampleBuilder<T> builder() {
        return new ExampleBuilder<T>();
    }

    public static class ExampleBuilder<T> {
        int data;
        Node left;
        Node right;

        private ExampleBuilder() {}

        public ExampleBuilder data(int data) {
            this.data = data;
            return this;
        }

        public ExampleBuilder left(Node left) {
            this.left = left;
            return this;
        }

        public ExampleBuilder right(Node right) {
            this.right = right;
            return this;
        }

        public Node build() {
            return new Node(data, left, right);
        }
    }
}

