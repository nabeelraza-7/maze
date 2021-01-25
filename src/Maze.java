import java.util.ArrayList;
import java.util.Stack;

/**
 *  (1,1, (null))
 *  (1,2, (1, 1, (null)))
 *  (1,3, (1,2, (1, 1, (null))))
 */

public class Maze {
    int width;
    int height;
    char[][] maze;
    Node finalNode = null;

    public static void main(String[] args){
        Maze m = new Maze(8, 8);
        m.printMaze();
        System.out.println("================================");
        Node start = new Node(0, 0);
        if(m.solveMaze(start)){
            m.markPath();
            m.printMaze();
        } else {
            System.out.println("Failed to find a solution");
        }
        // System.out.println(m.possibleMoves(start));
    }

    public void markPath() {
        /**
         * Marks the followed path with '*'
         */
        Node parent = finalNode;
        while (parent != null){
            maze[parent.x][parent.y] = '*';
            parent = parent.parent;
        }
        maze[0][0] = 'S';
        maze[width-1][height-1] = 'E';
    }

    public Maze(int width, int height) {
        /**
         * Creates the maze
         */
        this.width = width;
        this.height = height;
        maze = new char[width][height];
        fillMaze();
    }

    private void fillMaze(){
        /**
         * Fills the maze with 20% of '-'
         */
        for (int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                // maze[i][j] = ' ';
                if (Math.random() < 0.2){
                    maze[i][j] = '-';
                } else{
                    maze[i][j] = ' ';
                }
            }
        }
        maze[0][0] = 'S';
        maze[width-1][height-1] = 'E';
    }

    private boolean goalReached(Node pos){
        /**
         * Checks if the current position pos is the desired goal
         */
        return (pos.x == width-1) && (pos.y == height-1);
    }

    public void printMaze(){
        /**
         * Displays the maze on console
         */
        for (int i = 0; i < width; i++){
            System.out.print("|");
            for (int j = 0; j < height; j++){
                System.out.print(" " + maze[i][j]);
            }
            System.out.println("|");
        }
    }
    
    private ArrayList<Node> possibleMoves(Node pos){
        /**
         * Returns a list with all the possible moves
         * out of NORTH, SOUTH, WEST and EAST
         */
        ArrayList<Node> result = new ArrayList<>();
        // NORTH
        if (pos.y > 0 && maze[pos.x][pos.y-1] != '-'){
            Node temp = new Node(pos.x, pos.y-1);
            result.add(temp);
        } 
        // SOUTH
        if (pos.y < height-1 && maze[pos.x][pos.y+1] != '-'){
            Node temp = new Node(pos.x, pos.y+1);
            result.add(temp);
        } 
        // WEST
        if (pos.x > 0 && maze[pos.x-1][pos.y] != '-'){
            Node temp = new Node(pos.x-1, pos.y);
            result.add(temp);
        } 
        // EAST
        if (pos.x < width-1 && maze[pos.x+1][pos.y] != '-'){
            Node temp = new Node(pos.x+1, pos.y);
            result.add(temp);
        } 
        return result;
    }

    public boolean solveMaze(Node pos){
        /**
         * Main function which solves the maze
         * it implements Depth First Search
         */
        //This will Store all the Nodes that have been checked.
        ArrayList<Node> checked = new ArrayList<>();
        Stack<Node> path = new Stack<>();
        path.push(pos);
        // Implementing Depth-First Search
        while (!path.isEmpty()){
            Node currentNode = path.pop();
            if (goalReached(currentNode)){
                finalNode = currentNode;
                return true;
            }
            for (Node child: possibleMoves(currentNode)){
                // System.out.println("Current Node: " + currentNode);
                // if (checked.contains(child)){
                if (checkIfExists(checked, child)){
                    // System.out.println("Already Checked!");
                    continue;
                }
                checked.add(child);
                path.push(new Node(child, currentNode));
            }
        }
        return false;
    }

    private boolean checkIfExists(ArrayList<Node> l, Node n){
        for (Node node: l){
            if (node.x == n.x && node.y == n.y) return true;
        }
        return false;
    }
}
