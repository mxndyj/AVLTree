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
        private void inorder(){
        inorder2(root);
    }
    //prints inorder traversal of tree
    private void inorder2(Node root) {
        if (root != null) {
            inorder2(root.left);
            System.out.print(root.value + " ");
            inorder2(root.right);
        }
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
        private void delete(int value){
        root = deleteHelper(root, value);
    }

    private Node deleteHelper(Node root, int value){
        if (root == null){
            return root;
        }
        if(value < root.value){
            root.left = deleteHelper(root.left, value);
        }
        else if(value > root.value){
            root.right = deleteHelper(root.right, value);
        }
        else{
            if(root.left == null){
                return root.right;
            }
            else if(root.right == null){
                return root.left;
            }
            root.value = min(root.right);
            root.right = deleteHelper(root.right, root.value);
        }
        return root;
    }

    private int min(Node root){
        int min = root.value;
        while(root.left != null){
            min = root.left.value;
            root = root.left;
        }
        return min;
    }

    public static void main(String[] args){
        AVL tree = new AVL();
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
