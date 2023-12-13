package up.mi.cm.sg.gui;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import up.mi.cm.sg.AgglomerationGUI;
import up.mi.cm.sg.ParseAgglomeration;

public class AutoResolutionPane extends FlowPane{
	public AutoResolutionPane(Stage stage) {
		this.setOrientation(Orientation.VERTICAL);
		this.setAlignment(Pos.CENTER);
		TextField path = new TextField();
		
		Button naive = new Button("Resolution naive");
		Button naiveUpgraded = new Button("Resolution naive optimisee");
		Button upgrade = new Button("Resolution optimisee");
		Button print = new Button("Afficher");
		Button quit = new Button("Fin");

		naive.setOnAction(event->{
			AgglomerationGUI.agg = ParseAgglomeration.parseAgg(path.getText());
			AgglomerationGUI.agg.naiveSolutions(0);
			stage.setScene(new Scene(new AfficherPane(stage,0)));
		});
		
		naiveUpgraded.setOnAction(event->{
			AgglomerationGUI.agg = ParseAgglomeration.parseAgg(path.getText());
			AgglomerationGUI.agg.naiveSolutions2(0);
			stage.setScene(new Scene(new AfficherPane(stage,0)));
		});
		
		upgrade.setOnAction(event->{
			AgglomerationGUI.agg = ParseAgglomeration.parseAgg(path.getText());
			AgglomerationGUI.agg.algorithmeGlouton();
			stage.setScene(new Scene(new AfficherPane(stage,0)));
			
		});
			
		print.setOnAction(event->{
			AgglomerationGUI.agg = ParseAgglomeration.parseAgg(path.getText());
			stage.setScene(new Scene(new AfficherPane(stage,0)));
		});
		
		quit.setOnAction(event->{
			System.exit(1);
		});
		this.getChildren().addAll(new Label("Nom du fichier "),path,naive,naiveUpgraded,upgrade,print);
	}

}
