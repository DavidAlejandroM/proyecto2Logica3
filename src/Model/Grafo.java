/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import Model.SNode;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Alejandro
 */
public class Grafo {
    private Object[] vec;
    private String[] vecString;
    private Lado[] lados;
    private int [][] adya;
    private ArrayList<Integer> recorrido;
    private ArrayList<ArrayList<Integer>> recorridos;

    public Grafo(String []v) {
        vec = new Object[v.length];
        visitados = new int[v.length];
        for (int i = 0; i < vec.length; i++) {
            vec[i] = null;
            visitados[i] = 0;
        }
        vecString = v;
        conectarGrafo();
        crearLados();
        recorrido = new ArrayList<Integer>();
        recorridos = new ArrayList<ArrayList<Integer>>();
    }
   
    private void conectarGrafo() {
        for (int i = 0; i < vec.length; i++) {
            for (int j = 0; j < vecString.length; j++) {
                if(i!=j && comparar(vecString[i],vecString[j]))
                {
                    SNode s = new SNode(j);
                    SNode q = null;
                    if(vec[i] == null)
                    {
                        vec[i] = s;
                    }
                    else
                    {
                        SNode p = (SNode) vec[i];
                        while(p!=null)
                        {
                            q = p;
                            p = p.getLink();
                        }
                        q.setLink(s);
                    }
                }
            }
        }
    }

    private boolean comparar(String nodo1, String nodo2) {
        int tam1 = nodo1.length();
        int tam2 = nodo2.length();
        boolean b = false;
        if(tam1 == tam2)
        { 
            int count = 0;
            
            for (int i = 0; i < tam1; i++) {
                if(nodo1.charAt(i) != nodo2.charAt(i))
                {
                    count++;
                }
            }
            if (count>1)
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        else if((tam1 == tam2+1)||(tam2 == tam1+1))
        {
            if (tam1 < tam2) {
                for (int i = 0; i < tam1; i++) {
                    if (nodo1.charAt(i) != nodo2.charAt(i)) {
                        return false;        
                    }
                }
                return true;
            }
            else
            {
                for (int i = 0; i < tam2; i++) {
                    if (nodo1.charAt(i) != nodo2.charAt(i)) {
                        return false;        
                    }
                }
                return true;
            }
        }
        else
        {
           return false; 
        }   
    }
    /**
     * Este metodo imprime por consola el grafo
     */
    public void imprimirGrafo()
    {
        System.out.println(this.toString());
    }
    /**
     * Este metodo convierte el grafo a un string para visualizarlo mejor
     * @return 
     */
    public String toString()
    {
        String s = "";
        for (int i = 0; i < vec.length; i++) {
            if (vec[i] == null) {
                  s = s + "["+String.valueOf(i)+"]->null\n";              
            }
            else
            {
                s = s + "["+String.valueOf(i)+"]";
                String s1 = "";
                SNode p = (SNode) vec[i];
                while (p != null)
                {                    
                    s1 = s1 + " -> "+String.valueOf((int) p.getData());
                    p = p.getLink();
                }
                s = s + s1 + "\n";
            }
        }
        return s;
    }
    /**
     * Este metodo retorna un vector con los id de los nodos del grafo en
     * tip String
     * @return 
     */
    public String[] getIdStringsNodos()
    {
        int n = vecString.length;
        String[] v = new String[n];
        
        for (int i = 0; i < n; i++) {
            v[i] = String.valueOf(i);
        }
        return v;
    }
    
    public Lado[] getLados()
    {
        return lados;
    }
    
    /**
     * Este metodo retorna un vector con los nombres de cada nodo siendo la
     * posicion de cada nombre el id de cada nodo
     * @return 
     */
    public String[] getNombresNodo()
    {
        return vecString;
    }
    
    
    /**
     * Este metodo crea todos los lados del grafo
     */
    private void crearLados() 
    { 
        ArrayList<Lado> array = new ArrayList<Lado>();
        Lado[] lados;
        int j = 0;
        for (int i = 0; i < vec.length; i++) {
            if (vec[i] != null) {
                SNode p =(SNode) vec[i];
                Object object = i;
                while (p != null)
                {
                    Lado l = new Lado(object,p.getData());
                    if (!validarLado(l,array)) {
                        array.add(l);
                    }
                    p = p.getLink();
                }
            }
        }
        
        lados = new Lado[array.size()];
        
        Iterator<Lado> nombreIterator = array.iterator();
            while(nombreIterator.hasNext())
            {
                Lado elemento = nombreIterator.next();
                lados[j] = elemento;
                j++;
            }
            this.lados = lados;
        
    }
    /**
     * valida si el lado ya esta existe en el ArrayList
     * @param l Lado
     * @param array ArrayLis de Lado
     * @return 
     */
    private boolean validarLado(Lado l, ArrayList<Lado> array) {
        if (array.isEmpty()) {
            return false;
        }
        else
        {
            Iterator<Lado> nombreIterator = array.iterator();
            while(nombreIterator.hasNext())
            {
                Lado elemento = nombreIterator.next();
                if (l.equals(elemento)) {
                    return true;
                }
                else
                {
                    if (elemento.getLi().equals(l.getLd()) && elemento.getLd().equals(l.getLi())) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    int[] visitados;
    public void obtenerCaminos(int a, int b, int count)
    {   
        count++;
        recorrido.add(a);
        visitados[a] = 1;
        SNode p =(SNode) vec[a];
        while (p != null) {
           if (vec[a] == null && vec[b] == null) {
            System.out.println("no se puede llegar");   
            }
            else
            {
                int i = (int) p.getData();
                if (visitados[i] == 0 ) {
                    if (i == b) {
                        //guardarRecorrido(i);
                        recorrido.add(i);
                        recorridos.add(recorrido);
                    }
                    else
                    {
                        obtenerCaminos(i, b, count);
                    }   
                }

                
            }
            p = p.getLink();
        }
        
        recorrido.remove(count-1);
        count--;
        visitados[a] = 0;
              
    }

    
    private void guardarRecorrido(int b)
    {
        int n = 0;
        String s = "";
        String[] recorrido;
        for (int i = 0; i < visitados.length; i++) {
            if (visitados[i] == 1) {
                n++;
            }
        }
        recorrido = new String[n+1];
        n = 0;
        for (int i = 0; i < visitados.length; i++) {
            if(visitados[i] == 1)
            {
                recorrido[n] = String.valueOf(i);
                n++;
                s = s + String.valueOf(i)+" - ";
            }
        }
        recorrido[n+1] = String.valueOf(b);
        System.out.println(s + String.valueOf(b));
    }
    
    private void crearMatrizAdyacencia()
    {
        
    }
    
    
}
