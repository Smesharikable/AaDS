package Algorythms.Structures;

import Algorythms.Interfaces.Node;

/**
 *
 * @param <E> 
 * @author Ilya Shkuratov
 */
public class BinaryTree<E> {
    private Node<E> root;
    
    public BinaryTree() {}
    
    public BinaryTree(E value) {
        root = new Operation(value);
    }
    
    public Node<E> getRoot() { return root; }
    
    public class Operation<E> implements Node<E>{
        E value;
        Node<E> left;
        Node<E> right;
        
        public Operation(E value) { this.value = value; }
        
        @Override
        public E getValue() { return value; }
        
        @Override
        public Node<E> getRight() { return right; }
        
        @Override
        public Node<E> getLeft() { return left; }
        
        @Override
        public void setRight(E value) { right = new Operation(value); }
        
        public void setRightNode(Node<E> node) { right = node; }
        
        @Override
        public void setLeft(E value) { left = new Operation(value); }
        
        public void setLeftNode(Node<E> node) { left = node; }
    }
    
    public class Operand<E> implements Node<E>{
        E value;
        // true if this value is negative
        boolean sign;
        boolean right;
        
        public Operand(E value, boolean sign, boolean right) { 
            this.value = value; 
            this.sign = sign; 
            this.right = right;
        }
        
        @Override
        public E getValue() { return value; };

        @Override
        public Node<E> getRight() { return null; }

        @Override
        public Node<E> getLeft() { return null; }

        @Override
        public void setRight(E value) {}

        @Override
        public void setLeft(E value) {}
        
        public boolean isPositive() { return sign; }
        
        public boolean isRight() { return right; }
    }
}
