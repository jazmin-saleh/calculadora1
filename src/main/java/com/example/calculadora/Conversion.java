package com.example.calculadora;
import java.util.Stack;
public class Conversion {

    //convercion de infija a prefija
    public static StringBuilder conversionPrefijo(String expresion){
        StringBuilder eprefija = new StringBuilder();
        StringBuilder einfija = new StringBuilder(expresion);
        einfija.reverse();
        Stack<Character> stack = new Stack<>();
        char[] carexp = new String(einfija).toCharArray();

        for (int i =0; i< carexp.length; i++){
            if (carexp[i] =='('){
                carexp[i] =')';
                i++;
            }else if(carexp[i] == ')'){
                carexp[i] = '(';
                i++;
            }
        }

        for (int i =0; i< carexp.length; i++){
            char car = carexp[i];
            if (jerarquia(car) >0){
                while (stack.isEmpty()==false && jerarquia(stack.peek()) >= jerarquia(car)){
                    eprefija.append(" " +stack.pop());
                }
                stack.push(car);
            }else  if(car == ')'){
                char aux = stack.pop();
                while (aux != '('){
                    eprefija.append(" " +aux);
                    aux = stack.pop();
                }
            }else if(car == '('){
                stack.push(car);
            }else {
                eprefija.append(" " +car);
            }
        }

        for (int i =0; i<= stack.size(); i++){
            eprefija.append(" " +stack.pop());
        }
        return  eprefija.reverse();
    }

    public static int jerarquia (char car){
        switch (car){
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }

    //Conversion de infija a sufija(posfija)

    public static String conversionPostfijo(String expresion){
        String epostfija = "";
        Stack<Character> stack = new Stack<>();
        for (int i =0; i < expresion.length(); i++){
            char car = expresion.charAt(i);
            if (jerarquia(car) > 0){
                while (stack.isEmpty() == false && jerarquia(stack.peek()) >= jerarquia(car)){
                    epostfija += " " +stack.pop();
                }
                stack.push(car);
            }else if (car == ')'){
                char aux = stack.pop();
                while (aux != '('){
                    epostfija += " " +aux;
                    aux = stack.pop();
                }
            }else if (car == '('){
                stack.push(car);
            }else{
                epostfija += " " +car;
            }
        }
        for (int i =0; i < stack.size(); i++){
            epostfija += " " +stack.pop();
        }
        return  epostfija;
    }
}
