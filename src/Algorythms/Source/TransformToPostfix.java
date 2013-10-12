package Algorythms.Source;

import Algorythms.Exceptions.IllegalCharacterException;
import Algorythms.Exceptions.IllegalSignPositionException;
import Algorythms.Exceptions.StackIsEmptyException;
import Algorythms.Structures.ListStack;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ilys Shkuratov
 */
public class TransformToPostfix {
    static private ListStack<String> stack = new ListStack();
    static private State state = State.Start;
    static private Input input;
    static private int position;
    static private boolean isnegative = false;
    static private ArrayList<String> result = new ArrayList();
    static private StringBuilder sb = new StringBuilder();
    
    /**
     *
     * @param expression - the source expression, that will be transformed
     *      into postfix form
     * @return String representation expression in postfix notation
     * @throws IllegalCharacterException    - if the character placed 
     *      in non-suitable place
     * @throws IllegalSignPositionException - if the sign placed 
     *      in non-suitable place
     */
    static public String[] tranform(String expression) 
            throws IllegalCharacterException, IllegalSignPositionException {
        int p, i = 0;
        position = 0;
        try {
            while( i < expression.length() ) {
                p = expression.codePointAt(i);
                if (Character.isSupplementaryCodePoint(p)) i += 2;
                else i ++;
                position ++;
                setInput(p);
                switch (state) {
                    case Start:
                        StartAction(p);
                        break;
                    case Number:
                        NumberAction(p);
                        break;
                    case Operator:
                        OperatorAction(p);
                        break;
                    case Bracket:
                        BracketAction(p);
                        break;
                    default:
                        break;
                }
            }
            addResult();
            while (!stack.isempty()) result.add(stack.pop());
        } catch (StackIsEmptyException ex) {
            Logger.getLogger(TransformToPostfix.class.getName()).log(Level.SEVERE, null, ex.toString());
        }
        String[] s = new String[result.size()];
        s = result.toArray(s);
        clear();
        return s;
    }
    
    
    
    /*
     * State Machine action methods.
     */
    
    static private void StartAction(int p) throws IllegalSignPositionException {
        switch (input) {
            case NUM:
                state = State.Number;
                sb.append(Character.toChars(p));
                break;
            case BRKT:
                state = State.Bracket;
                if (Character.toChars(p)[0] == '(') {
                    stack.push("(");
                } else {
                    clear();
                    throw new IllegalSignPositionException("Open bracket "
                            + "mustn't be at first place in expression");
                }
                break;
            case OP:
                clear();
                throw new IllegalSignPositionException("Sign of operation" +
                        "mustn't be at first place in expression");
            default:
                break;
        }
    }
    
    static private void NumberAction(int p) throws IllegalSignPositionException, StackIsEmptyException {
        switch (input) {
            case NUM:
                if (isnegative) {
                    isnegative = false;
//                    sb.append('(');
                    sb.append('-');
//                    sb.append(')');
                }
                sb.append(Character.toChars(p));
                break;
            case BRKT:
                state = State.Bracket;
                if (Character.toChars(p)[0] == '(') {
                    clear();
                    throw new IllegalSignPositionException(
                        String.format("Brackets mustn't go after number", position));
                } else {
                    addResult();
                    while (!stack.isempty() && !stack.top().equals("(")) {
                        result.add(stack.pop());
                    }
                    if (!stack.isempty()) stack.pop();
                }
                break;
            case OP:
                addResult();
                state = State.Operator;
                putOperation(Character.toChars(p)[0]);
                break;
            default:
                break;
        }
    }
    
    private static void OperatorAction(int p) throws IllegalSignPositionException {
        switch (input) {
            case NUM:
                state = State.Number;
                sb.append(Character.toChars(p));
                break;
            case BRKT:
                state = State.Bracket;
                if (Character.toChars(p)[0] == '(') {
                    stack.push("(");
                } else {
                    clear();
                    throw new IllegalSignPositionException(
                            String.format("Close bracket mustn't go "
                            + "after operation. Position: %d", position));
                }
                break;
            case OP:
                clear();
                throw new IllegalSignPositionException(
                        String.format("Two operations mustn't be in a row. "
                        + "Position: %d", position));
            default:
                break;
        }
    }
    
    private static void BracketAction(int p) 
            throws IllegalSignPositionException, StackIsEmptyException {
        switch (input) {
            case NUM:
                if ("(".equals(stack.top())) {
                    state = State.Number;
                    sb.append(Character.toChars(p));
                } else {
                    clear();
                    throw new IllegalSignPositionException(
                            String.format("Operation sign has missed "
                            + "at position %d", position));
                }
                break;
            case BRKT:
                if (Character.toChars(p)[0] == '(') {
                    stack.push("(");
                } else {
                    while(!"(".equals(stack.top()))
                        result.add(stack.pop());
                    stack.pop();
                }
                break;
            case OP:
                state = State.Operator;
                if (!stack.isempty() && "(".equals(stack.top())) {
                    // reading negative value
                    if (Character.toChars(p)[0] == '-') {
                        state = State.Number;
                        isnegative = true;
                    } else {
                        clear();
                        throw new IllegalSignPositionException(
                            String.format("Missing operand before "
                            + "operation at position: %d", position));
                    }
                } else {
                    putOperation(Character.toChars(p)[0]);
                }
                break;
            default:
                break;
        }
    }
    
    
    
    /*
     * Auxiliary methods.
     */
    
    
    /**
     *
     * @param c - character, that will be investigated
     * @return 
     *      0 - if 'c' is not a operator
     *      1 - if 'c' is '-' or '+'
     *      2 - if 'c' is '/' or '*'
     */
    static public int isOperator(char c) {
        if (c == '/' || c == '*') return 2;
        if (c == '-' || c == '+') return 1;
        return 0;
    }
    
    static public int isOperator(String s) {
        if (s.equals("/") || s.equals("*")) return 2;
        if (s.equals("-") || s.equals("+")) return 1;
        return 0;
    }
    
    static private void setInput(int c) throws IllegalCharacterException {
        char temp = Character.toChars(c)[0];
        if (Character.isDigit(c)) {
            input = Input.NUM;
        } else if (temp == '/' || temp == '*' || temp == '+' || temp == '-') {
            input = Input.OP;
        } else if (temp == '(' || temp == ')') {
            input = Input.BRKT;
        } else {
            clear();
            throw new IllegalCharacterException(
                    String.format("Illegal character at %d position", position));
        }
    }
    
    private static void putOperation(char c) throws StackIsEmptyException {
        int priority = isOperator(c);
        if (stack.isempty() || "(".equals(stack.top()))  
            stack.push(Character.toString(c));
        else if (priority == 2 && isOperator(stack.top()) == 1)
            stack.push(Character.toString(c));
        else if (priority == 2 && isOperator(stack.top()) == 2) {
            while (!stack.isempty() && !"(".equals(stack.top()) && isOperator(stack.top()) != 1)
                result.add(stack.pop());
            stack.push(Character.toString(c));
        } else if (priority == 1) {
            while(!stack.isempty() && !"(".equals(stack.top()))
                result.add(stack.pop());
            stack.push(Character.toString(c));
        }
    }
    
    private static void addResult() {
        if (sb.length() > 0) result.add(sb.toString());
        sb.delete(0, sb.length());
    } 
    
    private static void clear() {
        input = Input.NUM;
        state = State.Start;
        stack.clear();
        result.clear();
        sb.delete(0, sb.capacity());
    }
    
    
    private static enum State {
        Start, Number, Operator, Bracket 
    }
    
    private static enum Input {
        NUM, BRKT, OP
    }
    
}
