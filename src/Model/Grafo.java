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
 *  @author Yaqueline
 * @author Alejandro
 */
public class Grafo {
    private Object[] vec;
    private String[] vecString;
    private Lado[] lados;
    private int [][] adya;
    private ArrayList<Integer> recorrido;
    private ArrayList<ArrayList<Integer>> recorridos;
    private int[] visitados;
    private Lado[] ladosRecorrido;

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
    
    /**
     * este metodo me valida  si el tamaño de las palabras tien una diferencia
     * mayor a uno devuelve falso, si es igual pero difieron en mas de 1 letra,
     * falso, si alguno de los dos nodos tiene una letra mas el principio de la
     * palabra debe ser la misma la unica letra que puede cambiar es la ultima
     * en la palabra de mayor longitud
     * @param nodo1
     * @param nodo2
     * @return 
     */
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
/**
 * esta funcion reinicia los arraysList que contiene la informacion de los camino
 * @param a nodo a
 * @param b nodo b
 * @param count contador que siempre debe ir en cero
 */
    public void obtenerCaminos(int a, int b, int count)
    {
        recorrido = new ArrayList<Integer>();
        recorridos = new ArrayList<ArrayList<Integer>>();
        obtenerCamino(a, b, 0);
    }
   
    /**
     * Con este metodo se obtienen todos los caminos de un nodo a a un nodo b
     * @param a
     * @param b
     * @param count siempre enviar en 0
     */
    private void obtenerCamino(int a, int b, int count)
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
                        
                        recorrido.add(i);
                        guardarRecorrido();
                        recorrido.remove(count);
                    }
                    else
                    {
                        obtenerCamino(i, b, count);
                    }   
                }

                
            }
            p = p.getLink();
        }
        
        recorrido.remove(count-1);
        count--;
        visitados[a] = 0;
              
    }

    /**
     * este metodo crea un nuevo array list para hacer una copia y guardarlo en
     * el arrayList de arrayList
     */
    private void guardarRecorrido()
    {
        ArrayList<Integer> arra = new ArrayList<Integer>();
        Iterator<Integer> nombreIterator = recorrido.iterator();
            while(nombreIterator.hasNext())
            {
                arra.add(nombreIterator.next());
            }
            recorridos.add(arra);
    }
    /**
     * este metodo genera todos los recorridos en un array de string donde cada
     * recorrido es un string
     * @return 
     */
    public String[] stringRecorridos()
    {
        String[] strings = new String[recorridos.size()];
        int i = 0;
        Iterator<ArrayList<Integer>> iterator = recorridos.iterator();
        while (iterator.hasNext()) {
            ArrayList<Integer> ar = iterator.next();
            Iterator<Integer> nombreIterator = ar.iterator();
            String s = "";
            while(nombreIterator.hasNext())
            {
                s = s + String.valueOf(nombreIterator.next()) +"  ";
            }
            strings[i] = s;
            i++;
        }
          
        return strings;
    }
    /**
     * este metodo convierte los recorrido a un vector de objetos y cada
     * este en cada posicion tiene un vector con el recorrido
     * @return 
     */
    public Object[] Recorridos()
    {
        Object[] object = new Object[recorridos.size()];
        int i = 0;
        int j = 0;
        Iterator<ArrayList<Integer>> iterator = recorridos.iterator();
        while (iterator.hasNext()) {
            ArrayList<Integer> ar = iterator.next();
            Iterator<Integer> nombreIterator = ar.iterator();
            int[] s = new int[ar.size()];
            while(nombreIterator.hasNext())
            {
                s[j] = nombreIterator.next();
                j++;
            }
            j=0;
            object[i] = s;
            i++;
        }
          
        return object;
    }
    
    /**
     * me retorna el id del los lados del recorrido en un vector string
     * @param recorrido contiene en un arreglo de enteros los vertices del
     * recorrido
     * @return retorna un vector de strings con los ids de los lados del 
     * recorrido
     */
    public String[] rocorridoToidLados(int [] recorrido)
    {
        int n = recorrido.length;
        String[] ladoString = new String[n-1];
        ladosRecorrido = new Lado[n-1];
        Lado[] lados = this.lados;
        for (int i = 0; i < n-1 ; i++) 
        {
            int nodoA = recorrido[i];
            int nodoB = recorrido[i+1];
            for (int j = 0; j < lados.length; j++) 
            {
                Lado l = this.lados[j];
                Lado la = new Lado(nodoA,nodoB);
                Lado lb = new Lado(nodoB,nodoA);
                if (l.equals(la) || l.equals(lb)) {
                    ladoString[i] = String.valueOf(j);
                    if (l.equals(la))
                    {
                        ladosRecorrido[i] = la;
                    }
                    else
                    {
                        ladosRecorrido[i] = lb;
                    }
                }
                
            } 
        }
        return ladoString;
    }
    /**
     * obtiene los lados correspondientes al recorrido que se le ingresa,
     * si por ejemplos se entra 0,1,2 entrega los lados 0-1, 2-1
     * @param recorrido vector de enteros con el id del vertice
     * @return 
     */
    public Lado[] lasdosRecorridoLados(int [] recorrido)
    {
        String[] s = rocorridoToidLados(recorrido);
        
        return ladosRecorrido;
    }
    
    /**
     * 
     */
    public Object[] ladosMasCortos(int a,int b)
    {
        Lado[] lados;
        recorrido = new ArrayList<Integer>();
        recorridos = new ArrayList<ArrayList<Integer>>();
        obtenerCamino(a,b,0);
        Object[] allCaminos = this.Recorridos();
        int n = vec.length;
        for (int i = 0; i < allCaminos.length; i++) {
            
            int[] camino = (int[])allCaminos[i];
            if (n > camino.length) {
                n = camino.length;
            }
        }
        int m = 0;
        for (int i = 0; i < allCaminos.length; i++) {
            
            int[] camino = (int[])allCaminos[i];
            if (n == camino.length) {
                m++;
            }
        }
        Object[] object = new Object[m];
        m = 0;
        for (int i = 0; i < allCaminos.length; i++) {
            int[] camino = (int[])allCaminos[i];
            if (n == camino.length) {
                object[m] = camino;
                m++;
            } 
        }
        return object;
    }
}
