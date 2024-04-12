import java.util.Scanner;

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

    public static void main(String[] args) {
        AVL tree = new AVL();
        Scanner scanner = new Scanner(System.in);

        System.out.println("AVL Tree operations:\n" +
                "'i' followed by numbers separated by spaces to insert them.\n" +
                "'d' followed by a number to delete that number.\n" +
                "'p' to print inorder traversal.\n" +
                "'q' to quit.");

        while (true) {
            System.out.print("\nEnter an operation: ");
            String operation = scanner.next();

            switch (operation) {
                case "i": // Insert operation
                    System.out.print("Enter numbers to insert, separated by spaces: ");
                    scanner.nextLine(); 
                    String numbers = scanner.nextLine();
                    String[] numsToInsert = numbers.split("\\s+");
                    for (String numStr : numsToInsert) {
                        try {
                            int valueToInsert = Integer.parseInt(numStr);
                            tree.insert(valueToInsert);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid number: " + numStr);
                        }
                    }
                    break;
                case "d": // Delete operation
                    System.out.print("Enter number to delete: ");
                    if (scanner.hasNextInt()) {
                        int valueToDelete = scanner.nextInt();
                        tree.delete(valueToDelete);
                    } else {
                        System.out.println("Please enter a valid number after 'd'.");
                        scanner.nextLine(); 
                    }
                    break;
                case "p": // Print inorder operation
                    System.out.println("Inorder traversal of the AVL tree:");
                    tree.inorder();
                    System.out.println(); 
                    break;
                case "q": // Quit operation
                    System.out.println("Exiting program.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid operation. Please enter 'i', 'd', 'p', or 'q'.");
                    scanner.nextLine(); 
                    break;
            }
        }
    }
}