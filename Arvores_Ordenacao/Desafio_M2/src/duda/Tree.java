package duda; 
public class Tree {
    private Node root = null;
    // private int height;

    public Tree() {

    }

    public Node getRoot() {
        return root;
    }

    // public int getHeight() {
    //     return height;
    // }

    public void setRoot(Node root) {
        this.root = root;
    }

    // public void setHeight(int height) {
    //     this.height = height;
    // }

    public void addValue(int value) {
        root = addValueR(value, root);
    }

    private Node addValueR(int value, Node current) {
        if(current == null)
            return new Node(value);
        if(value < current.value)
            current.left = addValueR(value, current.left);
        else if(value > current.value)
            current.right = addValueR(value, current.right);
        else
            return current;
        return current;
    }

    public String printTreeValuesR(Node node, String str, String arc, String tab) {
        if(node == null)
            return str;

        str = str + tab + arc + node.getValue() + "\n";
        tab = tab + "|   ";
        
        
        str = printTreeValuesR(node.getLeft(), str, "|---", tab);
        str = printTreeValuesR(node.getRight(), str, "|---", tab);
        return str;
    }

    public void printTreeValues() {
        String strTreeValues = printTreeValuesR(this.root, "", "", "");
        System.out.print(strTreeValues);
    }
}
