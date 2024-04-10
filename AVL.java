
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
        root = null; // initiated the root to be empty
    }

    public void inorder() {
        inorder2(root);
    }

    // prints inorder traversal of tree
    private void inorder2(Node root) {
        if (root != null) {
            inorder2(root.left);
            System.out.print(root.value + " ");
            inorder2(root.right);
        }
    }

    // height getter
    private int height(Node node) {
        if (node == null)
            return -1;
        return node.height;
    }

    public int height() {
        return height(root);
    }

    // method to update the height
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
        return balance(node); 
    }

    void delete(int value) {
        root = deleteHelper(root, value);
    }

    private Node deleteHelper(Node root, int value) {
        if (root == null) {
            return root;
        }
        if (value < root.value) {
            root.left = deleteHelper(root.left, value);
        } else if (value > root.value) {
            root.right = deleteHelper(root.right, value);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            root.value = min(root.right);
            root.right = deleteHelper(root.right, root.value);
        }

        updateHeight(root);
        return balance(root);
    }

    private int min(Node root) {
        int min = root.value;
        while (root.left != null) {
            min = root.left.value;
            root = root.left;
        }
        return min;
    }

    private int getBalance(Node node) {
        if (node == null)
            return 0;
        return height(node.left) - height(node.right);
    }

    private Node balance(Node node) {
        int balanceFactor = getBalance(node);

        // 1:Left Heavy
        if (balanceFactor > 1) {
            // 1a: Single right for LL
            if (getBalance(node.left) >= 0) {
                return rightRotate(node);
            }
            // 1b: Left-Right
            else {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
        }
        // 2: Right Heavy
        else if (balanceFactor < -1) {
            // 2a: for a left rotate for Right-Right
            if (getBalance(node.right) <= 0) {
                return leftRotate(node);
            }
            // 2b: Right-Left
            else {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
        }
        return node;
    }

    // Right rotation method
    private Node rightRotate(Node node) {
        Node newParent = node.left;
        Node newRightChildOfParent = newParent.right;

        // Perform rotation
        newParent.right = node;
        node.left = newRightChildOfParent;

        // Update heights
        updateHeight(node);
        updateHeight(newParent);

        // Return new root
        return newParent;
    }

    // Left rotation method
    private Node leftRotate(Node node) {
        Node newParent = node.right;
        Node newLeftChildOfParent = newParent.left;

        // Perform rotation
        newParent.left = node;
        node.right = newLeftChildOfParent;

        // Update heights
        updateHeight(node);
        updateHeight(newParent);

        // Return new root
        return newParent;
    }

    // need to implement: search and delete handling

    public static void main(String[] args) {
        AVLTREE tree = new AVLTREE();
        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);
        tree.inorder();
        System.out.println();
        tree.delete(60);
        tree.inorder();
    }
}
