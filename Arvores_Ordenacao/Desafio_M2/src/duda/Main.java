package duda;
import java.util.Scanner;

public class Main {
    // TODO O melhor é declarar como global OU declarar dentro da função e passar como 
    // parâmetro para as demais OU cnetralizar as alterações dentro de uma única função?
    static Tree binaryTree = new Tree();

    public static void main(String[] args) {
        int userSelectedOption;
        do {
            Terminal.printMessageForTime("", 0);
            userSelectedOption = getUserSelectedOption();
            Terminal.printMessageForTime("", 0);

            // TODO tratar para não adicionar valores invalidos (ex: string em numero)
            switch(userSelectedOption) {
                case 1:
                    insertValue();
                    break;
                case 2:
                    deleteValue();
                    break;
                case 3:
                    printTree();
                    break;
                case 4:
                break;
            }
        } while (userSelectedOption != 4);
    }

    public static String menuUser() {
        String menu = "ÁRVORE AVL";
        menu += "\nDigite a opção que desejar: ";
        menu += "\n1. Inserir valor";
        menu += "\n2. Remover valor";
        menu += "\n3. Imprimir árvore";
        menu += "\n4. Sair";
        return menu;
    }

    public static int getUserSelectedOption() {
        int userSelectedOption;
        System.out.println(menuUser());
        do {
            userSelectedOption = Teclado.leInt();
            if (userSelectedOption > 4 || userSelectedOption < 1) {
                System.out.println("\nValor inválido. Tente novamente!");
            }
        } while (userSelectedOption > 4 || userSelectedOption < 1);
        return userSelectedOption;
    }

    public static void insertValue() {
        int value = Teclado.leInt();
        binaryTree.addValue(value);
    }

    public static void deleteValue() {
        System.out.println("Implementar o delete de um valor da árvore");
    }

    public static void printTree() {
        int back;
        binaryTree.printTreeValues();
        System.out.println("\nPara retornar, digite 0:");
        do {
            back = Teclado.leInt();
        } while(back != 0);
    }
}
