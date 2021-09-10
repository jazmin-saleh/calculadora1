package com.example.calculadora;

import java.util.Stack;

public class Conversion {

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

    public static String conversionPostfijo(String expresion) throws Error{
        String epostfija = "";
        Stack<Character> stack = new Stack<>();
        for (int i =0; i < expresion.length(); i++){
            char car = expresion.charAt(i);
            if (jerarquia(car) > 0){
                while (stack.isEmpty() == false && jerarquia(stack.peek()) >= jerarquia(car)){
                    epostfija += stack.pop();
                }
                stack.push(car);
            }else if (car == ')'){
                char aux = stack.pop();
                while (aux != '('){
                    epostfija += aux;
                    aux = stack.pop();
                }
            }else if (car == '('){
                stack.push(car);
            }else{
                epostfija += car;
            }
        }
        for (int i =0; i < stack.size(); i++){
            epostfija += stack.pop();
        }
        return  epostfija;
    }
}

class Error extends Exception{

    String fallo ;
    public Error(String mensaje) {
        fallo = mensaje;
    }



    public String toString() {
        return fallo;
    }

}
