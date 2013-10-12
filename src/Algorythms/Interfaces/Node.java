package Algorythms.Interfaces;

/**
 *
 * @param <E> 
 * @author 1
 */
public interface Node<E> {
    
    public E getValue();
        
    public Node<E> getRight();

    public Node<E> getLeft();

    public void setRight(E value);

    public void setLeft(E value);

}
