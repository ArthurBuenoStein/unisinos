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
        // System.out.println("Criei a Árvore com o elemento "+ele.getValor());
    }

    // métodos de controle;
    public boolean isEmpty(){
        return (this.ele == null);
    }

    public void imprimirPreOrdem(){
        if (!isEmpty()){
            System.out.print(this.ele.getValor() + " ");
            if (this.esq != null){
                this.esq.imprimirPreOrdem();
            }
            if (this.dir != null){
                this.dir.imprimirPreOrdem();
            }
        }
    }

    public void inserir(Elemento novo){
        if(isEmpty()){
            this.ele = novo;
        }
        else{
            Arvore novaArvore = new Arvore(novo);
            if (novo.getValor() < this.ele.getValor()){ // vou inserir na descendência esquerda
                if (this.esq == null){ // sou um nó folha?
                    this.esq = novaArvore;
                    // System.out.println("Inseri o elemento "+ novo.getValor() + " à esquerda de "+ this.ele.getValor());
                }
                else{
                    this.esq.inserir(novo); // responsabilidade para sub-árvore esquerda
                }
            }
            else if (novo.getValor() > this.ele.getValor()){ // inserir na descendência direita
                if (this.dir == null){ // sou um nó folha?
                    this.dir = novaArvore;
                    // System.out.println("Inseri o elemento "+ novo.getValor() + " à direita de "+ this.ele.getValor());
                }
                else{
                    this.dir.inserir(novo);
                }
            }
        }
    }

    public boolean busca(int valor){
        if (isEmpty()){
            return false;
        }
        if (this.ele.getValor() == valor){
            return true;
        }
        else {
            if (valor < this.ele.getValor()){
                if (this.esq == null){
                    return false;
                }                
                else{
                    return this.esq.busca(valor); // repassei a responsabilidade para a subarvore esquerda
                }
        }
            else if (valor > this.ele.getValor()){
                if (this.dir == null){
                    return false;
                }
                else{
                    return this.dir.busca(valor);
                }
            }
            return false;
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

