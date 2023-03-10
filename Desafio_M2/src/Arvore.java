public class Arvore {
    private Elemento ele;
    private Arvore dir;
    private Arvore esq;

    public Arvore(){
        this.ele = null; 
        this.dir = null; 
        this.esq = null; 
    }

    public Arvore(Elemento ele){
        this.ele = ele;
        this.dir = null;
        this.esq = null;
    }

    // métodos de controle;
    public boolean isEmpty(){
        return (this.ele == null);
    }

    public void inserir(Elemento novo){
        if(isEmpty()){
            this.ele = novo;
        }
        else{
            Arvore novaArvore = new Arvore(novo);
            if (novo.getValor() < this.ele.getValor()){ // inserir na esquerda
                if (this.esq == null){ // nó folha
                    this.esq = novaArvore;
                }
            }
        }
    }

    // gets e sets
    public void setElemento(Elemento ele) {
        this.ele = ele;
    }

    public void setDireita(Arvore dir) {
        this.dir = dir;
    }

    public void setEsquerda(Arvore esq) {
        this.esq = esq;
    }

    public Arvore getDireita() {
        return dir;
    }

    public Arvore getEsquerda() {
        return esq;
    }

    public Elemento getElemento() {
        return ele;
    }


}

