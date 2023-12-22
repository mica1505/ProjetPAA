package up.mi.cm.sg.gui;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import up.mi.cm.sg.AgglomerationGUI;
/**
 * Classe qui represente le panneau qui va contenir l'affichage des villes de notre agglomeration 
 * suivi des noms des villes qui possedent une zone de recharge
 * @author 
 *
 */
public class AfficherPane extends FlowPane{
	public AfficherPane(Stage s,int numMenu) {
		TextArea ta = new TextArea();
		//boutton b pour revenir au menu d'avant
		Button b = new Button("Menu");
		ta.setText(AgglomerationGUI.agg.allZones()+"\n"+AgglomerationGUI.agg.toString());
		this.getChildren().addAll(ta,b);
		
		b.setOnAction(event->{
			if(numMenu == 0) {
				s.setScene(AgglomerationGUI.menuAuto);
			}
			if(numMenu == 1) {
				s.setScene(AgglomerationGUI.menu1);
			}
			//si on etait dans SecondMennu le bouton nous renvoie dans le SecondMenu
			if(numMenu == 2) {
				s.setScene(AgglomerationGUI.menu2);
			}
			//si on etait dans MainPane(menu principal) le bouton nous renvoie dans le MainPane
			if(numMenu == 3) {
				s.setScene(AgglomerationGUI.menuStart);
			}
		});
	}
}