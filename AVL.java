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

}
