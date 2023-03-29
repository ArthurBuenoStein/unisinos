package M2; 

public class Node {
    protected int value;
    protected Tree right = null;
    protected Tree left = null;

    public Node(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Tree getRight() {
        return right;
    }

    public Tree getLeft() {
        return left;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setRight(Tree right) {
        this.right = right;
    }

    public void setLeft(Tree left) {
        this.left = left;
    }
}
