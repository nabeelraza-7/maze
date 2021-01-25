public class Node{
    /**
     * This class holds information
     * about any location of the maze
     * with it's parent if any.
     */
    public Node parent = null;
    public int x, y;
    public Node(int x, int y){
        // Constructor without the need of parent
        this.x = x;
        this.y = y;
    }

    public Node(Node n, Node parent){
        // Constructor for nested Nodes
        this.x = n.x;
        this.y = n.y;
        this.parent = parent;
    }

    public String toString(){
        return "(" + x + ", " + y + ")" + parent;
    }
}
