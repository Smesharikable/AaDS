package aads;

import Algorythms.Exceptions.IllegalCharacterException;
import Algorythms.Exceptions.IllegalSignPositionException;
import Algorythms.Exceptions.StackIsEmptyException;
import Algorythms.Exceptions.TreeIsEmptyException;
import Algorythms.Source.SyntacticTree;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ilya Shkuratov
 */
public class TestSyntacticTree {
    static final Logger logger = Logger.getLogger(TestSyntacticTree.class.getName());
    static Scanner in = new Scanner(System.in);
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean close = false;
        
        do {
            System.out.println("Please, input desired expression without spaces.");
            String expr = in.nextLine();
            System.out.println("Input 'show' to see how stack is changed.");
            System.out.println("Press Enter in other case.");
            boolean show = (in.nextLine().equals("show"));

            SyntacticTree st = new SyntacticTree();
            try {
                tryStree(st, expr, show);
            } catch (IllegalCharacterException ex) {
                logger.log(Level.SEVERE, null, ex);
            } catch (IllegalSignPositionException ex) {
                logger.log(Level.SEVERE, null, ex);
            } catch (TreeIsEmptyException ex) {
                logger.log(Level.SEVERE, null, ex);
            } catch (StackIsEmptyException ex) {
                logger.log(Level.SEVERE, null, ex);
            }
            
            System.out.println("\nType 'q' to quit.");
            System.out.println("Type 'more' parse one more expression.");
            while (in.hasNext()){
                String input = in.nextLine();
                if (input.equals("q")) {
                    in.close();
                    close = true;
                    break;
                }
                if (input.endsWith("more")) break;
            }
        } while (!close);
        in.close();
    }
    
    public static void testTree() {
        // test parsing expression and computing it in postfix form
        try {
            
            SyntacticTree stree = new SyntacticTree();
            
            tryStree(stree, "1+2/3", false);
            stree.printTree(stree.getRoot());
            
            tryStree(stree, "(1+2)/3", false);
            stree.printTree(stree.getRoot());
            
            tryStree(stree, "(1+2/3)*4-5/6*(-1)", false);
            stree.printTree(stree.getRoot());
            
            tryStree(stree, "3+2-1", false);
            stree.printTree(stree.getRoot());
            
        } catch (TreeIsEmptyException ex) {
            logger.log(Level.SEVERE, null, ex);
        } catch (StackIsEmptyException ex) {
            logger.log(Level.SEVERE, null, ex);
        } catch (IllegalCharacterException ex) {
            logger.log(Level.SEVERE, null, ex);
        } catch (IllegalSignPositionException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }    
    
    public static void tryStree(SyntacticTree stree, String expression, boolean show) 
            throws IllegalCharacterException, 
            IllegalSignPositionException,
            TreeIsEmptyException,
            StackIsEmptyException {
        
        stree.parseExpression(expression);
        
        System.out.println("Tree:");
        stree.printTree(stree.getRoot());
        System.out.println();
        
        System.out.println("InfixTravers:");
        System.out.println(stree.infixTraverse());
        System.out.println();
        System.out.println("PostfixTravers:");
        System.out.println(stree.postfixTraverse());
        System.out.println();
        System.out.println("PrefixTravers:");
        System.out.println(stree.prefixTraverse());
        System.out.println();
        
        System.out.println("Compute expression with ListStack:");
        System.out.println("result: " + stree.compute(true, show));
        System.out.println();
        System.out.println("Compute expression with ArrayStack:");
        System.out.println("result: " + stree.compute(false, show));
    
    }
    
    
    
}
