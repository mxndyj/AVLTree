
# AVL Tree Implementation in Java
This Java AVL Tree implementation provides a demonstration of an self-balancing binary search tree. The AVL Tree maintains balance using rotation operations to ensure that operations such as insertions, and deletions  remain efficient even as the tree size increases.

#  Features
**Main Class**: Contains methods to manage the AVL tree operations like insert, delete, and display inorder traversal for user use 

**Node Class**: Represents the individual elements of the tree, holding values and pointers to left and right child nodes, along with the height of the node.

**Insert**: Adds a new node while maintaining the balanced nature of the AVL tree.

**Delete**: Removes a node and rebalances the tree if necessary.

**Inorder**: Displays the elements of the tree in a sorted order.

**Height**: Calculates the height of any node to help maintain tree balance during operations.

**Search**: Searches for item

**Balancing Rotations**: Includes right and left rotations, along with their double counterparts (right-left and left-right), to ensure the tree remains balanced.

**Testcases**: JUnit test cases to ensure correctness and efficiency.

# How to run program

First, save and compile AVL.java.

When prompted, you can perform the following operations:
Insert: Type i, press enter, and then the numbers you want to insert separated by spaces. Press enter.
Delete: Type d, press enter, then type the number you wish to delete and press enter.
Search: Type s, press enter, then type number you want to search for.
Print Inorder Traversal: Type p and press enter to see the current inorder traversal of the tree.
Quit: Type q and press enter to exit the program.

# Example Usage

Enter an operation: i

Enter numbers to insert, separated by spaces: 10 20 5 15

Enter an operation: p

Inorder traversal of the AVL tree:

5 10 15 20

Enter an operation: d

Enter number to delete: 10

Enter an operation: p

Inorder traversal of the AVL tree:

5 15 20

Enter an operation: q

Exiting program.
