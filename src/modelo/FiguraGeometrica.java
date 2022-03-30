/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.LinkedList;
import javafx.scene.paint.Color;

/**
 *
 *Esta clase se tienen todos los atributos de una figura geométrica
 * @author Juan Camilo Hidalgo Betancourth 2205622
 * @author Andrés Felipe Hidalgo Betancourth 2205621
 * @author Alejandro Gomez Llanos 2201157
 * @author Diego Fernando Rangel Lopez
 * date 28 Marzo 2022
 */
public class FiguraGeometrica {
    
    private String nombre;
    private String colorBorde;
    private String ColorRelleno; 
    private LinkedList<Punto2D> LosPuntos;
    private ColorValue fillValue;
    private ColorValue strokeValue;
    private double lineW;

    public FiguraGeometrica(String nombre, String colorBorde, String ColorRelleno, LinkedList<Punto2D> LosPuntos, ColorValue fillValue, ColorValue strokeValue, double lineW) {
        this.nombre = nombre;
        this.colorBorde = colorBorde;
        this.ColorRelleno = ColorRelleno;
        this.LosPuntos = LosPuntos;
        this.fillValue = fillValue;
        this.strokeValue = strokeValue;
        this.lineW = lineW;
    }
    public FiguraGeometrica(){
    
    }
    /**
     * Get the value of lineW
     *
     * @return the value of lineW
     */
    public double getLineW() {
        return lineW;
    }

    /**
     * Set the value of lineW
     *
     * @param lineW new value of lineW
     */
    public void setLineW(double lineW) {
        this.lineW = lineW;
    }


    /**
     * Get the value of strokeValue
     *
     * @return the value of strokeValue
     */
    public ColorValue getStrokeValue() {
        return strokeValue;
    }

    /**
     * Set the value of strokeValue
     *
     * @param strokeValue new value of strokeValue
     */
    public void setStrokeValue(ColorValue strokeValue) {
        this.strokeValue = strokeValue;
    }


    /**
     * Get the value of fillValue
     *
     * @return the value of fillValue
     */
    public ColorValue getFillValue() {
        return fillValue;
    }

    /**
     * Set the value of fillValue
     *
     * @param fillValue new value of fillValue
     */
    public void setFillValue(ColorValue fillValue) {
        this.fillValue = fillValue;
    }


    /**
     * Get the value of LosPuntos
     *
     * @return the value of LosPuntos
     */
    public LinkedList<Punto2D> getLosPuntos() {
        return LosPuntos;
    }

    /**
     * Set the value of LosPuntos
     *
     * @param LosPuntos new value of LosPuntos
     */
    public void setLosPuntos(LinkedList<Punto2D> LosPuntos) {
        this.LosPuntos = LosPuntos;
    }

    /**
     * Get the value of ColorRelleno
     *
     * @return the value of ColorRelleno
     */
    public String getColorRelleno() {
        return ColorRelleno;
    }

    /**
     * Set the value of ColorRelleno
     *
     * @param ColorRelleno new value of ColorRelleno
     */
    public void setColorRelleno(String ColorRelleno) {
        this.ColorRelleno = ColorRelleno;
    }


    /**
     * Get the value of colorBorde
     *
     * @return the value of colorBorde
     */
    public String getColorBorde() {
        return colorBorde;
    }

    /**
     * Set the value of colorBorde
     *
     * @param colorBorde new value of colorBorde
     */
    public void setColorBorde(String colorBorde) {
        this.colorBorde = colorBorde;
    }


    /**
     * Get the value of nombre
     *
     * @return the value of nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Set the value of nombre
     *
     * @param nombre new value of nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * toHexString.
     *
     * @param color. Este método convierte un objeto tipo color en un valor
     * hexadecimal.
     * @return Un valor hexadecimal.
     */
    public static String toHexString(Color color) {
        int r = ((int) Math.round(color.getRed() * 255)) << 24;
        int g = ((int) Math.round(color.getGreen() * 255)) << 16;
        int b = ((int) Math.round(color.getBlue() * 255)) << 8;
        int a = ((int) Math.round(color.getOpacity() * 255));

        return String.format("#%08X", (r + g + b + a));
    }

}
