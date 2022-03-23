/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import modelo.Punto2D;

/**
 *
 * @author andres
 */
public class FXMLDocumentController implements Initializable {

    double coordenadaX;
    double coordenadaY;
    GraphicsContext g;
    @FXML
    ColorPicker colorRelleno, colorBorde;

    @FXML
    RadioButton RadioButton;

    @FXML
    private Canvas lienzo;
    @FXML
    private Label label, labelRelleno, labelBorde;

    @FXML
    private void obtenerCoordenadas(MouseEvent event) {
        coordenadaX = event.getX();
        coordenadaY = event.getY();
//
        Punto2D objp = new Punto2D(coordenadaX, coordenadaY);

        System.out.println("Punto " + objp.toString());
    }

    @FXML
    private void hexagono(ActionEvent event) {

    }

    @FXML
    private void heptagono(ActionEvent event) {

    }

    @FXML
    private void octagono(ActionEvent event) {

    }

    @FXML
    private void decagono(ActionEvent event) {

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
    }

}
