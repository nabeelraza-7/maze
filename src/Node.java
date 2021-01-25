
public class Node{
    public Node parent = null;
    public int x, y;
    public Node(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Node(Node n, Node parent){
        this.x = n.x;
        this.y = n.y;
        this.parent = parent;
    }

    public String toString(){
        return "(" + x + ", " + y + ")" + parent;
    }
}
