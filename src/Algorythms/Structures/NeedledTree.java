package Algorythms.Structures;

import Algorythms.Interfaces.Node;

    
/**
 *
 * @author Ilya Shkuratov
 */
public class NeedledTree<T> {
    private NNode<T> head;
    private ArrayTree tree;
    
    public NeedledTree(ArrayTree art) {
        head = new NNode();
        head.left = null;
        head.right = head;
        head.ltag = false;
        head.rtag = false;
        tree = art;
        buildTree();
    }
    
    public NNode getRoot() {
        return head.left;
    }
    
    
    private void buildTree() {
        if (tree.getValue(0) != null) {
            NNode<T> root = new NNode(tree.getValue(0));
            head.left = root;
            int i = tree.left(0);
            if ((i > 0) && !tree.getValue(i).isEmpty()) {
                root.left = new NNode(tree.getValue(i));
                root.ltag = true;
                build(root.left, i, root, head);
            } else {
                root.left = head;
                root.ltag = false;
            }
            i = tree.right(0);
            if ((i > 0) && !tree.getValue(i).isEmpty()) {
                root.right = new NNode(tree.getValue(i));
                root.rtag = true;
                build(root.right, i, head, root);
            } else {
                root.right = head;
                root.rtag = false;
            }
        }
        tree = null;
    } 
    
    private void build(NNode node, int index, NNode lastl, NNode lastr) {
        int i = tree.left(index);
        if ((i > 0) && !tree.getValue(i).isEmpty()) {
            node.left = new NNode(tree.getValue(i));
            node.ltag = true;
            build(node.left, i, node, lastr);
        } else {
            node.left = lastr;
            node.ltag = false;
        }
        i = tree.right(index);
        if ((i > 0) && !tree.getValue(i).isEmpty()) {
            node.right = new NNode(tree.getValue(i));
            node.rtag = true;
            build(node.right, i, lastl, node);
        } else {
            node.right = lastl;
            node.rtag = false;
        }
    }
      
    public void print(NNode start) {
        p(start, 1);
    }
    
    private void p(NNode node, int depth) {
        String s = "%" + depth * 4 + "s";
        String s2 = "%" + (depth + 1) * 4 + "s]";
        
        if(node.rtag == true) p(node.right, depth + 1);
        else if (node.right.value == null)
            System.out.printf(s2 + "\n", "HEAD");
        else 
            System.out.printf(s2 + "\n", node.right.value);
            
        System.out.printf(s + "\n", node.value);
        
        if(node.ltag == true) p(node.left, depth + 1);
        else if (node.left.value == null)
            System.out.printf(s2 + "\n", "HEAD");
        else
            System.out.printf(s2 + "\n", node.left.value);
    }
    
    
    public static class NNode<T> implements Node<T> {
        T value;
        NNode right, left;
        boolean rtag, ltag;
        
        public NNode () {}
        
        public NNode (T value) {
            this.value = value;
        }
        
        @Override
        public T getValue() {
            return value;
        }

        @Override
        public NNode getRight() {
            return right;
        }

        @Override
        public NNode getLeft() {
            return left;
        }

        @Override
        public void setRight(T value) {
            right = new NNode(value);
        }

        @Override
        public void setLeft(T value) {
            left = new NNode(value);
        }
        
    }
    
}
