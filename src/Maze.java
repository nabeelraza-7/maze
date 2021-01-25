import java.util.ArrayList;
import java.util.Stack;

public class Maze {
    /**
     * 
     * @author: Nabeel Raza
     */
    private int height;
    private int width;
    private char[][] maze;
    private Node finalNode = null;

    public static void main(String[] args) {
        int height = 10;
        int width = 30;
        Maze maze = new Maze(height, width);
        maze.printMaze();
        
        for (int i = 0; i < width; i++) {
            System.out.print(" =");
        }
        System.out.println();

        Node start = new Node(0, 0);// Node to start from
        if (maze.solveMaze(start)) {
            maze.markPath();
            maze.printMaze();
        } else {
            System.out.println("Failed to find a solution");
        }
    }

    public Maze(int height, int width) {
        /**
         * Creates the maze
         */
        this.height = height;
        this.width = width;
        maze = new char[height][width];
        fillMaze();
    }

    public void markPath() {
        /**
         * Marks the followed path with '*'
         */
        Node parent = finalNode;
        while (parent != null) {
            maze[parent.x][parent.y] = '*';
            parent = parent.parent;
        }
        maze[0][0] = 'S'; // Starting location
        maze[height - 1][width - 1] = 'E'; // Ending location
    }

    private void fillMaze() {
        /**
         * Fills the maze with 20% of '-'
         */
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (Math.random() < 0.2) {
                    maze[i][j] = '-';
                } else {
                    maze[i][j] = ' ';
                }
            }
        }
        maze[0][0] = 'S'; // Starting location
        maze[height - 1][width - 1] = 'E'; // Ending location
    }

    private boolean goalReached(Node pos) {
        /**
         * Checks if the current position pos is the desired goal
         */
        return (pos.x == height - 1) && (pos.y == width - 1);
    }

    public void printMaze() {
        /**
         * Displays the maze on console
         */
        for (int i = 0; i < height; i++) {
            System.out.print("|");
            for (int j = 0; j < width; j++) {
                System.out.print(" " + maze[i][j]);
            }
            System.out.println("|");
        }
    }

    private ArrayList<Node> possibleMoves(Node pos) {
        /**
         * Returns a list with all the possible moves out of NORTH, SOUTH, WEST and EAST
         */
        ArrayList<Node> result = new ArrayList<>();
        // NORTH
        if (pos.y > 0 && maze[pos.x][pos.y - 1] != '-') {
            Node temp = new Node(pos.x, pos.y - 1);
            result.add(temp);
        }
        // SOUTH
        if (pos.y < width - 1 && maze[pos.x][pos.y + 1] != '-') {
            Node temp = new Node(pos.x, pos.y + 1);
            result.add(temp);
        }
        // WEST
        if (pos.x > 0 && maze[pos.x - 1][pos.y] != '-') {
            Node temp = new Node(pos.x - 1, pos.y);
            result.add(temp);
        }
        // EAST
        if (pos.x < height - 1 && maze[pos.x + 1][pos.y] != '-') {
            Node temp = new Node(pos.x + 1, pos.y);
            result.add(temp);
        }
        return result;
    }

    public boolean solveMaze(Node pos) {
        /**
         * Main function which solves the maze it implements Depth First Search
         */
        ArrayList<Node> checked = new ArrayList<>();// Stores all the checked nodes
        Stack<Node> path = new Stack<>();
        path.push(pos);
        // Implementing Depth-First Search
        while (!path.isEmpty()) {
            Node currentNode = path.pop();
            if (goalReached(currentNode)) {
                finalNode = currentNode;
                return true;
            }
            for (Node child : possibleMoves(currentNode)) {
                if (checkIfExists(checked, child)) {
                    continue;
                }
                checked.add(child);
                path.push(new Node(child, currentNode));
            }
        }
        return false;
    }

    private boolean checkIfExists(ArrayList<Node> list, Node n) {
        /**
         * Checks if the node already exists in the checked list
         */
        for (Node node : list) {
            if (node.x == n.x && node.y == n.y)
                return true;
        }
        return false;
    }
}
