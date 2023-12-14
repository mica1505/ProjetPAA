package up.mi.cm.sg.gui;

import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import up.mi.cm.sg.AgglomerationGUI;

public class AddZone extends FlowPane{
	public AddZone(Stage stage) {
		this.setOrientation(Orientation.VERTICAL);
		Button add = new Button("Ajouter");
		//afficher le message d'erreur
		Label msg = new Label("");
		
		TextField city1 = new TextField();
		
		add.setOnAction(event->{
			try {
				if(AgglomerationGUI.agg.getCity(city1.getText().toUpperCase())!=null) {
					AgglomerationGUI.agg.addZone(AgglomerationGUI.agg.getCity(city1.getText().toUpperCase()));
				}
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
			//on revient sur le menu de depart
			stage.setScene(new Scene(new AfficheZonesPane(stage)));
		});
		
		this.getChildren().addAll(new Label("Nom de la ville "),city1,add);
	}
}
