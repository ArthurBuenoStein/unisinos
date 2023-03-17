package Arthur;
import java.util.Scanner;

public class AppQueUsaArvore {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        ArvoreAVL arvore = new ArvoreAVL(new Elemento(teclado.nextInt()));
        System.out.println(arvore.printArvoreAVL(0));

        while(true){
            arvore.inserir(new Elemento(teclado.nextInt()));
            System.out.println(arvore.printArvoreAVL(0));
        }
    }
}
