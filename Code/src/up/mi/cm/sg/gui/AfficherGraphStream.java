package up.mi.cm.sg.gui;


import java.util.ArrayList;
import java.util.List;
import java.awt.event.WindowEvent;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.fx_viewer.FxViewer;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.view.ViewerPipe;

import javafx.application.Application;
import javafx.stage.Stage;
import up.mi.cm.sg.AgglomerationGUI;
import up.mi.cm.sg.City;

public class AfficherGraphStream extends Application {
	private Viewer fxViewer;
	public List<City> CA;

	@Override
	public void start(Stage primaryStage) throws Exception {
		CA = AgglomerationGUI.agg.getCA();
		Graph graph = new SingleGraph("tutorial 1");

		
        graph.setAutoCreate(true);
        graph.setStrict(false);
        
        for(City c : CA) {
        	Node n = graph.addNode(c.getName());
        	n.setAttribute("ui.class", c.getZone() ? "Red" : "Blue");
        }
        String nameCity, nameVoisin;
        
        for(City c : CA) {
        	nameCity = c.getName();
        	for(City v : c.getCities()) {
        		nameVoisin = v.getName();
        		graph.addEdge(nameCity+nameVoisin, nameCity, nameVoisin);      		
        	}
        }
        

        for (Node node : graph) {
            node.setAttribute("label", "Noeud " + node.getId());
        }
        
        System.setProperty("org.graphstream.ui", "swing");
        graph.setAttribute("ui.stylesheet", "node.Red { fill-color: red; } node.Blue { fill-color: blue; }");
		graph.display();

        
    }

    private void handleClose() {
    	System.out.println("la fermeture");
        if (fxViewer != null) {
            fxViewer.close(); // Fermer le FxViewer (la fenêtre GraphStream)
            fxViewer = null; // Réinitialiser la référence
        }

	}

}
