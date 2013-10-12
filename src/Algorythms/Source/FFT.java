package Algorythms.Source;

import Complex.Complex;
import java.util.Arrays;

/**
 *
 * @author Ilya Shkuratov
 */
public class FFT {
    
    public static Complex[] fftRecursive(Complex[] coefficients) {
        int n = coefficients.length;
        
        if (n == 1) { return coefficients; }
        
        Complex[] result = new Complex[n];
        Complex[] roots = new Complex[n];
        Complex[] even = new Complex[n / 2];
        Complex[] odd = new Complex[n / 2];
        
        int i;
        double arg, mult = 2 * Math.PI / n;
        for (i = 0; i < n; i ++) {
            arg = mult * i;
            roots[i] = new Complex(Math.cos(arg), Math.sin(arg));
        }
        
        for (i = 0; i < n / 2; i ++) {
            even[i] = coefficients[2 * i];
            odd[i] = coefficients[2 * i +  1];
        }
        
        even = fftRecursive(even);
        odd =  fftRecursive(odd);
        for (i = 0; i < n; i ++) {
            result[i] = even[i % (n / 2)].add(roots[i].mult(odd[i % (n / 2)]));
            //System.out.println(even[i % (n / 2)] + " " + odd[i % (n / 2)]);
        }
        //System.out.println();
        
        return result;
    }
    
    public static Complex[] fftRev(Complex[] coeff) {
        int n = coeff.length;
        Complex[] result = fftRecursive(coeff);
        
        for (Complex c : result) System.out.println(c);
        System.out.println();
        
        Complex buf = new Complex();
        Complex denom = new Complex(n, 0);
        for (int i = 0; i < n / 2; i ++) {
            buf.setBoth(result[n - i - 1]);
            result[n - i - 1].setBoth(result[i].divIn(denom));
            result[i].setBoth(buf.divIn(denom));
        }
        return result;
    }
    
    
    public static Complex[] fft(Complex[] coeff, boolean reverse) {
        int i, j, n = coeff.length;
        int half = n / 2;
        int[] add = {half, -half / 2};
        int[] pos = new int[n];
        double mult = 2 * Math.PI / n;
        double temp;
        
        if (reverse) mult *= -1;
        
        Complex[] roots = new Complex[n];
        Complex[] buf = new Complex[n];
        
        // computing roots of 1
        for (i = 0; i < n; i ++) {
            temp = mult * i;
            roots[i] = new Complex(Math.cos(temp), Math.sin(temp));
        }
        
        // split coefficients        
        pos[0] = 0;
        buf[0] = new Complex(coeff[0]);
        i = 2;
        int offset = 0;
        int or = 0;
        for (j = 1; j < n; j ++) {
            if ((j & (j - 1)) == 0) { 
                offset = j;
                or = n / i;
                i <<= 1; 
                pos[j] = or;
            } else {
                pos[j] = or | pos[j - offset];
            }
            buf[j] = new Complex(coeff[pos[j]]);
        }
        
        System.out.println("\nStart coefficeints:");
        for (Complex c : buf) System.out.println(c);
        System.out.println();
        
        // calculate polinom value with merging
        /*
         * block - size of coefficient group for merging
         */
//        Complex[] nextlvl = new Complex[n];
//        for (i = 0; i < n; i++) nextlvl[i] = new Complex();
        
        System.out.println("\nRadix-2 butterfly");
        Complex t, t2;
        int rstep, end;
        for (int block = 1; block < n; block <<= 1) {
            rstep = n / (block * 2);
            for (i = 0; i < n; i += block) {
                for (j = 0; j < block; j ++) {
                    end = i + block;
                    t = roots[j * rstep].mult(buf[end]);  
                    t2 = buf[i].sub(t);
                    buf[i].addIn(t);
                    buf[end].setBoth(t2);
                    System.out.println(buf[i] + " " + buf[end]);
                    i ++;
                }
            }
            System.out.println();
        }
        
        if (reverse) {
            t = new Complex(n, 0);
            for (i = 0; i < n; i ++) {
                buf[i].divIn(t);
            }
        }
        
        return buf;
    }
    
    
    public static Complex[] mult(double[] first, double[] second) {
        return mult(RealToComplex(first), RealToComplex(second));
    }
    
    public static Complex[] mult(Complex[] first, Complex[] second) {
        int degree = first.length + second.length - 1;
        int flen = first.length;
        int slen = second.length;
        
        // expansion arrays to suitable size
        first = Arrays.copyOf(first, degree);
        Arrays.fill(first, flen, first.length, new Complex());
        
        second = Arrays.copyOf(second, degree);
        Arrays.fill(second, slen, second.length, new Complex());
        
        // addition to the degree of two and using fast Fourier transform
        first = fft(UpToDegree(first), false);
        second = fft(UpToDegree(second), false);
        
        // creation of resulting vector and multiplying source vectors
        Complex[] result = new Complex[first.length];
        for (int i = 0; i < first.length; i ++)
            result[i] = new Complex(first[i].mult(second[i]));
        
        return fft(result, true);
    }
    
    
    public static Complex[] RealToComplex(double[] coeff) {
        //coeff = Arrays.copyOf(coeff, completeToDegree(coeff.length));
        Complex[] result = new Complex[coeff.length];
        for (int i = 0; i < coeff.length; i ++) {
            result[i] = new Complex(coeff[i], 0);
        }
        return result;
    }
    
    public static Complex[] UpToDegree(Complex[] pol) {
        int n = completeToDegree(pol.length);
        if (n > pol.length) {
            pol = Arrays.copyOf(pol, completeToDegree(n = pol.length));
            Arrays.fill(pol, n, pol.length, new Complex());
        }
        return pol;
    }
    
    private static int completeToDegree(int n) {
        int result = 1;
        while (result < n) result <<= 1;
        return result;
    }
    
    
}
