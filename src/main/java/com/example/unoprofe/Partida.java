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
public class Partida {

    private MazoDeCartas mazo;
    private PilaDeDescarte pilaDescarte;
    private List<Jugador> jugadores;
    private int indiceJugadorActual;
    private boolean direccionJuego; // True para sentido horario, False para antihorario
    private CartaUno primeraCarta;

    public Partida(Jugador jugador) {
        this.mazo = new MazoDeCartas();
        this.pilaDescarte = new PilaDeDescarte();
        jugadores = new ArrayList<Jugador>();
        this.jugadores.add(jugador);
        this.jugadores.add(new JugadorIA("diego"));
        this.indiceJugadorActual = 0; // Comenzar con el primer jugador
        this.direccionJuego = true; // Sentido horario inicialmente
        this.primeraCarta = null;
        mazo.mezclar();
        repartirCartasIniciales();
        jugarPrimeraCarta();
    }

    public Partida(List<Jugador> jugadores) {
        this.mazo = new MazoDeCartas();
        this.pilaDescarte = new PilaDeDescarte();
        this.jugadores = jugadores;
        this.indiceJugadorActual = 0; // Comenzar con el primer jugador
        this.direccionJuego = true; // Sentido horario inicialmente
        this.primeraCarta = null;
        mazo.mezclar();
        repartirCartasIniciales();
        jugarPrimeraCarta();
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(Jugador jugador) {

        jugadores.add(jugador);
    }

    private void repartirCartasIniciales() {
        for (Jugador jugador : jugadores) {
            for (int i = 0; i < 7; i++) { // Cada jugador comienza con 7 cartas
                jugador.tomarCarta(mazo.sacarCarta());
            }
        }
    }

    public CartaUno getPrimeraCarta() {
        return primeraCarta;
    }

    public boolean getDireccionJuego() {
        return direccionJuego;
    }

    private void jugarPrimeraCarta() {
        // Asegurarse de que la primera carta no sea un comodín
        primeraCarta = mazo.sacarCarta();
        while (primeraCarta.getTipo() == CartaUno.Tipo.COMODIN || primeraCarta.getTipo() == CartaUno.Tipo.COMODIN_TOMA_CUATRO) {
            mazo.agregarCartaAlFinal(primeraCarta); // Volver a poner la carta en el mazo
            mazo.mezclar();
            primeraCarta = mazo.sacarCarta();
        }
        pilaDescarte.agregarCarta(primeraCarta);
    }

    public void jugarTurno() {
        Jugador jugadorActual = jugadores.get(indiceJugadorActual);
        CartaUno cartaJugada = jugadorActual.elegirCartaParaJugar(pilaDescarte.verUltimaCarta());

        if (cartaJugada != null) {
            pilaDescarte.agregarCarta(cartaJugada);
            aplicarEfectoCarta(cartaJugada);
        } else {
            jugadorActual.tomarCarta(mazo.sacarCarta());
        }

        avanzarAlSiguienteJugador();
    }

    private void aplicarEfectoCarta(CartaUno cartaJugada) {
        switch (cartaJugada.getTipo()) {
            case SALTO:
                // Saltar el próximo jugador
                avanzarAlSiguienteJugador();
                break;
            case REVERSA:
                // Cambiar la dirección del juego
                direccionJuego = !direccionJuego;
                break;
            case TOMA_DOS:
                // El siguiente jugador toma dos cartas y pierde su turno
                avanzarAlSiguienteJugador();
                Jugador siguienteJugador = jugadores.get(indiceJugadorActual);
                siguienteJugador.tomarCarta(mazo.sacarCarta());
                siguienteJugador.tomarCarta(mazo.sacarCarta());
                break;
            case COMODIN:
                // El jugador actual elige un nuevo color
                // Nota: La implementación de cómo se elige el color dependerá de la interacción con el usuario o la IA
                break;
            case COMODIN_TOMA_CUATRO:
                // Similar al Comodín, pero el siguiente jugador toma cuatro cartas y pierde su turno
                // Además, el jugador actual elige un nuevo color
                avanzarAlSiguienteJugador();
                Jugador jugadorSiguiente = jugadores.get(indiceJugadorActual);
                for (int i = 0; i < 4; i++) {
                    jugadorSiguiente.tomarCarta(mazo.sacarCarta());
                }
                break;
            default:
                // No hay efecto especial para las cartas numéricas
                break;
        }
    }

    public boolean esTerminada() {
        for (Jugador jugador : jugadores) {
            if (jugador.getMano().isEmpty()) {
                // Si algún jugador se queda sin cartas, la partida termina
                return true;
            }
        }

        // Puedes agregar aquí otros criterios de finalización si lo deseas
        // Por ejemplo, un límite de tiempo o alcanzar una puntuación específica
        return false; // La partida continúa si ninguno de los jugadores se ha quedado sin cartas
    }

    private void avanzarAlSiguienteJugador() {
        if (direccionJuego) {
            indiceJugadorActual = (indiceJugadorActual + 1) % jugadores.size();
        } else {
            indiceJugadorActual = (indiceJugadorActual - 1 + jugadores.size()) % jugadores.size();
        }
    }

  
    // Otros métodos necesarios, como verificar si un jugador ha ganado, cambiar la dirección del juego, etc.
}
