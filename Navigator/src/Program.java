public class Program {
    public static void main(String[] args) {

        char[][] map = new char[][]{
                {'#','#','#','#','#','#','#','#'},
                {'#','.','.','.','@','.','.','#'},
                {'#','.','#','#','#','#','.','#'},
                {'#','.','.','.','.','.','.','#'},
                {'#','#','#','#','#','.','#','#'},
                {'#','.','X','.','.','.','.','#'},
                {'#','#','#','#','#','#','#','#'}
        };

        System.out.println();
        for (char[] aMap : map) System.out.println(aMap);
        Main main = new Main();
        char[][] newMap = main.searchRoute(map);

        if (newMap != null)
            for (char[] lines : newMap) System.out.println(lines);
    }
}
