package up.mi.cm.sg.gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class MainPane extends FlowPane{

	public MainPane(Stage stage) {
		this.setOrientation(Orientation.VERTICAL);
		this.setAlignment(Pos.CENTER);
		Button man = new Button("Resoudre manuellement");
		Button auto = new Button("Resoudre automatiquement");
		Button end = new Button("Fin");
		
		man.setOnAction(event->{
			//redirection vers combien de villes menu
			stage.setScene(new Scene(new CreateAgg(stage),300,300));
		});
		
		auto.setOnAction(event->{
			stage.setScene(new Scene(new AutoResolutionPane(stage),300,300));
		});
		
		end.setOnAction(event->{
			System.exit(1);
		});
		
		this.getChildren().addAll(man,auto,end);
	}
	
}
