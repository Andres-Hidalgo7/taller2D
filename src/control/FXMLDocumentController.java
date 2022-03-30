/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import modelo.ColorValue;
import modelo.FiguraGeometrica;
import modelo.ManejoArchivo;
import modelo.Punto2D;

/**
 *
 *En esta clase va toda la lógica del programa al ejecutarse
 * @author Juan Camilo Hidalgo Betancourth 2205622
 * @author Andrés Felipe Hidalgo Betancourth 2205621
 * @author Alejandro Gomez Llanos 2201157
 * @author Diego Fernando Rangel Lopez
 * date 28 Marzo 2022
 */
public class FXMLDocumentController implements Initializable {

    //Variables para el metodo ngon
    double coordenadaX, coordenadaY;
    double cX, cY, o, a;
    double[] xx, yy;

    //variables para el metodo estrella5
    double[] x, y;
    
    //Variables para curva
    LinkedList<Punto2D> pCurva = new LinkedList<>();
    
    GraphicsContext g;

    @FXML
    private ColorPicker colorRelleno, colorBorde;

    @FXML
    private Label lRadio;

    @FXML
    private Slider sliderBorde, sliderRadio;

    @FXML
    private Canvas lienzo;
    @FXML
    private RadioButton relleno, borde;
   //Variables para el guardado del las figuras
    DecimalFormat df;
    FileChooser fc;
    LinkedList<FiguraGeometrica> lasFiguras;
    LinkedList<Punto2D> losPuntos;
    
    String colorStroke = "Sin borde";
    String colorFill = "Sin relleno";
    //Variables para guardar el color
    double red = 0;
    double blue = 0;
    double green = 0;
    double opacity = 0;
    double lineW = 0;

    ColorValue fillValue;
    ColorValue strokeValue;
//contador para guardar varias veces una misma figura
    int contador = 0;
//Array para almcenar los puntos leidos
    double pX[];
    double pY[];

    @FXML
    /**
     * ObtenerCoordenadas
     *
     * @param Event //este método obtiene las coordenadas del mouse cuando da click en el area de trabajo
     * 
     */
    private void obtenerCoordenadas(MouseEvent event) {
        coordenadaX = event.getX();
        coordenadaY = event.getY();
//
        Punto2D objp = new Punto2D(coordenadaX, coordenadaY);
        pCurva.add(objp);
        if(pCurva.size() > 4){
            pCurva.removeFirst();
        }
        

        System.out.println("Punto " + objp.toString());
    }
    /**
     * ngon
     *
     * @param x  
     * @param y
     * @param r
     * @param n //Este metodo crea un polígono de lados n, y es llamado por lo botones que hacen poligonos
     * y también guarda las figuras
     */
    public void ngon(double x, double y, double r, int n) {

        xx = new double[n];
        yy = new double[n];

        a = 2 * Math.PI / n;

        o = 0;//inicializar el angulo 

        for (int i = 0; i < n; i++) {

            cX = Math.cos(o);
            cY = Math.sin(o);

            xx[i] = x + cX * r;
            yy[i] = y + cY * r;

            o += a;

        }
        //Dibuja dependiendo de si estan activados el borde y el relleno
        lineW = sliderBorde.getValue();
        if (!relleno.isSelected() && borde.isSelected()) {

            g.setStroke(colorBorde.getValue());
            g.setLineWidth(sliderBorde.getValue());
            g.strokePolygon(xx, yy, n);

            colorStroke = FiguraGeometrica.toHexString(colorBorde.getValue());
            red = colorBorde.getValue().getRed();
            blue = colorBorde.getValue().getBlue();
            green = colorBorde.getValue().getGreen();
            opacity = colorBorde.getValue().getOpacity();

            strokeValue = new ColorValue(red, blue, green, opacity);

        } else if (relleno.isSelected() && !borde.isSelected()) {

            g.setFill(colorRelleno.getValue());
            g.fillPolygon(xx, yy, n);

            red = colorRelleno.getValue().getRed();
            blue = colorRelleno.getValue().getBlue();
            green = colorRelleno.getValue().getGreen();
            opacity = colorRelleno.getValue().getOpacity();

            fillValue = new ColorValue(red, blue, green, opacity);

        } else {

            g.strokePolygon(xx, yy, n);
            g.setStroke(colorBorde.getValue());
            g.setLineWidth(sliderBorde.getValue());

            colorStroke = FiguraGeometrica.toHexString(colorBorde.getValue());
            red = colorBorde.getValue().getRed();
            blue = colorBorde.getValue().getBlue();
            green = colorBorde.getValue().getGreen();
            opacity = colorBorde.getValue().getOpacity();

            strokeValue = new ColorValue(red, blue, green, opacity);

            g.setFill(colorRelleno.getValue());
            g.fillPolygon(xx, yy, n);

            red = colorRelleno.getValue().getRed();
            blue = colorRelleno.getValue().getBlue();
            green = colorRelleno.getValue().getGreen();
            opacity = colorRelleno.getValue().getOpacity();

            fillValue = new ColorValue(red, blue, green, opacity);
        }
        losPuntos = new LinkedList<>();
        for (int i = 0; i < xx.length; i++) {
            losPuntos.add(new Punto2D(xx[i], yy[i]));
        }
        FiguraGeometrica figura = new FiguraGeometrica("Poligono No."
                + contador, colorStroke, colorFill, losPuntos, strokeValue, fillValue, lineW);
        lasFiguras.add(figura);
        contador++;
        System.out.println("Numero de figuras en la lista " + lasFiguras.size());

    }

    @FXML
    /**
     * activarBoton
     *
     * @param Event //Este metodo activa y desactiva el colorpicker del relleno y de los bordes
     * dependiendo si estan activados o no
     * 
     */
    private void activarBoton(ActionEvent event) {
        if (relleno.isSelected()) {
            colorRelleno.setDisable(false);
        } else {
            g.setFill(colorRelleno.getValue());
            colorRelleno.setDisable(true);
        }

        if (borde.isSelected()) {
            colorBorde.setDisable(false);
            sliderBorde.setDisable(false);
        } else {
            colorBorde.setDisable(true);
            sliderBorde.setDisable(true);
        }
    }

    @FXML
    /**
     * hexagono
     *
     * @param Event //Genera un hexagono llamando al metodo ngon
     * 
     */
    private void hexagono(ActionEvent event) {
        ngon(coordenadaX, coordenadaY, sliderRadio.getValue(), 6);
    }
 /**
     * heptagono
     *
     * @param Event //Genera un heptagono llamando al metodo ngon
     * 
     */
    @FXML
    private void heptagono(ActionEvent event) {
        ngon(coordenadaX, coordenadaY, sliderRadio.getValue(), 7);
    }

    @FXML
     /**
     * octagono
     *
     * @param Event //Genera un octagono llamando al metodo ngon
     * 
     */
    private void octagono(ActionEvent event) {
        ngon(coordenadaX, coordenadaY, sliderRadio.getValue(), 8);
    }

    @FXML
     /**
     * decagono
     *
     * @param Event //Genera un decagono llamando al metodo ngon
     * 
     */
    private void decagono(ActionEvent event) {
        ngon(coordenadaX, coordenadaY, sliderRadio.getValue(), 10);
    }

    @FXML
     /**
     * curva
     *
     * @param Event //Genera una curva usando la función de BezierCurvTo
     * 
     */
    private void curva(ActionEvent event) {
        double x1,y1,x2,y2,x3,y3,x4,y4;
        
        if(pCurva.size() < 4){
            JOptionPane.showMessageDialog(null,"Recuerda marcar 4 puntos antes de generar la curva" );
        }else{
            x1 = pCurva.get(0).getX();
            y1 = pCurva.get(0).getY();

            x2 = pCurva.get(1).getX();
            y2 = pCurva.get(1).getY();

            x3 = pCurva.get(2).getX();
            y3 = pCurva.get(2).getY();

            x4 = pCurva.get(3).getX();
            y4 = pCurva.get(3).getY();

            g.moveTo(x1, y1);
            g.setStroke(colorBorde.getValue());
            g.setLineWidth(sliderBorde.getValue());
            g.bezierCurveTo(x2, y2, x3, y3, x4, y4);
            g.stroke();
        
        }
        
        

    }

    @FXML
     /**
     * pacman
     *
     * @param Event //Hace un pacman usandoo curvas y lineas
     * 
     */
    private void pacman(ActionEvent event) {
        //aqui sabremos el tamaño que tendremos
        x = new double[3];
        y = new double[3];

        //radio
        double radio = 60;

        //punto del centro
        x[0] = coordenadaX;
        y[0] = coordenadaY;

        //cord 1 a la derecha
        x[1] = coordenadaX + radio + radio / 2.2;
        y[1] = coordenadaY;

        //punto 2 de abajo                           
        x[2] = coordenadaX + radio + radio / 12 * Math.sin(45 * Math.PI / 180);
        y[2] = coordenadaY + radio + radio / 12 * Math.cos(45 * Math.PI / 180);
        if (!relleno.isSelected() && borde.isSelected()) {
            g.setStroke(colorBorde.getValue());
            g.setLineWidth(sliderBorde.getValue());
            g.strokeArc(x[0] - radio - radio / 2, y[0] - radio - radio / 2, radio * 3, radio * 3, 0, 315, ArcType.OPEN);
            g.strokeLine(x[0], y[0], x[1], y[1]);
            g.strokeLine(x[0], y[0], x[2], y[2]);
            //g.strokeArc(x[0]-radio-radio/2, y[0]-radio-radio/2, radio*3, radio*3, 0, 315, ArcType.OPEN);
        } else if (relleno.isSelected() && !borde.isSelected()) {
            //rellenar arco y borde de arco, se deben hacer en ese orden 
            g.setStroke(colorRelleno.getValue());
            g.setFill(colorRelleno.getValue());
            g.strokeLine(x[0], y[0], x[1], y[1]);
            g.strokeLine(x[0], y[0], x[2], y[2]);
            g.fillArc(x[0] - radio - radio / 2, y[0] - radio - radio / 2, radio * 3, radio * 3, 0, 315, ArcType.ROUND);
            g.strokeArc(x[0] - radio - radio / 2, y[0] - radio - radio / 2, radio * 3, radio * 3, 0, 315, ArcType.OPEN);
        } else {
            g.setStroke(colorBorde.getValue());
            g.setLineWidth(sliderBorde.getValue());
            g.strokeLine(x[0], y[0], x[1], y[1]);
            g.strokeLine(x[0], y[0], x[2], y[2]);
            
            g.setFill(colorRelleno.getValue());
            g.fillArc(x[0] - radio - radio / 2, y[0] - radio - radio / 2, radio * 3, radio * 3, 0, 315, ArcType.ROUND);
            g.strokeArc(x[0] - radio - radio / 2, y[0] - radio - radio / 2, radio * 3, radio * 3, 0, 315, ArcType.OPEN);
        }
    }

    @FXML
     /**
     * estrella5
     *
     * @param Event //crea una estrella calculando sus puntos uno por uno
     * y también guarda la figura
     * 
     */
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
        y[4] = y[3];

        //punto 6
        x[5] = x[4] + radiointerno * Math.cos(324 * Math.PI / 180);
        y[5] = y[4] - radiointerno * Math.sin(324 * Math.PI / 180);

        //punto 7
        x[6] = x[5] + radiointerno * Math.cos(252 * Math.PI / 180);
        y[6] = y[5] - radiointerno * Math.sin(252 * Math.PI / 180);

        //punto 8
        x[7] = x[6] + radiointerno * Math.cos(324 * Math.PI / 180);
        y[7] = y[6] + radiointerno * Math.sin(324 * Math.PI / 180);

        //punto 9
        x[8] = x[7] - radiointerno * Math.cos(216 * Math.PI / 180);
        y[8] = y[7] - radiointerno * Math.sin(216 * Math.PI / 180);

        //punto 10
        x[9] = x[0] + radiointerno * Math.cos(216 * Math.PI / 180);
        y[9] = y[0] - radiointerno * Math.sin(216 * Math.PI / 180);
        lineW = sliderBorde.getValue();
        if (!relleno.isSelected() && borde.isSelected()) {

            g.setStroke(colorBorde.getValue());
            g.setLineWidth(lineW);
            g.strokePolygon(x, y, 10);

            colorStroke = FiguraGeometrica.toHexString(colorBorde.getValue());
            red = colorBorde.getValue().getRed();
            blue = colorBorde.getValue().getBlue();
            green = colorBorde.getValue().getGreen();
            opacity = colorBorde.getValue().getOpacity();

            strokeValue = new ColorValue(red, blue, green, opacity);

        } else if (relleno.isSelected() && !borde.isSelected()) {

            g.setFill(colorRelleno.getValue());
            g.fillPolygon(x, y, 10);

            red = colorRelleno.getValue().getRed();
            blue = colorRelleno.getValue().getBlue();
            green = colorRelleno.getValue().getGreen();
            opacity = colorRelleno.getValue().getOpacity();

            fillValue = new ColorValue(red, blue, green, opacity);

        } else {

            g.setStroke(colorBorde.getValue());
            g.setLineWidth(sliderBorde.getValue());
            g.strokePolygon(x, y, 10);

            colorStroke = FiguraGeometrica.toHexString(colorBorde.getValue());
            red = colorBorde.getValue().getRed();
            blue = colorBorde.getValue().getBlue();
            green = colorBorde.getValue().getGreen();
            opacity = colorBorde.getValue().getOpacity();

            strokeValue = new ColorValue(red, blue, green, opacity);

            g.setFill(colorRelleno.getValue());
            g.fillPolygon(x, y, 10);

            red = colorRelleno.getValue().getRed();
            blue = colorRelleno.getValue().getBlue();
            green = colorRelleno.getValue().getGreen();
            opacity = colorRelleno.getValue().getOpacity();

            fillValue = new ColorValue(red, blue, green, opacity);

        }
        losPuntos = new LinkedList<>();
        for (int i = 0; i < x.length; i++) {
            losPuntos.add(new Punto2D(x[i], y[i]));
        }
        FiguraGeometrica figura = new FiguraGeometrica("Estrella5P No."
                + contador, colorStroke, colorFill, losPuntos, strokeValue, fillValue, lineW);
        lasFiguras.add(figura);
        contador++;
        System.out.println("Numero de figuras en la lista " + lasFiguras.size());
    }

    @FXML
     /**
     * estrella6
     *
     * @param Event //crea una estrella calculando sus puntos uno por uno
     * y también guarda la figura
     * 
     */
    private void estrella6(ActionEvent event) {
        double rad = sliderRadio.getValue();

        x = new double[12];
        y = new double[12];

        x[0] = (coordenadaX + rad * Math.cos(11 * 2 * Math.PI / 12));
        y[0] = (coordenadaY + rad * Math.sin(11 * 2 * Math.PI / 12));

        x[1] = (coordenadaX + (rad / 2.5) * Math.cos(12 * 2 * Math.PI / 12));
        y[1] = (coordenadaY + (rad / 2.5) * Math.sin(12 * 2 * Math.PI / 12));

        x[2] = (coordenadaX + rad * Math.cos(2 * Math.PI / 12));
        y[2] = (coordenadaY + rad * Math.sin(2 * Math.PI / 12));

        x[3] = (coordenadaX + (rad / 2.5) * Math.cos(2 * 2 * Math.PI / 12));
        y[3] = (coordenadaY + (rad / 2.5) * Math.sin(2 * 2 * Math.PI / 12));

        x[4] = (coordenadaX + rad * Math.cos(3 * 2 * Math.PI / 12));
        y[4] = (coordenadaY + rad * Math.sin(3 * 2 * Math.PI / 12));

        x[5] = (coordenadaX + (rad / 2.5) * Math.cos(4 * 2 * Math.PI / 12));
        y[5] = (coordenadaY + (rad / 2.5) * Math.sin(4 * 2 * Math.PI / 12));

        x[6] = (coordenadaX + rad * Math.cos(5 * 2 * Math.PI / 12));
        y[6] = (coordenadaY + rad * Math.sin(5 * 2 * Math.PI / 12));

        x[7] = (coordenadaX + (rad / 2.5) * Math.cos(6 * 2 * Math.PI / 12));
        y[7] = (coordenadaY + (rad / 2.5) * Math.sin(6 * 2 * Math.PI / 12));

        x[8] = (coordenadaX + rad * Math.cos(7 * 2 * Math.PI / 12));
        y[8] = (coordenadaY + rad * Math.sin(7 * 2 * Math.PI / 12));

        x[9] = (coordenadaX + (rad / 2.5) * Math.cos(8 * 2 * Math.PI / 12));
        y[9] = (coordenadaY + (rad / 2.5) * Math.sin(8 * 2 * Math.PI / 12));

        x[10] = (coordenadaX + rad * Math.cos(9 * 2 * Math.PI / 12));
        y[10] = (coordenadaY + rad * Math.sin(9 * 2 * Math.PI / 12));

        x[11] = (coordenadaX + (rad / 2.5) * Math.cos(10 * 2 * Math.PI / 12));
        y[11] = (coordenadaY + (rad / 2.5) * Math.sin(10 * 2 * Math.PI / 12));

        lineW = sliderBorde.getValue();
        if (!relleno.isSelected() && borde.isSelected()) {

            g.setStroke(colorBorde.getValue());
            g.setLineWidth(lineW);
            g.strokePolygon(x, y, 12);

            colorStroke = FiguraGeometrica.toHexString(colorBorde.getValue());
            red = colorBorde.getValue().getRed();
            blue = colorBorde.getValue().getBlue();
            green = colorBorde.getValue().getGreen();
            opacity = colorBorde.getValue().getOpacity();

            strokeValue = new ColorValue(red, blue, green, opacity);

        } else if (relleno.isSelected() && !borde.isSelected()) {

            g.setFill(colorRelleno.getValue());
            g.fillPolygon(x, y, 12);

            red = colorRelleno.getValue().getRed();
            blue = colorRelleno.getValue().getBlue();
            green = colorRelleno.getValue().getGreen();
            opacity = colorRelleno.getValue().getOpacity();

            fillValue = new ColorValue(red, blue, green, opacity);

        } else {

            g.setStroke(colorBorde.getValue());
            g.setLineWidth(sliderBorde.getValue());
            g.strokePolygon(x, y, 12);

            colorStroke = FiguraGeometrica.toHexString(colorBorde.getValue());
            red = colorBorde.getValue().getRed();
            blue = colorBorde.getValue().getBlue();
            green = colorBorde.getValue().getGreen();
            opacity = colorBorde.getValue().getOpacity();

            strokeValue = new ColorValue(red, blue, green, opacity);

            g.setFill(colorRelleno.getValue());
            g.fillPolygon(x, y, 12);

            red = colorRelleno.getValue().getRed();
            blue = colorRelleno.getValue().getBlue();
            green = colorRelleno.getValue().getGreen();
            opacity = colorRelleno.getValue().getOpacity();

            fillValue = new ColorValue(red, blue, green, opacity);

            losPuntos = new LinkedList<>();
            for (int i = 0; i < x.length; i++) {
                losPuntos.add(new Punto2D(x[i], y[i]));
            }

        }
        FiguraGeometrica figura = new FiguraGeometrica("Estrella6P No."
                + contador, colorStroke, colorFill, losPuntos, strokeValue, fillValue, lineW);
        lasFiguras.add(figura);
        contador++;
        System.out.println("Numero de figuras en la lista " + lasFiguras.size());
    }

    @FXML
     /**
     *limpiarLienzo
     *
     * @param Event //Deja en blanco todo el area de trabajo
     * 
     */
    private void limpiarLienzo(ActionEvent event) {
        
 
        g.clearRect(0, 0, lienzo.getWidth(), lienzo.getHeight());

        g = lienzo.getGraphicsContext2D();
        double w = lienzo.getWidth();
        double h = lienzo.getHeight();
        g.setStroke(Color.BLACK);
        g.setLineWidth(0.5);
        g.strokeRect(0, 0, w, h);
    }

    @FXML
     /**
     * guardarArchivo
     *
     * @param Event //Guarda el archivo XML con las figuras en una lista
     * 
     */
    private void guardarArchivo(ActionEvent event) {

        fc.setTitle("Guardar XML");
        fc.setInitialFileName("archivo");
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XML Files", "*.xml"));

        File f = fc.showSaveDialog(null);
        fc.setInitialDirectory(f.getParentFile());

        boolean t = ManejoArchivo.crearArchivoXML(lasFiguras, f);

        if (t) {
            JOptionPane.showMessageDialog(null, "Archivo creado y guardado");
        } else {
            JOptionPane.showMessageDialog(null, "No se creó el archivo");
        }
        System.out.println("t = " + t);

    }

    @FXML
    /**
     * leerArchivo
     *
     * @param Event //Lee el archivo guardado anteriormente y lo dibuja en el
     * area de trabajo
     * 
     */
    private void leerArchivo(ActionEvent event) {

        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XML Files", "*.xml"));
        File f = fc.showOpenDialog(null);

        if (fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XML Files", "*.xml"))) {
            lasFiguras = ManejoArchivo.leerArchivoXML(f);
            int s = lasFiguras.size();
            System.out.println("s = " + s);

            for (int i = 0; i < s; i++) {
                FiguraGeometrica F = lasFiguras.get(i);
                System.out.println("Figuras = " + lasFiguras);
                LinkedList<Punto2D> losPuntos = F.getLosPuntos();
                ColorValue colorBorde = F.getStrokeValue();
                ColorValue colorRelleno = F.getFillValue();
                System.out.println("losPuntos = " + losPuntos);

                pX = new double[losPuntos.size()];
                pY = new double[losPuntos.size()];

                for (int j = 0; j < losPuntos.size(); j++) {
                    Punto2D p = losPuntos.get(j);
                    double xp = p.getX();
                    double yp = p.getY();
                    pX[j] = xp;
                    pY[j] = yp;

                }

                g.setLineWidth(F.getLineW());
                g.setFill(new Color(colorRelleno.getR(), colorRelleno.getG(), colorRelleno.getB(), colorRelleno.getA()));
                g.setStroke(new Color(colorBorde.getR(), colorBorde.getG(), colorBorde.getB(), colorBorde.getA()));
                g.strokePolygon(pX, pY, losPuntos.size());
                g.fillPolygon(pX, pY, losPuntos.size());

            }
        }
    }

    @FXML
    /**
     * guardarIMG
     *
     * @param Event //Guarda un snapshot del área de trabajo en png o jpg 
     * 
     */
    private void guardarIMG(ActionEvent event) throws IOException {

        Image snapshot = lienzo.snapshot(null, null);
        ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", new File("canvasCaptura.png"));

        FileChooser fc = new FileChooser();
        fc.setTitle("Guardar captura del canvas");

        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", ".jpg"),
                new FileChooser.ExtensionFilter("PNG", ".png"));
        //guarda la captura
        File canvasCapture1 = fc.showSaveDialog(null);
        if (canvasCapture1 != null) {
            WritableImage wi = new WritableImage((int) lienzo.getWidth(), (int) lienzo.getHeight());
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(lienzo.snapshot(null, wi), null), "png", canvasCapture1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    /**
     * Initialize
     *
     * @param Event //Este método se ejecuta cuando corre el programa
     * 
     */
    public void initialize(URL url, ResourceBundle rb) {
        g = lienzo.getGraphicsContext2D();
        double w = lienzo.getWidth();
        double h = lienzo.getHeight();
        g.setStroke(Color.BLACK);
        g.setLineWidth(0.5);
        g.strokeRect(0, 0, w, h);

        colorRelleno.setDisable(true);

        contador = 0;
        strokeValue = new ColorValue();
        fillValue = new ColorValue();
        lasFiguras = new LinkedList<>();
        fc = new FileChooser();

    }

}
