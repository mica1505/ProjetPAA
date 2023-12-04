package up.mi.cm.sg.gui;

import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import up.mi.cm.sg.AgglomerationGUI;
import up.mi.cm.sg.CA;

public class CreateAgg extends FlowPane{
	public CreateAgg(Stage stage) {
		this.setOrientation(Orientation.VERTICAL);
		Button create = new Button("Creer");
		//afficher le message d'erreur
		
		TextField city = new TextField();
		
		this.getChildren().addAll(new Label("Entrez le nombre de villes "),city,create);

		
		create.setOnAction(event->{
			AgglomerationGUI.agg = new CA(Integer.valueOf(city.getText()));
			//on revient sur le menu de depart
			stage.setScene(AgglomerationGUI.menu1);
		});
	}
}
