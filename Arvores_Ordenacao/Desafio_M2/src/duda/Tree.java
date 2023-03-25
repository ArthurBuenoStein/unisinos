package duda;

public class Tree {
    private Node root = null;
    private int height = 1;

    public Tree() {
    }

    public Tree(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void printTreeValues() {
        if (this.root == null)
            System.out.println("Árvore vazia!");
        else
            this.printTreeValues(0);
    }

    private void printTreeValues(int level) {
        String tab = "";
        String childrenRString = this.root.right != null ? "" + this.root.right.root.value : "-";
        String childrenLString = this.root.left != null ? "" + this.root.left.root.value : "-";
        String nodeInfo = " [R:" + childrenRString + " | L:" + childrenLString + " | H:" + this.height + "]";

        for (int i = 0; i < level; i++)
            tab += "|   ";
        if (level > 0)
            tab += "|---";

        System.out.println(tab + this.root.getValue() + nodeInfo);

        if (this.root.left != null)
            this.root.left.printTreeValues(level + 1);
        if (this.root.right != null)
            this.root.right.printTreeValues(level + 1);
    }

    public void addValue(int value) {
        if (containsNode(value)) {
            System.out.println("\nValor já existente na árvore!\n");
            return;
        }
        root = addValueR(value, this, 0).root;
        System.out.println("\nValor inserido com sucesso:\n");
    }

    private Tree addValueR(int value, Tree current, int level) {
        if (current == null || current.root == null)
            return new Tree(new Node(value));
        if (value < current.root.value) {
            current.root.left = addValueR(value, current.root.left, current.height + 1);
        } else if (value > current.root.value) {
            current.root.right = addValueR(value, current.root.right, current.height + 1);
        }
        current.height = current.calculateHeight();
        return current;
    }

    public void deleteValue(int value) {
        if (!containsNode(value)) {
            System.out.println("\nValor não existente na árvore!\n");
            return;
        }

        Tree actualRootTree = deleteValueR(value, this);
        root = actualRootTree != null ? actualRootTree.root : null;
        System.out.println("\nValor removido com sucesso:\n");
    }

    private Tree deleteValueR(int value, Tree current) {
        if (current == null)
            return null;

        if (value > current.root.value)
            current.root.right = deleteValueR(value, current.root.right);
        else if (value < current.root.value)
            current.root.left = deleteValueR(value, current.root.left);
        else {
            if (current.root.left == null && current.root.right == null)
                return null;
            if (current.root.right == null)
                return current.root.left;
            if (current.root.left == null)
                return current.root.right;

            Tree maxNodeForLeft = getMaxNode(current.root.left);
            current = deleteValueR(maxNodeForLeft.root.value, current);
            maxNodeForLeft.root.right = current.root.right;
            maxNodeForLeft.root.left = current.root.left;
            current = maxNodeForLeft;
        }

        current.height = current.calculateHeight();
        return current;
    }

    private Tree getMaxNode(Tree current) {
        return (current.root == null || current.root.right == null)
                ? current
                : getMaxNode(current.root.right);
    }

    public boolean containsNode(int value) {
        return containsNodeR(value, this);
    }

    private boolean containsNodeR(int value, Tree current) {
        if (current == null || current.root == null)
            return false;
        if (value == current.root.value)
            return true;

        return value < current.root.value
                ? containsNodeR(value, current.root.left)
                : containsNodeR(value, current.root.right);
    }

    public int calculateHeight() {
        int rightHeight = this.root.right == null ? 0 : this.root.right.height;
        int leftHeight = this.root.left == null ? 0 : this.root.left.height;
        int biggestHeight = rightHeight > leftHeight ? rightHeight : leftHeight;
        return biggestHeight + 1;
    }
}
