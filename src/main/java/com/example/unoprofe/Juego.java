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
public class Juego {
    private List<Jugador> jugadores;
    private Partida partidaActual;

    public Juego() {
        jugadores = new ArrayList<>();
        // Inicialización adicional si es necesaria
    }

    public void agregarJugador(Jugador jugador) {
        jugadores.add(jugador);
    }

    public void iniciarNuevaPartida() {
        // Asegúrate de tener suficientes jugadores, etc.
        partidaActual = new Partida(jugadores);
        jugarPartida();
    }

    private void jugarPartida() {
        while (!partidaActual.esTerminada()) {
            partidaActual.jugarTurno();
            // Mostrar estado del juego, si es necesario
            // Puede involucrar actualización de la interfaz de usuario
        }

        // Procesar el fin de la partida, determinar ganador, etc.
    }

    // Métodos adicionales, como para configuraciones del juego, reiniciar juego, etc.
}


