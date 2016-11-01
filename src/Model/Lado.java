/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Alejandro
 */
public class Lado {
    private int costo;
    private Vertice vi;
    private Vertice vj;

    public Lado(Vertice vi, Vertice vj) {
        this.vi = vi;
        this.vj = vj;
    }
    
    

    public int getCosto() {
        return costo;
    }

    
    
    
    
    
}
