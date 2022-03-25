/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import modelo.Punto2D;

/**
 *
 * @author andres
 */
public class FXMLDocumentController implements Initializable {
    double coordenadaX,coordenadaY;
    double cX;
    double cY;
    double o;
    double a;
    double[] xx;
    double[] yy;
    GraphicsContext g;
    @FXML
    ColorPicker colorRelleno, colorBorde;

    @FXML
    RadioButton RadioButton;
    
    @FXML
    Slider sliderBorde,sliderRadio;

    @FXML
    private Canvas lienzo;
    @FXML
    private RadioButton relleno, borde;

    @FXML
    private void obtenerCoordenadas(MouseEvent event) {
        coordenadaX = event.getX();
        coordenadaY = event.getY();
//
        Punto2D objp = new Punto2D(coordenadaX, coordenadaY);

        System.out.println("Punto " + objp.toString());
    }
    public void ngon(double x,double y,double r,int n){
        if(borde.isSelected()){
            g.setStroke(colorBorde.getValue());
            g.setLineWidth(sliderBorde.getValue());
        }
        
                
        xx = new double[n];
        yy = new double[n];
        
        a = 2 * Math.PI/n;
        
        o = 0;//inicializar el angulo 
        
        
        for (int i = 0; i < n; i++) {
            
            cX = Math.cos(o);
            cY = Math.sin(o);
            
            xx[i] = x + cX*r;
            yy[i] = y + cY*r;
            
            o += a;
                
        }
        if(!relleno.isSelected() && borde.isSelected()){
            g.strokePolygon(xx, yy, n);
        }else if(relleno.isSelected() && !borde.isSelected()){
            g.fillPolygon(xx, yy, n);
        }else{
            g.strokePolygon(xx, yy, n);
            g.fillPolygon(xx, yy, n);
        }
        
        
    }
    @FXML
    private void activarBoton(ActionEvent event) {
        if(relleno.isSelected()){
            colorRelleno.setDisable(false);
        }else{
            g.setFill(colorRelleno.getValue());
            colorRelleno.setDisable(true);
        }
        
        if(borde.isSelected()){
          colorBorde.setDisable(false);
        }else{
            colorBorde.setDisable(true);
        }
    }

    @FXML
    private void hexagono(ActionEvent event) {
        ngon(coordenadaX,coordenadaY,65,6);
    }

    @FXML
    private void heptagono(ActionEvent event) {
        ngon(coordenadaX,coordenadaY,65,7);
    }

    @FXML
    private void octagono(ActionEvent event) {
        ngon(coordenadaX,coordenadaY,65,8);
    }

    @FXML
    private void decagono(ActionEvent event) {
        ngon(coordenadaX,coordenadaY,65,10);
    }

    @FXML
    private void curva(ActionEvent event) {

    }

    @FXML
    private void pacman(ActionEvent event) {

    }
    
    @FXML
    private void estrella5(ActionEvent event) {

    }
    @FXML
    private void estrella6(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        g = lienzo.getGraphicsContext2D();
        double w = lienzo.getWidth();
        double h = lienzo.getHeight();
        g.setStroke(Color.BLACK);
        g.setLineWidth(0.5);
        g.strokeRect(0, 0, w, h);
        
        colorRelleno.setDisable(true);
        
    }

}
