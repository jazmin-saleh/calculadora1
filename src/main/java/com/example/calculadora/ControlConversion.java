package com.example.calculadora;

import java.util.ArrayList;

public class ControlConversion {

    private String expresion;
    private String [] carcateres;
    private ArrayList<String> array;

    public ControlConversion(String expresion) {
        this.expresion = expresion;
        carcateres = new String[]{"(",")","*","+","/","-","^"};
        array = new ArrayList<>();
    }

    public boolean veroficacionParentecis(){
        int pApertura =0, pCierre=0;
        for(int i=0; i< expresion.length(); i++){
            if (expresion.substring(i,i+1).equalsIgnoreCase("(")){
                pApertura++;
            }else if(expresion.substring(i,i+1).equalsIgnoreCase(")")){
                pCierre++;
            }
        }
        if (pApertura == pCierre) return true;
        else return false;
    }

    //generar espacios
    public String generarEspacios(){
        String aux="";
        if (veroficacionParentecis()){
            for (int i=0; i< expresion.length(); i++){
                if( verificar(expresion.substring(i,i+1))){
                    aux += expresion.substring(i,i+1);
                }else{
                    aux += " "+expresion.substring(i,i+1) + " ";
                }
            }
        }
        return aux;
    }

    // verificar
    public boolean verificar(String substring){
        for (int i=0; i< carcateres.length; i++){
            if (carcateres[i].equalsIgnoreCase(substring)){
                return  false;
            }
        }
        return true;
    }

    public String generarEspacios(String tetxo){
        String aux="";
        if (veroficacionParentecis()){
            for (int i=0; i< tetxo.length(); i++){
                if( verificar(tetxo.substring(i,i+1))){
                    aux += tetxo.substring(i,i+1);
                }else{
                    aux += " "+tetxo.substring(i,i+1) + " ";
                }
            }
        }
        return aux;
    }

    public String reducirEspacios() throws Error {
        //generar espacio llega bien
        /*System.out.println("Generar espacio "+generarEspacios());
        System.out.println( NotacionPolaca.Infijo2PosfijoTxt(generarEspacios()).toString());*/
        //Aca no esta llegando +6
        String espacios = NotacionPolaca.Infijo2PosfijoTxt(generarEspacios())+ " ";
        System.out.println("<<"+espacios);
        espacios = generarEspacios(espacios);
        String aux = "";
        for (int i=0; i< espacios.length(); i++){
            //System.out.print("> "+espacios.substring(i,i+1));
            if (!espacios.substring(i,i+1).equalsIgnoreCase(" ")){
                aux += espacios.substring(i,i+1);
                System.out.println(">>"+aux);
            }else{
                if ( !aux.equalsIgnoreCase("")){
                    array.add(aux);
                    System.out.println(">"+array);
                    aux ="";
                }
            }
        }
        System.out.println("\n"+array);
        String auxTos = "";
        for (String a:array) {
            auxTos += a + " ";
        }
        return auxTos.substring(0,auxTos.length()-1);
    }
}
