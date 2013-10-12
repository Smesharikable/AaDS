package aads;

import Algorythms.Structures.ArrayTree;
import Algorythms.Structures.NeedledTree;
import java.util.Scanner;

/**
 *
 * @author Ilya Shkuratov
 */
public class TestNeedledTree {
    private static Scanner in = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("Type 'Default' to see default example.");
        System.out.println("Input ");
        
        String[] srt = ArrayTreeTravers.createValue("ABCD EF     GHI");
        ArrayTree art = new ArrayTree(srt);
        NeedledTree<String> nd = new NeedledTree(art);
        nd.print(nd.getRoot());
    }
    
}
