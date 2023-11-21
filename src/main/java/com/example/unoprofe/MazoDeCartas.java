/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.unoprofe;

import java.util.Collections;
import java.util.Stack;

/**
 *
 * @author sebastianpuppo
 */
public class MazoDeCartas {
    private Stack<CartaUno> cartas;

    public MazoDeCartas() {
        cartas = new Stack<>();
        inicializarMazo();
    }

    private void inicializarMazo() {
        // Añadir cartas numéricas
        for (CartaUno.Color color : CartaUno.Color.values()) {
            if (color == CartaUno.Color.NEGRO) continue; // Saltar comodines por ahora

            // Una carta 0 de cada color
            cartas.add(new CartaUno(color, CartaUno.Tipo.NUMERO, 0));

            // Dos cartas de cada número del 1 al 9, de cada color
            for (int i = 1; i <= 9; i++) {
                cartas.add(new CartaUno(color, CartaUno.Tipo.NUMERO, i));
                cartas.add(new CartaUno(color, CartaUno.Tipo.NUMERO, i));
            }

            // Añadir cartas de acción (dos de cada tipo por color)
            for (CartaUno.Tipo tipoAccion : new CartaUno.Tipo[]{CartaUno.Tipo.SALTO, CartaUno.Tipo.REVERSA, CartaUno.Tipo.TOMA_DOS}) {
                cartas.add(new CartaUno(color, tipoAccion, -1));
                cartas.add(new CartaUno(color, tipoAccion, -1));
            }
        }

        // Añadir comodines (cuatro de cada tipo)
        for (int i = 0; i < 4; i++) {
            cartas.add(new CartaUno(CartaUno.Color.NEGRO, CartaUno.Tipo.COMODIN, -1));
            cartas.add(new CartaUno(CartaUno.Color.NEGRO, CartaUno.Tipo.COMODIN_TOMA_CUATRO, -1));
        }
    }

    public void mezclar() {
        Collections.shuffle(cartas);
    }

    public CartaUno sacarCarta() {
        if (cartas.isEmpty()) {
            // Manejar caso en que el mazo esté vacío (posiblemente rellenar con la pila de descarte)
            return null;
        }
        return cartas.pop();
    }
    
    public void agregarCartaAlFinal(CartaUno carta) {
        Stack<CartaUno> cartasTemporales = new Stack<>();
        
        // Transferir todas las cartas a una pila temporal
        while (!cartas.isEmpty()) {
            cartasTemporales.push(cartas.pop());
        }

        // Agregar la carta al final (que ahora es el tope de la pila temporal)
        cartasTemporales.push(carta);

        // Volver a colocar todas las cartas en la pila original
        while (!cartasTemporales.isEmpty()) {
            cartas.push(cartasTemporales.pop());
        }
    }

    // Métodos adicionales, como verificar si el mazo está vacío
    public boolean estaVacio() {
        return cartas.isEmpty();
    }
}

