package up.mi.cm.sg.gui;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import up.mi.cm.sg.AgglomerationGUI;

public class FirstMenuPane extends FlowPane{

	public FirstMenuPane(Stage stage) {
		this.setOrientation(Orientation.VERTICAL);
		this.setAlignment(Pos.CENTER);
		Button add = new Button("Ajouter une route");
		Button print = new Button("Afficher");
		Button fin = new Button("Fin");
		
		add.setOnAction(event->{
			//a verifier
			stage.setScene(new Scene(new AddCityPane(stage)));
			//on bascule sur un pane de sasie
		});
		
		fin.setOnAction(event->{
			//on passe au second menu
			stage.setScene(AgglomerationGUI.menu2);
		});
		print.setOnAction(event->{
			stage.setScene(new Scene(new AfficherPane(stage,1)));
		});
		this.getChildren().addAll(add,print,fin);
	}
}
