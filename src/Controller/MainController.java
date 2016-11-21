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

/**
 *
 * @author Alejandro
 */
public class MainController {
    private MainFrame mainFrame ;
    private Dimension dimension;
    private ManejadorArchivo mArchi;
    private Herramienta herramienta;
    private Grafo grafo;
    private DrawGraph g;
    
    
    public MainController() {
        dimension = Toolkit.getDefaultToolkit().getScreenSize();
        this.mainFrame = new MainFrame(this);
        //this.MainFrame.setSize(dimension);
        //this.MainFrame.setExtendedState(MainFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        this.mainFrame.setVisible(true);
        mArchi = new ManejadorArchivo();
        herramienta = new Herramienta();
    }
    
    public static void main(String[] args) {
        MainController mController = new MainController();
       
    }
    
    public void clickTodosLosCamino(String nodoA, String nodoB)
    {
        
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
        grafo.obtenerCaminos(0, 5, 0);
        mainFrame.limpiarPanel();
        mainFrame.setTextToTextArea(herramienta.textoTextArea(lineas));
        mainFrame.crearRadioButtons(grafo.getIdStringsNodos(),grafo.getNombresNodo());
        
        
        g = new DrawGraph(grafo.getIdStringsNodos(),grafo.getNombresNodo(),grafo.getLados());
        
        mainFrame.addGrafo(g.showGraph());
        
        mainFrame.setEnableButtons(true);
       
      
    }
    
    
}
