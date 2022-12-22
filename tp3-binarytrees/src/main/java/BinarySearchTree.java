public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> implements Tree<T>{
    @Override
    public void add(T data) {
        this.root = add(data, root);
    }

    protected BinaryNode<T> add(T value, BinaryNode<T> curNode) {
        if (curNode == null) {
            return new BinaryNode<> (value);
        }

        Integer compareResult = ((Integer)value).compareTo(curNode.getValue());
        if (compareResult > 0) {
            curNode.right = add(value, curNode.right);
        }
        else if (compareResult < 0) {
            curNode.left = add(value, curNode.left);
        }
        return curNode;
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
        this.root = remove(value, root);
    }

    protected BinaryNode<T> remove(T value, BinaryNode<T> curNode) { // Question: On doit utiliser contains avant ou non?
        if (curNode == null) {
            return null;
        }

        Integer compareResult = ((Integer)value).compareTo(curNode.getValue());
        if (compareResult > 0) {
            curNode.right = remove(value, curNode.right);
        }
        else if (compareResult < 0) {
            curNode.left = remove(value, curNode.left);
        }
        else if (curNode.left == null && curNode.right == null) {
            return null;
        }
        else if (curNode.left != null &&  curNode.right != null) {
            BinaryNode<T> minNode = findMin(curNode);
            curNode.value = minNode.value;
            curNode.right = remove(curNode.value, curNode.right);
            return curNode;
        }
        else {
            curNode = (curNode.left != null) ? curNode.left : curNode.right;
        }
        return null;
    }

    protected BinaryNode<T> findMin(BinaryNode<T> curNode){
        if (curNode == null) {
            return null;
        }
        else if (curNode.left == null) {
            return curNode;
        }
        return findMin(curNode);
    }
}
