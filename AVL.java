public class AVL {
    
    public class Node {
        private int value;
        private Node left;
        private Node right;
        private int height;

        public Node(int value) {
            this.value = value;
            this.height = 0; // assumed leaf node started with height 0
        
        }
    }

    private Node root;

    public AVL() {
        root = null; //initiated the root to be empty
    }
    
    //height getter
    private int height(Node node) {
        if (node == null) return -1;
        return node.height;
    }

    //method to update the height
    private void updateHeight(Node node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    // public insert method for easier user use
    public void insert(int value) {
        root = insert(root, value);
    }

    // actual insert function which is recursive
    private Node insert(Node node, int value) {
        if (node == null) {
            return new Node(value);
        }

        if (value < node.value) {
            node.left = insert(node.left, value);
        } else if (value > node.value) {
            node.right = insert(node.right, value);
        } else {
            // assuming no dupe values
            return node;
        }

        // Update height of this node
        updateHeight(node);

        // self balance the tree
        return balance(node); //NOTE: balance() not implemented yet
    }

}
