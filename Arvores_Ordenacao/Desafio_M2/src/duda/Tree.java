package duda;

public class Tree {
    private Node root = null;
    private int height = 1;
    private int balanceFactor = 0;

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

    public int getBalanceFactor() {
        return balanceFactor;
    }

    public void setBalanceFactor(int balanceFactor) {
        this.balanceFactor = balanceFactor;
    }

    public void printTreeValues() {
        if (this.root == null)
            System.out.println("Árvore vazia!");
        else
            printTreeValues(0);
    }

    private void printTreeValues(int level) {
        String tab = "";
        // String childrenRString = this.root.right != null ? "" + this.root.right.root.value : "-";
        // String childrenLString = this.root.left != null ? "" + this.root.left.root.value : "-";
        String nodeInfo = "";
            /*" [R:" + childrenRString + " | L:" + childrenLString 
            + " | H:" + this.height + " | BF:" + this.balanceFactor +  "]"; */

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
        Tree actualTree = new Tree(this.root);
        this.root = addValueR(value, actualTree).root;
        System.out.println("\nValor inserido com sucesso:\n");
    }

    private Tree addValueR(int value, Tree current) {
        if (current == null || current.root == null)
            return new Tree(new Node(value));
        if (value < current.root.value) 
            current.root.left = addValueR(value, current.root.left);
        else if (value > current.root.value)
            current.root.right = addValueR(value, current.root.right);
        else {
            System.out.println("\nValor já existente na árvore!\n");
            return current;
        }
        return rebalance(current);
    }

    public void deleteValue(int value) {
        if (!containsNode(value)) {
            System.out.println("\nValor não existente na árvore!\n");
            return;
        }

        Tree actualTree = new Tree(this.root);
        Tree newTree = deleteValueR(value, actualTree);
        root = newTree != null ? newTree.root : null;
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
            // current.root.value = maxNodeForLeft.root.value;
            // current.root.left = deleteValueR(current.root.value, current.root.left);

            current = deleteValueR(maxNodeForLeft.root.value, current);
            maxNodeForLeft.root.right = current.root.right;
            maxNodeForLeft.root.left = current.root.left;
            current = maxNodeForLeft;
        }
        return rebalance(current);
    }

    private Tree getMaxNode(Tree current) {
        return (current.root == null || current.root.right == null)
            ? current
            : getMaxNode(current.root.right);
    }

    public boolean containsNode(int value) {
        Tree current = this;
        while (current != null) {
            if (current.root.value == value)
                break;
            current = current.root.value < value ? current.root.right : current.root.left;
        }
        return current != null ? true : false;
    }

    private Tree rebalance(Tree currentTree) {
        updateHeight(currentTree);
        updateBalance(currentTree);

        if(currentTree.balanceFactor >= -1 && currentTree.balanceFactor <= 1)
            return currentTree;
        
        System.out.println("\nÁrvore desbalanceada no nó " + currentTree.root.value + " com fator de balanceamento " + currentTree.balanceFactor);

        if (currentTree.balanceFactor < -1) {
            updateHeight(currentTree.root.right);
            updateBalance(currentTree.root.right);
            if (currentTree.root.right.balanceFactor <= -1) {
                System.out.println("Balanceamento necessário: rotação simples para esquerda");
                currentTree = rotateLeft(currentTree);
            } else {
                System.out.println("Balanceamento necessário: rotação dupla para esquerda");
                currentTree.root.right = rotateRight(currentTree.root.right);
                currentTree = rotateLeft(currentTree);
            }
        } else if (currentTree.balanceFactor > 1) {
            updateHeight(currentTree.root.left);
            updateBalance(currentTree.root.left);
            if (currentTree.root.left.balanceFactor >= 1) {
                System.out.println("Balanceamento necessário: rotação simples para direita");
                currentTree = rotateRight(currentTree);
            } else {
                System.out.println("Balanceamento necessário: rotação dupla para direita");
                currentTree.root.left = rotateLeft(currentTree.root.left);
                currentTree = rotateRight(currentTree);
            }
        }
        return currentTree;
    }

    private void updateHeight(Tree t) {
        int leftHeight = getChildrenHeight(t.root.left);
        int rightHeight = getChildrenHeight(t.root.right);
        t.height = (leftHeight > rightHeight ? leftHeight : rightHeight) + 1;
    }

    private int getChildrenHeight(Tree t) {
        return t == null ? 0 : t.height;
    }

    private void updateBalance(Tree t) {
        t.balanceFactor = (t == null) ? 0 : getChildrenHeight(t.root.left) - getChildrenHeight(t.root.right);
    }

    private Tree rotateRight(Tree t) {
        Tree childrenL = t.root.left;
        Tree grandchildrenRByL = childrenL.root.right;
        childrenL.root.right = t;
        t.root.left = grandchildrenRByL;
        return childrenL;
    }

    private Tree rotateLeft(Tree t) {
        Tree childrenR = t.root.right;
        Tree grandchildrenLByR = childrenR.root.left;
        childrenR.root.left = t;
        t.root.right = grandchildrenLByR;
        return childrenR;
    }
}
