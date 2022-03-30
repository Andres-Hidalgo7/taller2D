/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author juank
 */
public class ColorValue {

    private double r;
    private double g;
    private double b;
    private double a;

    public ColorValue(double r, double g, double b, double a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }
    public ColorValue(){
        
    }

    /**
     * Get the value of a
     *
     * @return the value of a
     */
    public double getA() {
        return a;
    }

    /**
     * Set the value of a
     *
     * @param a new value of a
     */
    public void setA(double a) {
        this.a = a;
    }

    /**
     * Get the value of b
     *
     * @return the value of b
     */
    public double getB() {
        return b;
    }

    /**
     * Set the value of b
     *
     * @param b new value of b
     */
    public void setB(double b) {
        this.b = b;
    }

    /**
     * Get the value of g
     *
     * @return the value of g
     */
    public double getG() {
        return g;
    }

    /**
     * Set the value of g
     *
     * @param g new value of g
     */
    public void setG(double g) {
        this.g = g;
    }

    /**
     * Get the value of r
     *
     * @return the value of r
     */
    public double getR() {
        return r;
    }

    /**
     * Set the value of r
     *
     * @param r new value of r
     */
    public void setR(double r) {
        this.r = r;
    }

    @Override
    public String toString() {
        return "ColorValue{" + "r=" + r + ", g=" + g + ", b=" + b + ", a=" + a + '}';
    }

}
