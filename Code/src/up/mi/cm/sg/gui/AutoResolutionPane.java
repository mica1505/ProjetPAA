package up.mi.cm.sg.gui;

import java.io.File;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import up.mi.cm.sg.AgglomerationGUI;
import up.mi.cm.sg.ParseAgglomeration;

public class AutoResolutionPane extends FlowPane{
	public AutoResolutionPane(Stage stage) {
		this.setOrientation(Orientation.VERTICAL);
		this.setAlignment(Pos.CENTER);
		
		FileChooser explore = new FileChooser();
		explore.setTitle("Choisissez un fichier");
		
		Button selectFile = new Button("Selectionner un fichier");
		Button naive = new Button("Resolution naive");
		Button naiveUpgraded = new Button("Resolution naive optimisee");
		Button upgrade = new Button("Resolution gloutonne");
		Button print = new Button("Afficher");
		Button quit = new Button("Fin");
		
		selectFile.setOnAction(event->{
			File aggFile = explore.showOpenDialog(stage);
			if(aggFile!=null) {
				AgglomerationGUI.aggPath = aggFile.getAbsolutePath();
			}
			AgglomerationGUI.agg = ParseAgglomeration.parseAgg(AgglomerationGUI.aggPath);
			System.out.println(AgglomerationGUI.aggPath);
		});
		naive.setOnAction(event->{
			//un bouton qui nous redirige vers une saisie
			if(AgglomerationGUI.agg!=null)
				stage.setScene(new Scene(new InputResNaivePane(stage),300,300));
		});
		
		naiveUpgraded.setOnAction(event->{
			if(AgglomerationGUI.agg!=null)
				stage.setScene(new Scene(new InputResNaivePane(stage),300,300));
		});
		
		upgrade.setOnAction(event->{
			if(AgglomerationGUI.agg!=null) {
				System.out.println("resolution gloutonne");
				//AgglomerationGUI.agg = ParseAgglomeration.parseAgg(AgglomerationGUI.aggPath);
				stage.setScene(new Scene(new AfficherPane(stage,0)));
			}
			
		});
			
		print.setOnAction(event->{
			if(AgglomerationGUI.agg!=null) {
				//AgglomerationGUI.agg = ParseAgglomeration.parseAgg(AgglomerationGUI.aggPath);
				stage.setScene(new Scene(new AfficherPane(stage,0)));
			}
		});
		
		quit.setOnAction(event->{
			System.exit(1);
		});
		this.getChildren().addAll(selectFile,naive,naiveUpgraded,upgrade,print,quit);
	}

}
