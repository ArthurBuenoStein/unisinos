import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Digite a operação desejada: ");
            System.out.println("1 - Inserir");
            System.out.println("2 - Excluir");
            System.out.println("3 - Buscar");
            System.out.println("0 - Sair");

            int operation = scanner.nextInt();

            if (operation == 0) {
                break;
            }

            System.out.println("Digite o valor a ser operado: ");
            int value = scanner.nextInt();

            switch (operation) {
                case 1:
                    tree.insert(value);
                    break;
                case 2:
                    tree.delete(value);
                    break;
                case 3:
                    if (tree.search(value)) {
                        System.out.println(value + " encontrado na árvore!");
                    } else {
                        System.out.println(value + " não encontrado na árvore!");
                    }
                    break;
                default:
                    System.out.println("Operação inválida!");
                    break;
            }

            tree.print();
        }
    }
}

