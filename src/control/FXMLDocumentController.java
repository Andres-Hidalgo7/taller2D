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
    //Variables para el metodo ngon
    double coordenadaX,coordenadaY;
    double cX,cY,o,a;
    double[] xx,yy;
    
    //variables para el metodo estrella5
    double[] x,y;
    GraphicsContext g;
    
    @FXML
    ColorPicker colorRelleno, colorBorde;
    
    @FXML
    Label lRadio;
    
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
            g.setFill(colorRelleno.getValue());
            g.fillPolygon(xx, yy, n);
        }else{
            g.setFill(colorRelleno.getValue());
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
          sliderBorde.setDisable(false);
        }else{
            colorBorde.setDisable(true);
            sliderBorde.setDisable(true);
        }
    }

    @FXML
    private void hexagono(ActionEvent event) {
        ngon(coordenadaX,coordenadaY,sliderRadio.getValue(),6);
    }

    @FXML
    private void heptagono(ActionEvent event) {
        ngon(coordenadaX,coordenadaY,sliderRadio.getValue(),7);
    }

    @FXML
    private void octagono(ActionEvent event) {
        ngon(coordenadaX,coordenadaY,sliderRadio.getValue(),8);
    }

    @FXML
    private void decagono(ActionEvent event) {
        ngon(coordenadaX,coordenadaY,sliderRadio.getValue(),10);
    }

    @FXML
    private void curva(ActionEvent event) {
        g.bezierCurveTo(cX, cX, cX, cX, cX, cX);
    }

    @FXML
    private void pacman(ActionEvent event) {

    }
    
    @FXML
    private void estrella5(ActionEvent event) {
        x = new double[10];
        y = new double[10];

        double radiointerno = sliderRadio.getValue();
        //primer punto
        x[0] = (coordenadaX + radiointerno);
        y[0] = coordenadaY;
        
        //me muevo adentro punto 2
        x[1] = (x[0] - radiointerno);
        y[1] = y[0];
        
        //me dirijo al punto 3 
        x[2] = x[1] - radiointerno * Math.cos(72 * Math.PI / 180);
        y[2] = y[1] - radiointerno * Math.sin(72 * Math.PI / 180);
        
        //punto 4
        x[3] = x[2] + radiointerno * Math.cos(252 * Math.PI / 180);
        y[3] = y[2] - radiointerno * Math.sin(252 * Math.PI / 180);
        
        //punto 5
        x[4] = x[3] - radiointerno;
        y[4] = y[3] ;
        
        //punto 6
        x[5]= x[4] + radiointerno * Math.cos(324 * Math.PI / 180);
        y[5]= y[4] - radiointerno * Math.sin(324 * Math.PI / 180);
        
        //punto 7
        x[6]= x[5] + radiointerno * Math.cos(252 * Math.PI / 180);
        y[6]= y[5] - radiointerno * Math.sin(252 * Math.PI / 180);
        
        //punto 8
        x[7]= x[6] + radiointerno * Math.cos(324 * Math.PI / 180);
        y[7]= y[6] + radiointerno * Math.sin(324 * Math.PI / 180);
        
        //punto 9
        x[8]= x[7] - radiointerno * Math.cos(216 * Math.PI / 180);
        y[8]= y[7] - radiointerno * Math.sin(216 * Math.PI / 180);
        
        //punto 10
        x[9]= x[0] + radiointerno * Math.cos(216 * Math.PI / 180);
        y[9]= y[0] - radiointerno * Math.sin(216 * Math.PI / 180);
        
        if(!relleno.isSelected() && borde.isSelected()){
            g.setLineWidth(sliderBorde.getValue());
            g.strokePolygon(x, y, 10);
        }else if(relleno.isSelected() && !borde.isSelected()){
            g.setFill(colorRelleno.getValue());
            g.fillPolygon(x, y, 10);
        }else{
            g.setStroke(colorBorde.getValue());
            g.setLineWidth(sliderBorde.getValue());
            g.setFill(colorRelleno.getValue());
            g.strokePolygon(x, y, 10);
            g.fillPolygon(x, y, 10);
        }
    }
    @FXML
    private void estrella6(ActionEvent event) {

    }
    
    @FXML
    private void limpiarLienzo(ActionEvent event) {
        g.clearRect(0, 0, lienzo.getWidth(),lienzo.getHeight());
        
        g = lienzo.getGraphicsContext2D();
        double w = lienzo.getWidth();
        double h = lienzo.getHeight();
        g.setStroke(Color.BLACK);
        g.setLineWidth(0.5);
        g.strokeRect(0, 0, w, h);
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
