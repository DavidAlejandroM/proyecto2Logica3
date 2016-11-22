/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Grafo;
import Model.Herramienta;
import Model.ManejadorArchivo;
import View.DrawGraph;
import View.MainFrame;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import javax.swing.JFrame;
import Model.Lado;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *  @author Yaqueline
 * @author Alejandro
 */
public class MainController {
    private MainFrame mainFrame ;
    private Dimension dimension;
    private ManejadorArchivo mArchi;
    private Herramienta herramienta;
    private Grafo grafo;
    private DrawGraph g;
    private DrawGraph g2;
    private int count;
    Object[] ladoObjects;
    
    
    
    
    public MainController() {
        dimension = Toolkit.getDefaultToolkit().getScreenSize();
        this.mainFrame = new MainFrame(this);
        //this.MainFrame.setSize(dimension);
        //this.MainFrame.setExtendedState(MainFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        this.mainFrame.setVisible(true);
        mArchi = new ManejadorArchivo();
        herramienta = new Herramienta();
        count = -1;
    }
    
    public static void main(String[] args) {
        
        MainController mController = new MainController();
       
    }
    /**
     * se llama cuando se da en el boton de el mas corto y obtiene de la 
     * clase grafos el recorrido o los recorridos mas cortos y los envia
     * al panel de visualizacion
     * @param nodoA
     * @param nodoB 
     */
    public void clickElMasCorto(int nodoA, int nodoB)
    {
        
        
        count = -1;
        ladoObjects = grafo.ladosMasCortos(nodoA, nodoB);
        mainFrame.setEnableLast(true);
        mainFrame.setEnableNext(true);
        String[] s = grafo.stringRecorridos();
        String consola = herramienta.textoTextArea(s);
        mainFrame.setTextConsola(consola);
        clickNext();
    }
    /**
     * metodo que cambia el grafo para mostrar el recorrido siguiente
     */
    public void clickNext()
    {
        if (count < ladoObjects.length - 1) 
        {
            count++;
            pintarGrafo2();
            mainFrame.setEnableLast(true);
        }
        else
        {   
            if(grafo.Recorridos().length == 0)
            {
                mainFrame.removeGrafo2();
            }
            
            mainFrame.setEnableNext(false);
        }
  
    }
    /**
     * metodo que cambia el grafo para mostrar el recorrido anterior
     */
    public void clickLast()
    {
        if (count > 0) 
        {
            count--;
            pintarGrafo2();
            mainFrame.setEnableNext(true);
        }
        else
        {
            mainFrame.setEnableLast(false);
        }
    }
    /**
     * muestra el i-esimo recorrido en el grafo de visualizacion donde i es la
     * count
     */
    private void pintarGrafo2()
    {
            mainFrame.removeGrafo2();
            g2 = new DrawGraph(grafo.getIdStringsNodos(),grafo.getNombresNodo(),
                    grafo.lasdosRecorridoLados((int[])ladoObjects[count]));      
            mainFrame.addGrafo2(g2.showGraph());
            mainFrame.setEnableButtons(true);
    }
    /**
     * ejecuta el metodo todos los caminos enviando el nodoA el nodoB y un 
     * contador en cero (0) muestra los recorrido en consola
     * @param nodoA
     * @param nodoB 
     */
    public void clickTodosLosCamino(int nodoA, int nodoB)
    {
        count = -1;
        grafo.obtenerCaminos(nodoA, nodoB, 0);
        mainFrame.setEnableLast(true);
        mainFrame.setEnableNext(true);
        String[] s = grafo.stringRecorridos();
        String consola = herramienta.textoTextArea(s);
        mainFrame.setTextConsola(consola);
        ladoObjects = grafo.Recorridos();
        clickNext();
   
        
        
    }
    /**
     * Este mentodo crea un Grafo a partir de un archivo txt
     * donde en cada linea hay una palabra y esta palabra representa
     * un nodo del grafo
     * @param ruta
     * @throws FileNotFoundException 
     */
    public void crearGrafo(String ruta) throws FileNotFoundException
    {
        String[] lineas = mArchi.getLineasTexto(ruta);
        lineas = herramienta.normalizarStrings(lineas);
        
        grafo = new Grafo(lineas);
        grafo.imprimirGrafo();
        
        mainFrame.limpiarPanel();
        mainFrame.setTextToTextArea(herramienta.textoTextArea(lineas));
        mainFrame.crearRadioButtons(grafo.getIdStringsNodos(),grafo.getNombresNodo());
        
        
        
        
        g = new DrawGraph(grafo.getIdStringsNodos(),grafo.getNombresNodo(),grafo.getLados());
        
        mainFrame.addGrafo(g.showGraph());
        
        mainFrame.setEnableButtons(true);
       
      
    }
    
    
}
