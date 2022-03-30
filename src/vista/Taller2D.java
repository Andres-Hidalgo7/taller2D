/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 *Esta clase ejecuta todo el programa
 * @author Juan Camilo Hidalgo Betancourth 2205622
 * @author Andrés Felipe Hidalgo Betancourth 2205621
 * @author Alejandro Gomez Llanos 2201157
 * @author Diego Fernando Rangel Lopez
 * date 28 Marzo 2022
 */
public class Taller2D extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/vista/FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
