/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.unoprofe;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sebastianpuppo
 */
public abstract class Jugador {
    protected String nombre;
    protected List<CartaUno> mano;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.mano = new ArrayList<>();
    }

    public void tomarCarta(CartaUno carta) {
        mano.add(carta);
    }

    public List<CartaUno> getMano() {
        return mano;
    }

    public abstract CartaUno elegirCartaParaJugar(CartaUno cartaSuperiorPilaDescarte);

    // Otros métodos comunes, como verificar si el jugador tiene cartas que puede jugar, etc.
}


