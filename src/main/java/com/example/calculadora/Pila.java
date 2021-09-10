package com.example.calculadora;

public class Pila {
    int tamaño;
    char[] items;
    int i;

    Pila(int tamaño){
        this.tamaño=tamaño;
        this.items=new char[tamaño];
        this.i=0;
    }
    public boolean push(char item){
        if (i<tamaño){
            items[i++]=item;
            return true;
        }

        return false;

    }
    public char pop(){
        if(i<=0)
            return 0;
        return items[--i];

    }
    public char nextPop(){
        if(i<=0)
            return 0;
        return items[i-1];
    }

}

