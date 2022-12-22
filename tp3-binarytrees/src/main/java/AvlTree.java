public class AvlTree<T extends Comparable<T>> extends BinarySearchTree<T>{
    @Override
    public void add(T value) {
        this.root = add(value, this.root);
    }

    protected BinaryNode<T> add(T value, BinaryNode<T> curNode) {
        if (curNode == null)
            return new BinaryNode<>(value);
        int compareResult = value.compareTo(curNode.value);
        if (compareResult < 0)
            curNode.left = add(value, curNode.left);
        else if (compareResult > 0)
            curNode.right = add(value, curNode.right);
        else; // No duplicates
        return balance(curNode);
    }

    @Override
    public boolean contains(T value) {
        return contains(value, root);
    }

    private boolean contains(T value, BinaryNode<T> curNode) {
        if (curNode == null) {
            return false;
        }

        Integer compareResult = ((Integer)value).compareTo(curNode.getValue());
        if (compareResult == 0) {
            return true;
        }
        else if (compareResult > 0) {
            return contains(value, curNode.right);
        }
        else if (compareResult < 0) {
            return contains(value, curNode.left);
        }

        return false;
    }

    @Override
    public void remove(T value) {
        this.root = remove(value, this.root);
    }

    protected BinaryNode<T> remove(T value, BinaryNode<T> curNode) {
        if (curNode == null)
            return curNode; // Item not found; do nothing

        int compareResult = value.compareTo(curNode.value);

        if (compareResult < 0)
            curNode.left = remove(value, curNode.left);
        else if (compareResult > 0)
            curNode.right = remove(value, curNode.right);
        else if (curNode.left != null && curNode.right != null) { // Two children
            curNode.value = findMin(curNode.right).value;
            curNode.right = remove(curNode.value, curNode.right);
        }
        else
            curNode = (curNode.left != null) ? curNode.left : curNode.right;
        return balance(curNode);
    }

    private BinaryNode<T> balance(BinaryNode<T> node) {
        if (node == null)
            return node;
        if (height(node.left) - height(node.right) > 1)
       {
            if (height(node.left.left) >= height(node.left.right))
                node = rotateWithLeftChild(node);
            else
                node = doubleWithLeftChild(node);
        }
        else if (height(node.right) - height(node.left) > 1) {
            if (height(node.right.right) >= height(node.right.left))
                node = rotateWithRightChild(node);
            else
                node = doubleWithRightChild(node);
        }
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        return node;
    }
    // Left - Left
    private BinaryNode<T> rotateWithLeftChild(BinaryNode<T> k2) {
        BinaryNode<T> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        // Mettre à jour les hauteurs à la rotation
        k2.height = Math.max( height(k2.left), height(k2.right) + 1);
        k1.height = Math.max( height(k1.left), height(k2)) + 1;
        return k1;
    }
    // Left - Right
    private BinaryNode<T> rotateWithRightChild(BinaryNode<T> k1) {
        BinaryNode<T> k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        // Mettre à jour les hauteurs à la rotation
        k1.height = Math.max(height(k1.left), height(k1.right) + 1);
        k2.height = Math.max(height(k2.right), height(k1) + 1);
        return k2;
    }

    // Left - Right
    private BinaryNode<T> doubleWithLeftChild(BinaryNode<T> k3) {
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild( k3 );
    }

    // Right - Left
    private BinaryNode<T> doubleWithRightChild(BinaryNode<T> k1) {
        k1.right = rotateWithLeftChild(k1.right);
        return rotateWithRightChild(k1);
    }

    public void printPreOrder() {
        super.printPreOrder();
    }

    public void printPostOrder(){
        super.printPostOrder();
    }

    public void printInOrder(){
        super.printInOrder();
    }

//    private void printInOrder()

    private int height( BinaryNode<T> t )
    {
        return t == null ? -1 : t.height;
    }
}
