/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Lado;
import static cern.clhep.Units.g;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import javax.swing.JFrame;
import org.apache.commons.collections15.Transformer;
import sun.security.pkcs11.wrapper.CK_MECHANISM_INFO;


/**
 *  @author Yaqueline
 * @author Alejandro
 */
public class DrawGraph {

    private Graph<String, String> grafo;
    private BasicVisualizationServer<Integer, String> vv;
    private Transformer<String, Paint> edgeTransformer;
    private String[] idNodos;
    private String[] nombreNodos;
    private Lado[] lados;
    private String[] caminos;
    

    public DrawGraph(String[] idNodos, String[] nombresNodos, Lado[] lados) {
        grafo = new SparseMultigraph<String, String>();
        this.idNodos = idNodos;
        this.nombreNodos = nombresNodos;
        this.lados = lados;
        this.caminos = null;
        
        
        initVertices(null);
        initAristas();
        pintarRutas(null);
    }

   
/**
 * pinta las aristas de color negro
 * @param l 
 */
    public void pintarRutas(final String[] l) {
        
        edgeTransformer = new Transformer<String, Paint>() {
            @Override
            public Paint transform(String i) {
               
                return Color.BLACK;
            }
            
        };
        int i = 0;

        }
/**
 * pinta el grafo en el layout
 * @return 
 */
    public BasicVisualizationServer showGraph() {
        Layout<Integer, String> layout = new CircleLayout(grafo);
        layout.setSize(new Dimension(250, 250)); // sets the initial size of the layout space
        // The BasicVisualizationServer<V,E> is parameterized by the vertex and edge types
        vv = new BasicVisualizationServer<Integer, String>(layout);
        vv.setPreferredSize(new Dimension(350, 350)); //Sets the viewing area size    
        vv.getRenderContext().setEdgeDrawPaintTransformer(edgeTransformer);
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        /*JFrame frame = new JFrame("Grafo");
        frame.setSize(300, 300);
        frame.getContentPane().add(vv);
        frame.setVisible(true);*/

        return vv;
    }
/**
 * inicializa los vertices del grafo que se va a dibubar 
 * @param vertices 
 */
    public void initVertices(String[] vertices) {
        for (int i = 0; i < idNodos.length; i++) {
            grafo.addVertex(idNodos[i] + " - " + nombreNodos[i]);
        }
        /*grafo.addVertex("1");
        grafo.addVertex("2");
        grafo.addVertex("3");*/

    }
/**
 * inicializa las aristas del grafo que se va a dibujar
 */
    public void initAristas() {
        for (int i = 0; i < lados.length; i++) {
            String liString = lados[i].getLi().toString();
            String ldString = lados[i].getLd().toString();
            String li = liString + " - " + nombreNodos[Integer.valueOf(liString)];
            String ld = ldString + " - " + nombreNodos[Integer.valueOf(ldString)];
            String arista = String.valueOf(i);

            grafo.addEdge(arista, li, ld);
        }
        /*grafo.addEdge("Arista 1", "1", "2");
        grafo.addEdge("Arista 2", "1", "3");*/

    }

}
