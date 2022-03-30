/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 *Esta clase contiene atributos para guardar un punto2D
 * @author Juan Camilo Hidalgo Betancourth 2205622
 * @author Andrés Felipe Hidalgo Betancourth 2205621
 * @author Alejandro Gomez Llanos 2201157
 * @author Diego Fernando Rangel Lopez
 * date 28 Marzo 2022
 */
public class Punto2D {
    
    private double x;
    private double y;
    
    public Punto2D(double x,double y){
        this.x = x;
        this.y = y;
    }
    
    
    public Punto2D(){
    
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Punto"+ x+","+y;
    }
    
    
    
}
