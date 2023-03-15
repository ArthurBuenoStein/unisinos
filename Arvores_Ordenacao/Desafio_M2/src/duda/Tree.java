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

    public void deleteValue(int value) {
        root = deleteValueR(value, root);
    }

    private Node deleteValueR(int value, Node current) {
        if(current == null)
            return null;

        if(value > current.value) {
            current.right = deleteValueR(value, current.right);
            return current;
        } else if (value < current.value) {
            current.left = deleteValueR(value, current.left);
            return current;
        } else {
            if (current.left == null && current.right == null)
                return null;
            if (current.right == null)
                return current.left;
            if (current.left == null)
                return current.right;

            Node maxNodeForLeft = getMaxNode(current.left);
            current = deleteValueR(maxNodeForLeft.value, current);
            maxNodeForLeft.right = current.right;
            maxNodeForLeft.left = current.left;
            return maxNodeForLeft;
        }
    }

    private Node getMaxNode(Node current) {
        return current.right == null ? current : getMaxNode(current.right);
    }

    public String printTreeValuesR(Node node, String str, String arc, String tab) {
        if(node == null)
            return str;

        // TODO apenas para debug
        // Node childrenR = node.right;
        // Node childrenL = node.left;
        // String childrenRString = childrenR != null ? "" + childrenR.value : ".";   
        // String childrenLString = childrenL !=null  ? "" + childrenL.value : ".";   
        String childrens = ""; //"(R:" + childrenRString + " L:" + childrenLString + ")";    

        str = str + tab + arc + node.value + childrens + "\n";
        tab = tab + "|   ";
        
        
        str = printTreeValuesR(node.left, str, "|---", tab);
        str = printTreeValuesR(node.right, str, "|---", tab);
        return str;
    }

    public void printTreeValues() {
        String strTreeValues = printTreeValuesR(this.root, "", "", "");
        System.out.print(strTreeValues);
    }
}
