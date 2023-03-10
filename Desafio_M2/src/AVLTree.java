import java.util.Scanner;

public class AVLTree {
    private AVLNode root;

    public AVLTree() {
        this.root = null;
    }

    // inserção
    public void insert(int value) {
        this.root = insert(this.root, value);
    }

    private AVLNode insert(AVLNode node, int value) {
        if (node == null) {
            return new AVLNode(value);
        }

        if (value < node.value) {
            node.left = insert(node.left, value);
        } else if (value > node.value) {
            node.right = insert(node.right, value);
        } else {
            // valor já existe na árvore
            return node;
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        // caso LL
        if (balance > 1 && value < node.left.value) {
            System.out.println("Rotação à direita em " + node.value + " (caso LL)");
            return rightRotate(node);
        }

        // caso RR
        if (balance < -1 && value > node.right.value) {
            System.out.println("Rotação à esquerda em " + node.value + " (caso RR)");
            return leftRotate(node);
        }

        // caso LR
        if (balance > 1 && value > node.left.value) {
            System.out.println("Rotação à esquerda em " + node.left.value + " (caso LR)");
            node.left = leftRotate(node.left);
            System.out.println("Rotação à direita em " + node.value + " (caso LR)");
            return rightRotate(node);
        }

        // caso RL
        if (balance < -1 && value < node.right.value) {
            System.out.println("Rotação à direita em " + node.right.value + " (caso RL)");
            node.right = rightRotate(node.right);
            System.out.println("Rotação à esquerda em " + node.value + " (caso RL)");
            return leftRotate(node);
        }

        return node;
    }

    // exclusão
    public void delete(int value) {
        this.root = delete(this.root, value);
    }

    private AVLNode delete(AVLNode node, int value) {
        if (node == null) {
            return node;
        }

        if (value < node.value) {
            node.left = delete(node.left, value);
        } else if (value > node.value) {
            node.right = delete(node.right, value);
        } else {
            // encontrou o nó a ser deletado

            if (node.left == null || node.right == null) {
                // tem no máximo um filho
                AVLNode temp = null;

                if (node.left != null) {
                    temp = node.left;
                } else {
                    temp = node.right;
                }

                if (temp == null) {
                    // não tem filhos
                    temp = node;
                    node = null;
                } else {
                    // tem um filho
                    node = temp;
                }
            } else {
                // tem dois filhos
                AVLNode temp = minValueNode(node.right);

                node.value = temp.value;

                node.right = delete(node.right, temp.value);
            }
        }

        if (node == null) {

        }
    }

    public void print(String prefix, boolean isLeft) {
        if (right != null) {
            right.print(prefix + (isLeft ? "│   " : "    "), false);
        }
        System.out.println(prefix + (isLeft ? "└── " : "┌── ") + key + "(" + balanceFactor + ")");
        if (left != null) {
            left.print(prefix + (isLeft ? "    " : "│   "), true);
        }
    }

    public void print() {
        if (root != null) {
            root.print("", true);
        }
        System.out.println("-----------------------");
    }
}


           
