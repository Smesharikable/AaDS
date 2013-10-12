package aads;

import Algorythms.Structures.ArrayTree;
import Algorythms.Structures.NeedledTree;
import java.util.Scanner;

/**
 *
 * @author Shkuratov Ilya
 */
public class ArrayTreeTravers {
    static private Scanner scanner = new Scanner(System.in);
        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //String[] values = createValue("12 45  8");
        boolean close = false;
        
        do {
            String[] values;
            System.out.println("Please, input number of nodes, including empty nodes.");
            System.out.println("Input 0 to see default example");

            int n = scanner.nextInt();
            if (n == 0) {
                values = ArrayTreeTravers.createValue("ABCD EF     GHI");
            } else {
                values = new String[n];
                int j = 0;
                System.out.println("Please, input nodes value.");
                System.out.println("Type \"\" to input empty node.");
                while (j < n && scanner.hasNext()) {
                    values[j] = scanner.next();
                    System.out.println("value is : " + values[j]);
                    j ++;
                }

                for (int i = 0; i < values.length; i++) {
                    if (values[i].equals("\"\"")) values[i] = "";
                    System.out.print(values[i] + ", ");
                }
            }
            System.out.println();

            ArrayTree art = new ArrayTree(values);
            System.out.println();
            System.out.println(art);
            System.out.println("\nIn-order travers:");
            travers(art, 0);

            art.sealing();
            System.out.println("\nSealed tree");
            System.out.println(art);
            System.out.println("\nIn-order travers:");
            travers(art, 0);

            System.out.println("\nType 'sew' to create a NeedledTree, based on this ArrayTree");
            System.out.println("Or type 'q', to quit or 'more' to inpur another tree");
            String s;
            while (scanner.hasNext()) {
                s = scanner.nextLine();
                if (s.equals("sew")) {
                    NeedledTree<String> nd = new NeedledTree(art);
                    nd.print(nd.getRoot());
                    System.out.println("Type 'q', to quit or 'more' to inpur another tree");
                }
                if (s.equals("q")) {
                    scanner.close();
                    close = true;
                    break;
                }
                if (s.equals("more")) {
                    break;
                }
            }
        } while (!close);
    }
    
    public static String[] createValue(String src) {
        String[] result = new String[src.length()];
        char[] temp = src.toCharArray();
        for (int i = 0; i < result.length; i++) {
            if (Character.isSpaceChar(temp[i])) {
                result[i] = "";
            } else {
                result[i] = String.valueOf(temp[i]);
            }
        }
        return result;
    }
    
    public static void travers(ArrayTree art, int index) {
        int size = art.getSize();
        int temp;
        if (index >= size) return;
        temp = art.left(index);
        if (temp < size && temp != 0 && !art.getValue(temp).isEmpty())
            travers(art, temp);
        System.out.println(art.getValue(index));
        temp = art.right(index);
        if (temp < size && temp != 0 && !art.getValue(temp).isEmpty())
            travers(art, temp);
    }
}
