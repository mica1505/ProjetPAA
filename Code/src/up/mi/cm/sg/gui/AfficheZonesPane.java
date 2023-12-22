package up.mi.cm.sg.gui;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import up.mi.cm.sg.AgglomerationGUI;
/**
 * Classe qui represente le panneau qui va contenir l'affichage des noms des villes qui possedent une zone de recharge
 * On l'affiche apres chaque ajout/suppression de zones
 * @author 
 *
 **/
public class AfficheZonesPane extends FlowPane{
	public AfficheZonesPane(Stage s) {
		TextArea ta = new TextArea();
		//boutton de reotur au menu d'avant
		Button b = new Button("Menu");
		ta.setText(AgglomerationGUI.agg.allZones());
		
		
		b.setOnAction(event->{
			//on revient sur le second menu
			s.setScene(AgglomerationGUI.menu2);
		});
		this.getChildren().addAll(ta,b);
	}
}
