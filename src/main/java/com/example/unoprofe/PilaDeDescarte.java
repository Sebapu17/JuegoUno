package com.example.unoprofe;

import java.util.Stack;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author sebastianpuppo
 */
public class PilaDeDescarte {
    private Stack<CartaUno> pilaDescarte;

    public PilaDeDescarte() {
        pilaDescarte = new Stack<>();
    }

    public void agregarCarta(CartaUno carta) {
        pilaDescarte.push(carta);
    }

    public CartaUno verUltimaCarta() {
        if (!pilaDescarte.isEmpty()) {
            return pilaDescarte.peek();
        }
        return null;
    }

    public boolean estaVacia() {
        return pilaDescarte.isEmpty();
    }

    public Stack<CartaUno> vaciarPilaExceptoUltima() {
        Stack<CartaUno> cartasParaReutilizar = new Stack<>();
        CartaUno ultimaCarta = pilaDescarte.pop();

        while (!pilaDescarte.isEmpty()) {
            cartasParaReutilizar.push(pilaDescarte.pop());
        }

        pilaDescarte.push(ultimaCarta); // Volver a poner la última carta
        return cartasParaReutilizar;
    }

    // Otros métodos que puedan ser necesarios
}


