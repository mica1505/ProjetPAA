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
	public List<City> CA;

	@Override
	public void start(Stage primaryStage) throws Exception {
		CA = AgglomerationGUI.agg.getCA();
		Graph graph = new SingleGraph("tutorial 1");

		
        graph.setAutoCreate(true);
        graph.setStrict(false);
        
        for(City c : CA) {
        	Node n = graph.addNode(c.getName());
        	n.setAttribute("ui.style", c.getZone() ? "fill-color: green;" : "fill-color: red;");
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
}
