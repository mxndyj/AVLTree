import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class AVLTest {
    private AVL avlTree;

    @Before
    public void setUp() {
        avlTree = new AVL();
    }

    @Test
    public void testInsertAndVerifyBalance() {
        avlTree.insert(30);
        assertEquals( 0, avlTree.height());
        avlTree.insert(20);
        assertEquals( 1, avlTree.height());
        avlTree.insert(40);
        assertEquals( 1, avlTree.height());
        avlTree.insert(10);
        assertEquals (2, avlTree.height());
        avlTree.insert(25);
        assertEquals( 2, avlTree.height());
        avlTree.insert(35); 
        avlTree.insert(50); 
        assertEquals( 2, avlTree.height());
    }

    @Test
    public void testDeleteAndVerifyBalance() {
        avlTree.insert(30);
        avlTree.insert(20);
        avlTree.insert(40);
        avlTree.insert(10);
        avlTree.insert(25);
        avlTree.insert(35);
        avlTree.insert(50);

        avlTree.delete(10); 
        assertEquals( 2, avlTree.height());

        avlTree.delete(50);
        assertEquals( 2, avlTree.height());

        avlTree.delete(20); 
        assertEquals( 2, avlTree.height());
    }

    @Test
    public void testInsertionWithRotations() {
        // Right rotation test
        avlTree.insert(3);
        avlTree.insert(2);
        avlTree.insert(1);
        assertEquals(1, avlTree.height());

        // Left rotation test
        avlTree = new AVL(); // Reset tree
        avlTree.insert(1);
        avlTree.insert(2);
        avlTree.insert(3);
        assertEquals( 1, avlTree.height());

        // Right-Left rotation test
        avlTree = new AVL(); // Reset tree
        avlTree.insert(1);
        avlTree.insert(3);
        avlTree.insert(2);
        assertEquals( 1, avlTree.height());

        // Left-Right rotation test
        avlTree = new AVL(); // Reset tree
        avlTree.insert(3);
        avlTree.insert(1);
        avlTree.insert(2);
        assertEquals( 1, avlTree.height());
    }

    @Test
    public void testComplexBalancing() {
        int[] values = {50, 30, 70, 20, 40, 60, 80, 10, 25, 35, 45, 55, 65, 75, 85};
        for (int value : values) {
            avlTree.insert(value);
        }
        assertEquals( 3, avlTree.height());

        int[] deletions = {85, 25, 55};
        for (int delete : deletions) {
            avlTree.delete(delete);
        }
        assertEquals( 3, avlTree.height());

        int[] moreInserts = {5, 15, 22, 27, 37, 47};
        for (int insert : moreInserts) {
            avlTree.insert(insert);
        }
        assertEquals( 4, avlTree.height());
    }

    @Test
    public void testComprehensiveRotations() {
        int[] complexSequence = {40, 20, 60, 10, 30, 50, 70, 5, 15, 25, 35, 45, 55, 65, 75, 3, 8, 13, 18, 23, 28, 33, 38, 43, 48, 53, 58, 63, 68, 73, 78};
        for (int num : complexSequence) {
            avlTree.insert(num);
        }
        assertTrue( avlTree.height() <= Math.ceil(Math.log(complexSequence.length)/Math.log(2)));
    }

}