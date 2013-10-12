package aads;

import Algorythms.Source.FFT;
import Algorythms.Source.StraightPolinomMultiplier;
import Complex.Complex;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 *
 * @author Shkuratov Ilya
 */
public class TestFFT {
    static final Logger logger = Logger.getLogger(TestSyntacticTree.class.getName());
    static Scanner in = new Scanner(System.in);
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<Double> first = new ArrayList();
        ArrayList<Double> second = new ArrayList();
        boolean close = false;
        
        do {
            int n, j = 0;
            System.out.println("Please input _count_ of coefficients of first polinom.");
            n = in.nextInt();

            System.out.println("Please input _coefficients_ of first polinom.");
            while (j < n && in.hasNextDouble()) {
                first.add(in.nextDouble());
                j ++;
            }

            j = 0;

            System.out.println("Please input _count_ of coefficients of second polinom.");
            n = in.nextInt();
            System.out.println("Please input _coefficients_ of second polinom.");
            while (j < n && in.hasNextDouble()) {
                second.add(in.nextDouble());
                j ++;
            }


            double[] f = new double[first.size()];
            double[] s = new double[second.size()];
            for (int i = 0; i < first.size(); i ++) {
                f[i] = first.get(i).doubleValue();
            }
            for (int i = 0; i < second.size(); i ++) {
                s[i] = second.get(i).doubleValue();
            }
            multiply(f, s);
            
            first.clear();
            second.clear();
            System.out.println("\nType 'q' to quit.");
            System.out.println("Type 'more' to multiply one more pair of polinom.");
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
    
    public static void testFFT() {
        //test fft
        double[] first = {1, 0, -4, 3, 2};
        double[] second = {0, 2, 5, 1, -4, 10};
        multiply(first, second);
    }
        
    public static void multiply(double[] first, double[] second) {
        
        double[] result1 = StraightPolinomMultiplier.Multiply(first, second);
        double[] result2 = Complex.toDouble(FFT.mult(first, second));
        
        System.out.println("Straight multiply:");
        for (double d : result1) { System.out.printf("%3.1f ", d); }
        System.out.println();
        System.out.println("FFT multiply:");
        for (double d : result2) { System.out.printf("%3.1f ", d); }
        System.out.println();
        
    }
}
