package up.mi.cm.sg.gui;

import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import up.mi.cm.sg.AgglomerationGUI;
import up.mi.cm.sg.ExeptionChangesArea;
/**
 * Classe qui represente le paneau qui gere l'ajout d'une zone de recharge dans une ville
 * @author 
 *
 */
public class AddZone extends FlowPane{
	public AddZone(Stage stage) {
		this.setOrientation(Orientation.VERTICAL);
		Button add = new Button("Ajouter");
		//saisie du nom de la ville
		
		TextField city1 = new TextField();
		
		add.setOnAction(event->{
			//on essaye d'ajouter une zone de recharge a une ville ville, 
			//si la ville possede deja une de zone
			//une ExeptionChangesArea est levee
			try {
				//si la ville existe on lui ajoute une zone de recharge
				if(AgglomerationGUI.agg.getCity(city1.getText())!=null) {
					AgglomerationGUI.agg.addZone(AgglomerationGUI.agg.getCity(city1.getText()));
				}
				else {
					System.out.println("Ville introuvable. Veuillez saisir un nom de ville valide.");
				}
			}
			catch(ExeptionChangesArea e) {
				System.out.println(e.getMessage());
			}
			//on affiche les villes contiennent des zones de recharge
			stage.setScene(new Scene(new AfficheZonesPane(stage)));
		});
		
		this.getChildren().addAll(new Label("Nom de la ville "),city1,add);
	}
}
