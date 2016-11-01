/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.List;

/**
 *
 * @author Alejandro
 */
public class Vertice {
    private int id;
    private String nombre;
    private List<Lado> adyacentes;

    /**
     * El constructor de la Clase Vertice
     * @param id tipo int es numero para identificar el vertice
     * @param nombre tipo String es el nombre del nodo del grafo
     * @param lineas tipo List<Lado> 
     */
    public Vertice(int id, String nombre, String[] lineas) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    
    
}
