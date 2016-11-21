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
public class Herramienta {

    public Herramienta() {
    }
    
    /**
     * esta funcion retorna el vector de strings todo en minusculas
     * @param v String[] de letras convinadas
     * @return  retorna todo el vector en minusculas
     */
    public String[] normalizarStrings(String[] v)
    {
        for (int i = 0; i < v.length; i++) {
            v[i]=v[i].toLowerCase();
        }
        return v;
    }
    
    public String textoTextArea(String[] lineas)
    {
        String s = "";
        for (int i = 0; i < lineas.length; i++) {
            s = s + lineas[i]+"\n";
        }
        return s;
    }

//    public String[] objetsToStrings(Object[] ladoObjects) {
//        int n = ladoObjects.length;
//        String[] ladosStrings = new String[n];
//        for (int i = 0; i < n; i++) {
//            String recorrido = "";
//            int[] camino = (int[]) ladoObjects[i];
//            int m = camino.length;
//            for (int j = 0; j < m; j++) {
//                recorrido = recorrido + String.valueOf(camino[i])+ " ";
//            }
//            ladosStrings[i] = recorrido;   
//        }
//        return ladosStrings;
//    }
    
}
