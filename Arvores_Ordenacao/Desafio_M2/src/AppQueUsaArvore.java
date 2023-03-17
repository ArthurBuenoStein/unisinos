public class AppQueUsaArvore {
    public static void main(String[] args) {

        Elemento elem = new Elemento(0);
        ArvoreAVL arvore = new ArvoreAVL(new Elemento(8));
        arvore.inserir(new Elemento(5));
        arvore.inserir(new Elemento(1));
        arvore.inserir(new Elemento(7));
        arvore.inserir(new Elemento(15));
        arvore.inserir(new Elemento(12));
        arvore.inserir(new Elemento(18));

       arvore.imprimirInOrdem();
       
       elem.setValor(8);
       arvore.remover(elem);
       System.out.println("");

       arvore.imprimirInOrdem();


       
    }
}
