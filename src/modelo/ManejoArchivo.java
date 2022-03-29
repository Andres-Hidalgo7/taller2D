/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author juank
 */
public class ManejoArchivo {
    public static boolean crearArchivoXML(LinkedList<FiguraGeometrica> lasFiguras, File F) {

        boolean t = false;

        try {

            Element figurasgeometricas = new Element("figurasgeometricas");
            Document doc = new Document(figurasgeometricas);

            for (int i = 0; i < lasFiguras.size(); i++) {

                FiguraGeometrica f = new FiguraGeometrica();
                f = lasFiguras.get(i);
                LinkedList<Punto2D> lp = f.getLosPuntos();
                Element figura = new Element("figuraGeometrica");
                figura.setAttribute(new Attribute("name", f.getNombre()));
                figura.setAttribute(new Attribute("lineW",String.valueOf(f.getLineW())));

                figura.addContent(new Element("colorBorde")
                        .setAttribute("colorB", f.getColorBorde())
                        .setAttribute("redB", String.valueOf(f.getStrokeValue().getR()))
                        .setAttribute("blueB", String.valueOf(f.getStrokeValue().getB()))
                        .setAttribute("greenB", String.valueOf(f.getStrokeValue().getG()))
                        .setAttribute("opacityB", String.valueOf(f.getStrokeValue().getA())));

                figura.addContent(new Element("colorRelleno")
                        .setAttribute("colorR", f.getColorRelleno())
                        .setAttribute("redR", String.valueOf(f.getFillValue().getR()))
                        .setAttribute("blueR", String.valueOf(f.getFillValue().getB()))
                        .setAttribute("greenR", String.valueOf(f.getFillValue().getG()))
                        .setAttribute("opacityR", String.valueOf(f.getFillValue().getA())));

                for (int j = 0; j < lp.size(); j++) {
                    figura.addContent(new Element("punto" + (j + 1))
                            .setAttribute("x", String.valueOf(lp.get(j).getX()))
                            .setAttribute("y", String.valueOf(lp.get(j).getY())));
                }
                doc.getRootElement().addContent(figura);

            }

            XMLOutputter xmlOutput = new XMLOutputter();
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(doc, new FileWriter(F));
            t = true;
            System.out.println("Archivo Guardado!");

        } catch (IOException ex) {
            Logger.getLogger(ManejoArchivo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return t;

    }
    
    public static LinkedList<FiguraGeometrica> leerXML(File f) {

        LinkedList<FiguraGeometrica> lasFiguras = new LinkedList<>();
        LinkedList<Punto2D> lPoints;
        SAXBuilder builder = new SAXBuilder();
        
        File xmlFile= new File(f.getAbsoluteFile()+"");

        try {

            Document doc= (Document) builder.build(xmlFile);;
            Element root = doc.getRootElement();

            List list = root.getChildren("figura");

            for (int i = 0; i < list.size(); i++) {

                Element figura = (Element) list.get(i);
                List e = figura.getChildren();
                lPoints = new LinkedList<>();
                String nombre = figura.getAttributeValue("nombre");
                double lineW = Double.parseDouble(figura.getAttributeValue("lineW"));
                String colorBorde = figura.getAttributeValue("colorB");
                String colorRelleno = figura.getAttributeValue("colorR");
                
                Element colorStroke = (Element) e.get(0);
                double redB = Double.parseDouble(colorStroke.getAttributeValue("redB"));
                double blueB = Double.parseDouble(colorStroke.getAttributeValue("blueB"));
                double greenB = Double.parseDouble(colorStroke.getAttributeValue("greenB"));
                double opacityB = Double.parseDouble(colorStroke.getAttributeValue("opacityB"));
                ColorValue cB = new ColorValue(redB, blueB, greenB, opacityB);

                Element colorFill = (Element) e.get(1);
                double redR = Double.parseDouble(colorFill.getAttributeValue("redR"));
                double blueR = Double.parseDouble(colorFill.getAttributeValue("blueR"));
                double greenR = Double.parseDouble(colorFill.getAttributeValue("greenR"));
                double opacityR = Double.parseDouble(colorFill.getAttributeValue("opacityR"));
                ColorValue cR = new ColorValue(redR, blueR, greenR, opacityR);

                for (int j = 2; j < e.size(); j++) {

                    Element coordenadas = (Element) e.get(j);

                    double x = Double.parseDouble(coordenadas.getAttributeValue("x"));
                    double y = Double.parseDouble(coordenadas.getAttributeValue("y"));

                    lPoints.add(new Punto2D(x, y));
                }
                lasFiguras.add(new FiguraGeometrica(nombre, colorBorde, colorRelleno, lPoints, cB, cR,lineW));
            }
            System.out.println("lasFiguras = " + lasFiguras.size());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return lasFiguras;

    }
}
