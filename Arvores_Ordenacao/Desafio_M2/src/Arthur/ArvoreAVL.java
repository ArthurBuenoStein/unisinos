package Arthur;
public class ArvoreAVL {
    private Elemento ele;
    private ArvoreAVL dir;
    private ArvoreAVL esq;
    private int bal;

    public ArvoreAVL(){
        this.ele = null; 
        this.dir = null; 
        this.esq = null; 
        this.bal = 0;
    }

    public ArvoreAVL(Elemento ele){
        this.ele = ele;
        this.dir = null;
        this.esq = null;
        this.bal = 0;
        // System.out.println("Criei a Árvore com o elemento "+ele.getValor());
    }

    public int calcularAltura(){
        if (this.esq == null && this.dir == null){ // não tenho filhos
            return 1;
        }
        else if (this.esq != null && this.dir == null){
            return 1 + this.esq.calcularAltura();
        }
        else if (this.esq == null && this.dir != null){
            return 1 + this.dir.calcularAltura();
        }
        else {
            return 1 + Math.max(this.esq.calcularAltura(), this.dir.calcularAltura());
        }
    }

    public void calcularBalanceamento(){
        if (this.dir == null && this.esq == null){
            this.bal = 0;
        }
        else if (this.esq == null && this.dir != null){
            this.bal = this.dir.calcularAltura() - 0;
        }
        else if (this.esq != null && this.dir == null){
            this.bal = 0 - this.esq.calcularAltura();
        }
        else{
            this.bal = this.dir.calcularAltura() - this.esq.calcularAltura();
        }
        if (this.dir != null) this.dir.calcularBalanceamento();
        if (this.esq != null) this.esq.calcularBalanceamento();
        
    }

    public ArvoreAVL verificaBalanceamento(){
        if (this.bal >= 2 || this.bal <= -2){
            if (this.bal >= 2){
                if (this.bal * this.dir.getBalanceamento() > 0){
                    System.out.println("Rotação Simples Direita");
                    return rotacaoSimplesDireita();
                }
                else{
                    System.out.println("Rotação Dupla Direita");
                    return rotacaoDuplaDireita();
                }
            }
            else{ // bal <= -2
                if (this.bal * this.esq.getBalanceamento() > 0 ){
                    System.out.println("Rotação Simples Esquerda");
                    return rotacaoSimplesEsquerda();
                }
                else{
                    System.out.println("Rotação Dupla Esquerda");
                    return rotacaoDuplaEsquerda();
                     }
                }
            }
            this.calcularBalanceamento();
            if (this.esq != null) this.esq = this.esq.verificaBalanceamento();
            if (this.dir != null) this.dir = this.dir.verificaBalanceamento();

            return this;
    }

    public ArvoreAVL rotacaoSimplesDireita(){
        ArvoreAVL filhoDir;
        ArvoreAVL filhoDoFilho = null;

        filhoDir = this.getDireita();
        if (this.dir != null){
            if (this.dir.getEsquerda() != null){
                filhoDoFilho = filhoDir.getEsquerda();
            }
        }
        filhoDir.setEsquerda(this);
        this.setDireita(filhoDoFilho);

        return filhoDir;
    }

    public ArvoreAVL rotacaoSimplesEsquerda(){
        ArvoreAVL filhoEsq;
        ArvoreAVL filhoDoFilho = null;

        filhoEsq = this.getEsquerda();
        if (this.esq != null){
            if (this.esq.getDireita() != null){
                filhoDoFilho = filhoEsq.getDireita();
            }
        }
        filhoEsq.setDireita(this);
        this.setEsquerda(filhoDoFilho);

        return filhoEsq;
    }

    public ArvoreAVL rotacaoDuplaDireita(){
        ArvoreAVL arvore = this;
        ArvoreAVL filhoDir = this.getDireita();
        ArvoreAVL filhoDoFilho = filhoDir.getEsquerda();
        ArvoreAVL noInserido = filhoDoFilho.getDireita();

        filhoDir.setEsquerda(noInserido);
        filhoDoFilho.setDireita(filhoDir);
        this.setDireita(filhoDoFilho);

        ArvoreAVL novoFilhoDireita = this.getDireita();
        arvore.setDireita(null);
        novoFilhoDireita.setEsquerda(arvore);
        return novoFilhoDireita;
        
    }

    public ArvoreAVL rotacaoDuplaEsquerda(){
        ArvoreAVL arvore = this;
        ArvoreAVL filhoEsq = this.getEsquerda();
        ArvoreAVL filhoDoFilho = filhoEsq.getDireita();
        ArvoreAVL noInserido = filhoDoFilho.getEsquerda();

        filhoEsq.setDireita(noInserido);
        filhoDoFilho.setEsquerda(filhoEsq);
        this.setEsquerda(filhoDoFilho);

        ArvoreAVL novoFilhoEsquerda = this.getEsquerda();
        arvore.setEsquerda(null);
        novoFilhoEsquerda.setDireita(arvore);
        return novoFilhoEsquerda;
    }

    // métodos de controle;

    public ArvoreAVL inserir(Elemento novo){
        if(isEmpty()){
            this.ele = novo;
        }
        else{
            ArvoreAVL novaArvoreAVL = new ArvoreAVL(novo);
            if (novo.getValor() < this.ele.getValor()){ // vou inserir na descendência esquerda
                if (this.esq == null){ // sou um nó folha?
                    this.esq = novaArvoreAVL;
                    // System.out.println("Inseri o elemento "+ novo.getValor() + " à esquerda de "+ this.ele.getValor());
                }
                else{
                    this.esq = this.esq.inserir(novo); // responsabilidade para sub-árvore esquerda
                }
            }
            else if (novo.getValor() > this.ele.getValor()){ // inserir na descendência direita
                if (this.dir == null){ // sou um nó folha?
                    this.dir = novaArvoreAVL;
                    // System.out.println("Inseri o elemento "+ novo.getValor() + " à direita de "+ this.ele.getValor());
                }
                else{
                    this. dir = this.dir.inserir(novo);
                }
            }
        }
        return this;
    }

    // Remoção do nó da árvore

    public ArvoreAVL remover(Elemento ele) {
        // Primeiro caso - encontre elemento
        if (this.ele.getValor() == ele.getValor()){
            // caso mais simples - o elemento está em um nó folha
            if (this.dir == null && this.esq == null){
                return null;
            }
            else{
                 // tem filhos a esquerda, porém não tem a direita
                if (this.esq != null && this.dir == null){
                    return this.esq;
                }
                // tenho filhos à direita e não tenho à esquerda.
                else if (this.dir != null && this.esq == null){
                    return this.dir;
                }
                // possuo filhos dos dois lados;
                else{
                    // adotar a estratégia do maior entre os menores.
                    ArvoreAVL aux = this.esq;
                    while (aux.dir != null){ // enquanto ouvir filhos à direita
                        aux = aux.dir;
                    }
                    // troco os elementos da árvore
                    this.ele = aux.getElemento(); // o nó atual recebe o elemento do aux
                    // o maior dentre os menores
                    aux.setElemento(ele); // insiro no nó folha, o elemento a ser eleminido;

                    this.esq = esq.remover(ele);

                }
            }
        }
        else if (ele.getValor() < this.ele.getValor()){
            // Delegar a responsabilidade da sub árvore da esquerda
            this.esq = this.esq.remover(ele);
        }
        else if (ele.getValor() > this.ele.getValor()){
            // Delegar a responsabilidade da sub árvore da direita
            this.dir = this.dir.remover(ele);
        }
        return this;
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
                    return this.esq.busca(valor); // repassei a responsabilidade para a subArvoreAVL esquerda
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

    public void setDireita(ArvoreAVL dir) {
        this.dir = dir;
    }

    public void setEsquerda(ArvoreAVL esq) {
        this.esq = esq;
    }

    public ArvoreAVL getDireita() {
        return dir;
    }

    public ArvoreAVL getEsquerda() {
        return esq;
    }

    public Elemento getElemento() {
        return ele;
    }

    public int getBalanceamento(){
        return this.bal;
    }

    public void setBalanceamento(int bal){
        this.bal = bal;
    }

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

    public void imprimirInOrdem(){
        if (!isEmpty()){
            
            if (this.esq != null){
                this.esq.imprimirInOrdem();
            }
            System.out.print(this.ele.getValor() + " ");
            if (this.dir != null){
                this.dir.imprimirInOrdem();
            }
        }
    }

    public void imprimirInOrdemInversa(){
        if (!isEmpty()){
            
            if (this.dir != null){
                this.dir.imprimirInOrdemInversa();
            }
            System.out.print(this.ele.getValor() + " ");
            if (this.esq != null){
                this.esq.imprimirInOrdemInversa();
            }
            
        }
    }

    public void imprimirPosOrdem(){
        if (!isEmpty()){
            if (this.dir != null){
                this.dir.imprimirPosOrdem();
            }
            if (this.esq != null){
                this.esq.imprimirPosOrdem();
            }
            System.out.print(this.ele.getValor() + " ");
        }
    }

    public String printArvoreAVL(int level) {
        String str = this.toString() + "\n";
        for (int i=0; i<level; i++){
            str += "\t";
        }
        if (this.esq != null){
            str += "+-ESQ: "+this.esq.printArvoreAVL(level+1);
        }
        else{
            str += "+-ESQ: NULL";
        }
        str += "\n";
        for (int i=0; i<level; i++){
            str += "\t";
        }
        if (this.dir != null){            
            str += "+-DIR: "+this.dir.printArvoreAVL(level+1);
        }
        else{
            str += "+-DIR: NULL";
        }
        str += "\n";
        return str;
    }

    public String toString() {
        return "["+this.ele.getValor()+"] ("+this.bal+")";
    }

}

