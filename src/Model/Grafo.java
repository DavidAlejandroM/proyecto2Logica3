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
public class Grafo {
    Vertice[] vertices;

    public Grafo(String []v) {
        int n = v.length;
        vertices = new Vertice[n];        
        for (int i = 0; i < n; i++) {
            vertices[i] = new Vertice(i, v[i],v);
        }
        
    }
    
    public void imprimirVerticesGrafo()
    {
        for (int i = 0; i < vertices.length; i++) {
            System.out.println(vertices[i].getNombre());
        }
    }
    
}
