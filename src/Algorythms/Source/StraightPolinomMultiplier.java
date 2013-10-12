package Algorythms.Source;

/**
 *
 * @author Ilya Shkuratov
 */
public class StraightPolinomMultiplier {
    
    static public double[] Multiply(double[] first, double[] second) {
        double[] result = new double[first.length + second.length - 1];
        for (int i = 0; i < first.length; i ++) {
            for (int j = 0; j < second.length; j ++) {
                result[i + j] += first[i] * second[j];
            }
        }
        return result;
    }
    
}
