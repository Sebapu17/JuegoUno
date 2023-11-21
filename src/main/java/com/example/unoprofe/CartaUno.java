/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.unoprofe;

/**
 *
 * @author sebastianpuppo
 */
public class CartaUno {
    public enum Color {
        ROJO, GREEN, BLUE, YELLOW, NEGRO; // Negro para comodines
    }

    public enum Tipo {
        NUMERO, SALTO, REVERSA, TOMA_DOS, COMODIN, COMODIN_TOMA_CUATRO;
    }

    private Color color;
    private Tipo tipo;
    private int numero; // Usar -1 para cartas que no tienen número

    public CartaUno(Color color, Tipo tipo, int numero) {
        this.color = color;
        this.tipo = tipo;
        this.numero = numero;
    }

    // Getters
    public Color getColor() {
        return color;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public int getNumero() {
        return numero;
    }

    // Setters
    public void setColor(Color color) {
        this.color = color;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

@Override
public String toString() {
    StringBuilder representacion = new StringBuilder();

    switch (tipo) {
        case NUMERO:
            representacion.append(numero); // Para cartas numéricas
            break;
        case SALTO:
            representacion.append("skip"); // Para cartas de Salto
            break;
        case REVERSA:
            representacion.append("_"); // Para cartas de Reversa
            break;
        case TOMA_DOS:
            representacion.append("D2"); // Para cartas de Toma Dos
            break;
        case COMODIN:
            return "W.png"; // Para Comodines
        case COMODIN_TOMA_CUATRO:
            return "D4W.png"; // Para Comodines Toma Cuatro
        default:
            representacion.append("Desconocida");
            break;
    }

    // Añadir la primera letra del color en mayúscula, si la carta no es un comodín
    if (tipo != Tipo.COMODIN && tipo != Tipo.COMODIN_TOMA_CUATRO) {
        representacion.append(Character.toUpperCase(color.toString().charAt(0)));
    }

    return representacion.toString() + ".png";
}

    // Método para verificar si una carta puede ser jugada sobre otra
    public boolean puedeSerJugadaSobre(CartaUno otraCarta) {
        return this.color == otraCarta.color || 
               this.numero == otraCarta.numero || 
               this.tipo == otraCarta.tipo || 
               this.color == Color.NEGRO; // Comodines pueden jugarse sobre cualquier carta
    }
}


