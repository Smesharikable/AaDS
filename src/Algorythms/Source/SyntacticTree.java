package Algorythms.Source;

import Algorythms.Exceptions.IllegalCharacterException;
import Algorythms.Exceptions.IllegalOperandException;
import Algorythms.Exceptions.IllegalOperatorException;
import Algorythms.Exceptions.IllegalSignPositionException;
import Algorythms.Exceptions.StackIsEmptyException;
import Algorythms.Exceptions.StackOverflowException;
import Algorythms.Exceptions.TreeIsEmptyException;
import Algorythms.Interfaces.Node;
import Algorythms.Interfaces.Stack;
import Algorythms.Structures.ArrayStack;
import Algorythms.Structures.BinaryTree;
import Algorythms.Structures.BinaryTree.Operand;
import Algorythms.Structures.BinaryTree.Operation;
import Algorythms.Structures.ListStack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ilys Shkuratov
 */
public class SyntacticTree {
    
    // Stack for building tree from expression in posfix notation
    ListStack<Node<String>> stack;
    
    // Stack for expression in infix notation
    ListStack<Pair> tstack;
    
    // Stack for computing parsed expression
    Stack<Double> cstack;
    double  temp;
    
    // tree contained the parsed expression
    BinaryTree<String> tree;
    
    boolean showstack = false;
    StringBuilder sb = new StringBuilder();

    
    /**
     * Creates an empty tree
     */
    public SyntacticTree() {
        tstack = new ListStack();
        stack = new ListStack();
    }
    
    /**
     * Prints tree
     * @param node the root of subtree, which will be printed
     */
    public void printTree(Node<String> node) {
        print(node, 1);
    }
    
    /**
     * 
     * @return the root of current tree
     */
    public Node<String> getRoot() {
        return tree.getRoot();
    }
    
    private void print(Node<String> node, int depth) {
        if(node.getRight() != null) print(node.getRight(), depth + 1);
        if (node.getClass() == Operand.class && !((Operand) node).isPositive() ) {
            String s = "%" + depth * 4 + "s";
            System.out.printf(s + "\n", "(-" + node.getValue() + ")");
        } else {
            String s = "%" + depth * 4 + "s";
            System.out.printf(s + "\n", node.getValue());
        }
        if(node.getLeft() != null) print(node.getLeft(), depth + 1);
    }
    
    
    /*
     * Parse specified expression and tranform it into tree form
     */
    public void parseExpression(String exspression) 
            throws IllegalCharacterException, 
            IllegalSignPositionException {
        String[] s = TransformToPostfix.tranform(exspression);
        if (TransformToPostfix.isOperator(s[s.length - 1]) == 0) 
            throw new IllegalCharacterException("The end of expression must be a Operator");
        tree = new BinaryTree(s[s.length - 1]);
        stack.clear();
        stack.push(tree.getRoot());
        int j, i = s.length - 2;
        boolean isPositive = true;
        Operation node;
        try {
            while (i > -1) {
                // we can meet ')' in postfix notation only if there is a negative value
                if ('-' == s[i].charAt(0)) {
                    isPositive = false;
//                    i --;
                }
                j = TransformToPostfix.isOperator(s[i]);
                node = (Operation) stack.top();
                if (node.getRight() == null) {
                    if (j != 0) {
                        node.setRight(s[i]);
                        stack.push(node.getRight());
                    } else {
                        if (!isPositive) s[i] = s[i].substring(1);
                        node.setRightNode( tree.new Operand<String>(s[i], isPositive, true) );
//                        sb.delete(0, sb.length());
                    }
                } else {
                    stack.pop();
                    if (j != 0) {
                        node.setLeft(s[i]);
                        stack.push(node.getLeft());
                    } else {
                        if (!isPositive) s[i] = s[i].substring(1);
                        node.setLeftNode( tree.new Operand<String>(s[i], isPositive, false) );
//                        sb.delete(0, sb.length());
                    }
                }
                if (!isPositive) {
                    isPositive = true;
                }
                i --;
            }
        } catch (StackIsEmptyException ex) {
            Logger.getLogger(SyntacticTree.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    //Calculation methods
    
    public double compute(boolean withList, boolean show) 
            throws TreeIsEmptyException, StackIsEmptyException {
        if (tree.getRoot() == null) throw new TreeIsEmptyException();
        if (withList)
            cstack = new ListStack<Double>();
        else
            cstack = new ArrayStack<Double>(Double.class);
        showstack = show;
        postfixCompute(tree.getRoot());
        return cstack.top();
    }
    
    private void postfixCompute(Node<String> node) {
        if (node.getLeft() != null) postfixCompute(node.getLeft());
        if (node.getRight() != null) postfixCompute(node.getRight());
        if (node.getClass() == Operand.class) {
            try {
                // transform character representation into double represantation
                temp = Double.parseDouble(node.getValue().toString());
                // if value is negative, multiplying it on -1
                if (!((Operand) node).isPositive()) temp *= -1;
                cstack.push(temp);
                if (showstack) System.out.println(cstack);
            } catch (StackOverflowException ex) {
                Logger.getLogger(SyntacticTree.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                cstack.push(doOperation(cstack.pop(), cstack.pop(), node.getValue().toCharArray()[0]));
                if (showstack) System.out.println(cstack);
            } catch (IllegalOperandException ex) {
                Logger.getLogger(SyntacticTree.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalOperatorException ex) {
                Logger.getLogger(SyntacticTree.class.getName()).log(Level.SEVERE, null, ex);
            } catch (StackOverflowException ex) {
                Logger.getLogger(SyntacticTree.class.getName()).log(Level.SEVERE, null, ex);
            } catch (StackIsEmptyException ex) {
                Logger.getLogger(SyntacticTree.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private double doOperation(double second, double first, char op) 
            throws IllegalOperandException, IllegalOperatorException {
        if (op == '+') {
            return first + second;
        } else if (op == '-'){
            return first - second;
        } else if (op == '/') {
            if (second == 0) throw new IllegalOperandException();
            return first / second;
        } else if (op == '*') {
            return first * second;
        }
        throw new IllegalOperatorException();
    }
    
    
    // Section traversal methods
    
    public String infixTraverse() {
        infix(tree.getRoot());
        try {
            return tstack.pop().value.toString();
        } catch (StackIsEmptyException ex) {
            Logger.getLogger(SyntacticTree.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public String prefixTraverse() {
        StringBuilder sb = new StringBuilder();
        prefix(tree.getRoot(), sb);
        return sb.toString();
    }
    
    public String postfixTraverse() {
        StringBuilder sb = new StringBuilder();
        postfix(tree.getRoot(), sb);
        return sb.toString();
    }
    
    
    private void infix(Node<String> node) {
        if (node.getLeft() != null) infix(node.getLeft());
        if (node.getRight() != null) infix(node.getRight());
        if ( TransformToPostfix.isOperator(node.getValue()) != 0 ) {
            try {
                Pair first = tstack.pop();
                Pair second = tstack.pop();
                int p = TransformToPostfix.isOperator(node.getValue());
                if (first.priority < p) {
                    first.value.insert(0, '(');
                    first.value.append(')');
                }
                if (second.priority < p) {
                    second.value.insert(0, '(');
                    second.value.append(')');
                }
                tstack.push(
                        new Pair(second.value.append(node.getValue()).append(first.value), p) 
                        );
            } catch (StackIsEmptyException ex) {
                Logger.getLogger(SyntacticTree.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            StringBuilder nvalue = new StringBuilder(String.valueOf(node.getValue()));
            if ( !((Operand) node).isPositive() ) {
                nvalue.insert(0, "(-").append(')'); 
            } 
            tstack.push( new Pair(nvalue, 3) );
        }
    }
    
    private void prefix(Node<String> node, StringBuilder sb) {
        // if current node contanes operand and it's negative
        if (node.getClass() == Operand.class && !((Operand) node).isPositive() ) {
            sb.append("(-").append(node.getValue()).append(')');
        } else
            sb.append(node.getValue());
        if (node.getLeft() != null) prefix(node.getLeft(), sb);
        if (node.getRight() != null) prefix(node.getRight(), sb);
    }
    
    private void postfix(Node<String> node, StringBuilder sb) {
        if (node.getLeft() != null) postfix(node.getLeft(), sb);
        if (node.getRight() != null) postfix(node.getRight(), sb);
        // if current node contanes operand and it's negative
        if (node.getClass() == Operand.class && !((Operand) node).isPositive() ) {
            sb.append("(-").append(node.getValue()).append(')');
        } else
            sb.append(node.getValue());
    }
    
    
    // Auxiliary inner classes
    
    /*
     * Class for building expression in infix notation
     */
    private class Pair {
        StringBuilder value;
        int priority;
        
        public Pair(String value, int priority) { 
            this.value = new StringBuilder(value);
            this.priority = priority;
        }
        
        public Pair(StringBuilder value, int priority) { 
            this.value = new StringBuilder(value.toString());
            this.priority = priority;
        }
    }
    
}
