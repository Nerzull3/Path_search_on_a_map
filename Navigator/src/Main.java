import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Main implements Navigator {
    private enum State{
        Empty,
        Wall,
        Visited
    }

    @Override
    public char[][] searchRoute(char[][] map) {
        if (map == null) return null;

        Point start = null;
        Point goal = null;

        int height = map.length;
        int width = map[0].length;

        State[][] stateMap = new State[height][width];

        for (int y = 0; y < height; y++){
            for (int x = 0; x < width; x++){
                switch (map[y][x]) {
                    case '@':
                        start = new Point(x, y);
                        stateMap[y][x] = State.Empty;
                        break;

                    case 'X':
                        goal = new Point(x, y);
                        stateMap[y][x] = State.Empty;
                        break;

                    case '.':
                        stateMap[y][x] = State.Empty;
                        break;

                    case '#':
                        stateMap[y][x] = State.Wall;
                        break;
                }
            }
        }
        if (start == null || goal == null) return null;

        System.out.printf("\nstart: (%d, %d)\n", start.x, start.y);
        System.out.printf("goal: (%d, %d)\n", goal.x, goal.y);

        HashMap<Point, Point> track = new HashMap<>();
        track.put(start, null);
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()){
            Point node = queue.remove();
            stateMap[node.y][node.x] = State.Visited;

            // наполнение множества для вершины node смежными с ней вершинами
            HashSet<Point> linkedVertices = new HashSet<>();
            for (int dy = -1; dy <= 1; dy++){
                for (int dx = -1; dx <= 1; dx++){
                    if (stateMap[node.y + dy][node.x + dx] == State.Wall || dx != 0 && dy != 0) continue;
                    linkedVertices.add(new Point(node.x + dx, node.y + dy));
                }
            }

            // пробегаемся по смежным вершинам
            for (Point nextNode : linkedVertices) {
                if (track.containsKey(nextNode) || stateMap[nextNode.y][nextNode.x] == State.Visited) continue;
                track.put(nextNode, node);
                queue.add(nextNode);
            }
            // дошли до цели
            if (track.containsKey(goal)) break;
        }

        // не дошли до цели, обойдя все возможные пути
        if (!track.containsKey(goal)) return null;

        Point pathItem = goal;
        LinkedList<Point> vertices = new LinkedList<>();
        while (pathItem != null){
            vertices.add(pathItem);
            pathItem = track.get(pathItem);
        }

        for (int i = 1; i < vertices.size() - 1; i++)
            map[vertices.get(i).y][vertices.get(i).x] = '+';

        return map;
    }
}
