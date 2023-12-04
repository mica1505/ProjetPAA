package up.mi.cm.sg.gui;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class SecondMenuPane extends FlowPane{
	public SecondMenuPane(Stage stage) {
		this.setOrientation(Orientation.VERTICAL);
		this.setAlignment(Pos.CENTER);
		
		Button add = new Button("Ajouter une zone");
		Button remove = new Button("Supprimer une zone");
		Button print = new Button("Afficher");
		Button end = new Button("Fin");
		
		
		add.setOnAction(event->{
			stage.setScene(new Scene(new AddZone(stage),300,300));
		});
		
		remove.setOnAction(event->{
			stage.setScene(new Scene(new RemoveZone(stage),300,300));
		});
		
		end.setOnAction(event->{
			System.exit(1);
		});
		
		print.setOnAction(event->{
			stage.setScene(new Scene(new AfficherPane(stage,2)));
		});
		
		this.getChildren().addAll(add,remove,print,end);
	}
}
