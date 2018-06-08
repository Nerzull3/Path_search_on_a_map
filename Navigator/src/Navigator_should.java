import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;


public class Navigator_should {
    private Main main = new Main();

    @Test
    public void testOnFoundRoute(){
        char[][] map = new char[][]{
                {'#','#','#','#','#','#','#'},
                {'#','.','.','.','@','.','#'},
                {'#','.','#','#','#','#','#'},
                {'#','.','.','.','.','.','#'},
                {'#','#','#','#','#','.','#'},
                {'#','.','X','.','.','.','#'},
                {'#','#','#','#','#','#','#'}
        };

        char[][] expected = new char[][]{
                {'#','#','#','#','#','#','#'},
                {'#','+','+','+','@','.','#'},
                {'#','+','#','#','#','#','#'},
                {'#','+','+','+','+','+','#'},
                {'#','#','#','#','#','+','#'},
                {'#','.','X','+','+','+','#'},
                {'#','#','#','#','#','#','#'}
        };

        char[][] actual = main.searchRoute(map);
        for (int i = 0; i < expected.length; i++)
            assertTrue(Arrays.equals(expected[i], actual[i]));
    }

    @Test
    public void testOnChoiceRoute(){
        char[][] map = new char[][]{
                {'#','#','#','#','#','#','#'},
                {'#','.','.','.','.','@','#'},
                {'#','#','.','#','#','#','#'},
                {'#','.','.','.','.','.','#'},
                {'#','.','.','X','.','.','#'},
                {'#','.','.','.','.','.','#'},
                {'#','#','#','#','#','#','#'}
        };

        char[][] expected1 = new char[][]{
                {'#','#','#','#','#','#','#'},
                {'#','.','+','+','+','@','#'},
                {'#','#','+','#','#','#','#'},
                {'#','.','+','.','.','.','#'},
                {'#','.','+','X','.','.','#'},
                {'#','.','.','.','.','.','#'},
                {'#','#','#','#','#','#','#'}
        };

        char[][] expected2 = new char[][]{
                {'#','#','#','#','#','#','#'},
                {'#','.','+','+','+','@','#'},
                {'#','#','+','#','#','#','#'},
                {'#','.','+','+','.','.','#'},
                {'#','.','.','X','.','.','#'},
                {'#','.','.','.','.','.','#'},
                {'#','#','#','#','#','#','#'}
        };

        char[][] actual = main.searchRoute(map);
        for (int i = 0; i < expected1.length; i++)
            assertTrue(Arrays.equals(expected1[i], actual[i]) ||
                           Arrays.equals(expected2[i], actual[i]));
    }

    @Test
    public void testOnFoundShortestRoute(){
        char[][] map = new char[][]{
                {'#','#','#','#','#','#','#','#'},
                {'#','.','.','.','@','.','.','#'},
                {'#','.','#','#','#','#','.','#'},
                {'#','.','.','.','.','.','.','#'},
                {'#','#','#','#','#','.','#','#'},
                {'#','.','X','.','.','.','.','#'},
                {'#','#','#','#','#','#','#','#'}
        };

        char[][] expected = new char[][]{
                {'#','#','#','#','#','#','#','#'},
                {'#','.','.','.','@','+','+','#'},
                {'#','.','#','#','#','#','+','#'},
                {'#','.','.','.','.','+','+','#'},
                {'#','#','#','#','#','+','#','#'},
                {'#','.','X','+','+','+','.','#'},
                {'#','#','#','#','#','#','#','#'}
        };

        char[][] actual = main.searchRoute(map);
        for (int i = 0; i < expected.length; i++)
            assertTrue(Arrays.equals(expected[i], actual[i]));
    }

    @Test
    public void testOnExistingVisitedVertices(){
        char[][] map = new char[][]{
                {'#','#','#','#','#','#','#'},
                {'#','.','.','.','.','.','#'},
                {'#','.','.','@','.','.','#'},
                {'#','.','.','.','.','.','#'},
                {'#','.','.','.','.','.','#'},
                {'#','.','.','X','.','.','#'},
                {'#','#','#','#','#','#','#'}
        };

        char[][] expected = new char[][]{
                {'#','#','#','#','#','#','#'},
                {'#','.','.','.','.','.','#'},
                {'#','.','.','@','.','.','#'},
                {'#','.','.','+','.','.','#'},
                {'#','.','.','+','.','.','#'},
                {'#','.','.','X','.','.','#'},
                {'#','#','#','#','#','#','#'}
        };

        char[][] actual = main.searchRoute(map);
        for (int i = 0; i < expected.length; i++)
            assertTrue(Arrays.equals(expected[i], actual[i]));
    }

    @Test
    public void testOnImpossibleCycle(){
        char[][] map = new char[][]{
                {'#','#','#','#','#','#','#'},
                {'#','.','.','@','.','.','#'},
                {'#','.','.','.','.','.','#'},
                {'#','.','.','.','.','.','#'},
                {'#','#','#','#','#','#','#'},
                {'#','.','.','X','.','.','#'},
                {'#','#','#','#','#','#','#'}
        };

        char[][] actual = main.searchRoute(map);
        assertNull(actual);
    }

    @Test
    public void testOnImpossibleRoute(){
        char[][] map = new char[][]{
                {'#','#','#','#','#','#','#'},
                {'#','.','.','@','.','.','#'},
                {'#','#','#','#','#','#','#'},
                {'#','.','.','.','.','.','#'},
                {'#','.','X','.','.','.','#'},
                {'#','.','.','.','.','.','#'},
                {'#','#','#','#','#','#','#'}
        };

        char[][] actual = main.searchRoute(map);
        assertNull(actual);
    }

    @Test
    public void testOnNull() {
        char[][] actual = main.searchRoute(null);
        assertNull(actual);
    }

    @Test
    public void testOnIsNotFoundStartPosition(){
        char[][] map = new char[][]{
                {'.', '.', '.'},
                {'.', 'X', '.'},
                {'.', '.', '.'}
        };

        char[][] actual = main.searchRoute(map);
        assertNull(actual);
    }

    @Test
    public void testOnIsNotFoundGoalPosition(){
        char[][] map = new char[][]{
                {'.', '.', '.'},
                {'.', '@', '.'},
                {'.', '.', '.'}
        };

        char[][] actual = main.searchRoute(map);
        assertNull(actual);
    }
}
