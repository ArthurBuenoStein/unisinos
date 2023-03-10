package chatpgt;
class AVLNode {
    int value;
    AVLNode left;
    AVLNode right;
    int height;

    public AVLNode(int value) {
        this.value = value;
        this.height = 1;
    }
}
