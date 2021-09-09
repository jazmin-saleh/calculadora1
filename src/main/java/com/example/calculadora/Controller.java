package com.example.calculadora;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;

public class Controller implements Initializable {
    private static int vGap;
    private static int radius;
    private model.Tree<String> treeM;

    @FXML
    private AnchorPane canvas,calculator,tree;
    @FXML
    private TextField pantallaUno;

    @FXML
    private TextField PantallaDos;

    @FXML
    private Button igual;

    @FXML
    private Label base;


    @FXML
    private Button bin,oct,dec,hex,cerrar,siete, ocho, nueve, cuatro, cinco, seis, uno, dos, tres, cero, parentesisA, parentesisC, por, dividido, mas, menos, potencia, punto, porciento, c, ce,borrar;
    @FXML
    private Button a,d,b,e,cc,f;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vGap = 50;
        radius = 20;


    }
    private void displayAVLTree(model.Node<String> root,
                                double x, double y, double hGap) {

        if (root.getLeft() != null) {
            // Draw a line to the left node
            tree.getChildren().add(new Line(x - hGap, y + vGap, x, y));
            // Draw the left subtree recursively
            displayAVLTree(root.getLeft(), x - hGap, y + vGap, hGap / 2);
        }

        if (root.getRight() != null) {
            // Draw a line to the right node
            tree.getChildren().add(new Line(x + hGap, y + vGap, x, y));
            // Draw the right subtree recursively
            displayAVLTree(root.getRight(), x + hGap, y + vGap, hGap / 2);
        }


        //Mostrar un nodo
        String text = root.getInfo() + "";
        Text texto = new Text(x - 5, y + 5, symbol(text));
        texto.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        texto.setFill(Color.WHITE);
        Circle circle = new Circle(x, y, radius);
        circle.setFill(Color.BLACK);
        circle.setStroke(Color.BLACK);
/*        test.getChildren().addAll(circle,
                new Text(x - 4, y + 4, root.getInfo() + ""));*/
        tree.getChildren().addAll(circle, texto);
    }
public  void drawn(){
        expresion();
    tree.getChildren().clear();
    Stack<model.Node<String>> s = new Stack<>();
    //((((3 + 1) * 3) /((9 - 5) + 2)) - ((3 * (7 - 4)) + 6))
    String expresion = pantallaUno.getText();
//    String a = "3 1 + 3 * 9 5 - 2 + / 7 4 - 3 * 6 + -";
    String a = "34 1 + 35 * 9 58 - 2 + / 8 / 6 +";
//        String a = "3 1 +";
//    String a = expresion();
    System.out.println();
    String[] t = a.split(" ");
    for (int i = 0; i < t.length; i++) {
        s.push(new model.Node<String>(t[i]));
    }
    model.Tree<String> tree = new model.Tree<String>(s);
    tree.createTree();
    tree.getRoot();
    displayAVLTree(tree.getRoot(), 400, 100, 200);
}
    public String symbol(String sym){
        if(sym.equalsIgnoreCase("/"))return "÷";
        else if(sym.equalsIgnoreCase("*")) return "X";
        else return  sym;

    }

    public String expresion(){
        String a = "";
        a = Conversion.conversionPostfijo(pantallaUno.getText());
        if(a.length()>1)a= a.substring(1,a.length());
        System.out.println(a);
        return a;
    }

    public  String separar(String postfija){
        String aux = "";
        for (int i = 0; i < postfija.length(); i++) {
            if (postfija.substring(i, i + 1).equalsIgnoreCase("+") || postfija.substring(i, i + 1).equalsIgnoreCase("/") || postfija.substring(i, i + 1).equalsIgnoreCase("*") || postfija.substring(i, i + 1).equalsIgnoreCase("-")) {
                aux += " "+postfija.substring(i, i + 1);
            }else{
                aux += postfija.substring(i, i + 1);
            }

        }
        return aux;
    }
    @FXML
    public void cerrar(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void teclado(ActionEvent event) {
//        System.out.println("funciona");
        Object boton = event.getSource();
        if (boton.equals(uno)) {
            pantallaUno.setText(pantallaUno.getText() + "1");
        } else if (boton.equals(dos)) {
            pantallaUno.setText(pantallaUno.getText() + "2");
        } else if (boton.equals(tres)) {
            pantallaUno.setText(pantallaUno.getText() + "3");
        } else if (boton.equals(cuatro)) {
            pantallaUno.setText(pantallaUno.getText() + "4");
        } else if (boton.equals(cinco)) {
            pantallaUno.setText(pantallaUno.getText() + "5");
        } else if (boton.equals(seis)) {
            pantallaUno.setText(pantallaUno.getText() + "6");
        } else if (boton.equals(siete)) {
            pantallaUno.setText(pantallaUno.getText() + "7");
        } else if (boton.equals(ocho)) {
            pantallaUno.setText(pantallaUno.getText() + "8");
        } else if (boton.equals(nueve)) {
            pantallaUno.setText(pantallaUno.getText() + "9");
        } else if (boton.equals(cero)) {
            pantallaUno.setText(pantallaUno.getText() + "0");
        } else if (boton.equals(punto)) {
            pantallaUno.setText(pantallaUno.getText() + ".");
        } else if (boton.equals(parentesisA)) {
            pantallaUno.setText(pantallaUno.getText() + "(");
        } else if (boton.equals(parentesisC)) {
            pantallaUno.setText(pantallaUno.getText() + ")");
        } else if (boton.equals(porciento)) {
            pantallaUno.setText(pantallaUno.getText() + "%");
        } else if (boton.equals(potencia)) {
            pantallaUno.setText(pantallaUno.getText() + "^");
        } else if (boton.equals(por)) {
            pantallaUno.setText(pantallaUno.getText() + "*");
        } else if (boton.equals(dividido)) {
            pantallaUno.setText(pantallaUno.getText() + "/");
        } else if (boton.equals(mas)) {
            pantallaUno.setText(pantallaUno.getText() + "+");
        } else if (boton.equals(menos)) {
            pantallaUno.setText(pantallaUno.getText() + "-");
        } else if (boton.equals(c)) {
            pantallaUno.setText("");
            PantallaDos.setText("");
        } else if (boton.equals(ce)) {
            pantallaUno.setText("");
            PantallaDos.setText("");
        } else if (boton.equals(borrar)) {
      /*      System.out.println(pantallaUno.getText());
            if(pantallaUno.getText() == null) System.out.println("verdad");*/
            pantallaUno.setText(pantallaUno.getText().substring(0,pantallaUno.getText().length()-1));
        }else if(boton.equals(igual)){
            drawn();
        }
    }
    @FXML
    public void base(ActionEvent event) {
        Object boton = event.getSource();
        if (boton.equals(bin)) {
            base.setText("BIN");
            binario();
        } else if (boton.equals(oct)) {
            base.setText("OCT");
            octal();
        } else if (boton.equals(dec)) {
            base.setText("DEC");
            dec();
        } else if (boton.equals(hex)) {
            base.setText("HEX");
            hexagecimal();
        }
    }

    public void dec(){
        uno.setDisable(false);
        dos.setDisable(false);
        tres.setDisable(false);
        cuatro.setDisable(false);
        cinco.setDisable(false);
        seis.setDisable(false);
        siete.setDisable(false);
        ocho.setDisable(false);
        nueve.setDisable(false);
        cero.setDisable(false);
        punto.setDisable(false);

        a.setDisable(true);
        b.setDisable(true);
        cc.setDisable(true);
        d.setDisable(true);
        e.setDisable(true);
        f.setDisable(true);
    }

    public void octal(){
        uno.setDisable(false);
        dos.setDisable(false);
        tres.setDisable(false);
        cuatro.setDisable(false);
        cinco.setDisable(false);
        seis.setDisable(false);
        siete.setDisable(false);
        ocho.setDisable(false);
        cero.setDisable(false);
        punto.setDisable(false);

        nueve.setDisable(true);
        a.setDisable(true);
        b.setDisable(true);
        cc.setDisable(true);
        d.setDisable(true);
        e.setDisable(true);
        f.setDisable(true);
    }
    public void binario(){
        uno.setDisable(false);
        cero.setDisable(false);
        punto.setDisable(false);

        dos.setDisable(true);
        tres.setDisable(true);
        cuatro.setDisable(true);
        cinco.setDisable(true);
        seis.setDisable(true);
        siete.setDisable(true);
        ocho.setDisable(true);
        nueve.setDisable(true);
        a.setDisable(true);
        b.setDisable(true);
        cc.setDisable(true);
        d.setDisable(true);
        e.setDisable(true);
        f.setDisable(true);
    }

    public void hexagecimal(){
        uno.setDisable(false);
        dos.setDisable(false);
        tres.setDisable(false);
        cuatro.setDisable(false);
        cinco.setDisable(false);
        seis.setDisable(false);
        siete.setDisable(false);
        ocho.setDisable(false);
        nueve.setDisable(false);
        cero.setDisable(false);
        punto.setDisable(false);
        a.setDisable(false);
        b.setDisable(false);
        cc.setDisable(false);
        d.setDisable(false);
        e.setDisable(false);
        f.setDisable(false);
    }
}