/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Complex;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author 1
 */
public class Complex {
    private double re;
    private double im;
    private static int all = 10;
    private static int rest = 7;
    
    //Constructors
    public Complex() {
        re = 0;
        im = 0;
    }
    
    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }
    
    public Complex(Complex src) {
        re = src.re;
        im = src.im;
    }
    
    // unsafe method
    public static void setFormat(int all, int rest) {
        Complex.all = all;
        Complex.rest = rest;
    }
    
    public static double[] toDouble(Complex[] source) {
        double[] result = new double[source.length];
        for (int i = 0; i < source.length; i ++) {
            result[i] = source[i].re;
        }
        return result;
    }
    
    @Override
    public String toString() {
        double r = new BigDecimal(re).setScale(rest, RoundingMode.HALF_UP).doubleValue();
        double i = new BigDecimal(im).setScale(rest, RoundingMode.HALF_UP).doubleValue();
        String format = "(%" + all + "." + rest + "f, %" + all + "." + rest + "f)"; 
        return String.format(format, r, i);
    }
    
    //Getters
    public double getRe() {
        return re;
    }
    
    public double getIm() {
        return im;
    }
    
    public double getMagnitude() {
        return re * re + im * im;
    }
    
    public Complex getConjugate() {
        return new Complex(re, -1 * im);
    }
    
    
    //Setters
    public void setRe(double re) {
        this.re = re;
    }
    
    public void setIm(double im) {
        this.im = im;
    }
    
    public void setBoth(double re, double im) {
        this.re = re;
        this.im = im;
    }
    
    public void setBoth(Complex c) {
        this.re = c.re;
        this.im = c.im;
    }
    
    //Basic operations
    public Complex add(Complex op) {
        return new Complex(re + op.re, im + op.im);
    }
    
    public Complex addIn(Complex op) {
        re += op.re;
        im += op.im;
        return this;
    }
    
    public Complex sub(Complex op) {
        return new Complex(re - op.re, im - op.im);
    }
    
    public Complex subIn(Complex op) {
        re -= op.re;
        im -= op.im;
        return this;
    }
    
    public Complex mult(Complex op) {
        return new Complex(re * op.re - im * op.im, re * op.im + im * op.re);
    }
    
    public Complex mult(double re, double im) {
        return new Complex(this.re * re - this.im * im, this.re * im + this.im * re);
    }
    
    public void multIn(Complex op) {
        double r = re * op.re - im * op.im;
        double i = re * op.im + im * op.re;
        this.re = r;
        this.im = i;
    }
    
    public void multIn(double re, double im) {
        double r = this.re * re - this.im * im;
        double i = this.re * im + this.im * re;
        this.re = r;
        this.im = i;
    }
    
    public Complex div(Complex op) {
        double nre, nim;
        double denom = op.re * op.re + op.im * op.im;
        nre = (re * op.re + im * op.im) / denom;
        nim = (re * op.im - im * op.re) / denom;
        return new Complex(nre, nim);
    }
    
    public Complex divIn(Complex op) {
        double denom = op.re * op.re + op.im * op.im;
        re = (re * op.re + im * op.im) / denom;
        im = (re * op.im - im * op.re) / denom;
        return this;
    }
    
    public void conjugation() {
        im = -1 * im;
    }
    
    
}