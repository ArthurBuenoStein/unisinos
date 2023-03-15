package duda;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Tree binaryTree = new Tree();
        binaryTree.addValue(3);
        binaryTree.addValue(14);
        binaryTree.addValue(17);
        binaryTree.addValue(1);
        binaryTree.addValue(5);
        binaryTree.addValue(4);
        binaryTree.addValue(8);
        binaryTree.addValue(10);

        binaryTree.printTreeValues();
    }
}
