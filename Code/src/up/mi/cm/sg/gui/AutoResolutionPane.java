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
import up.mi.cm.sg.ExeptionChangesArea;
import up.mi.cm.sg.ParseAgglomeration;

public class AutoResolutionPane extends FlowPane{
	public AutoResolutionPane(Stage stage) {
		this.setOrientation(Orientation.VERTICAL);
		this.setAlignment(Pos.CENTER);
		
		FileChooser explore = new FileChooser();
		explore.setTitle("Choisissez un fichier");
		
		Button selectFile = new Button("Selectionner un fichier");
		Label erreur = new Label("");
		Button naive = new Button("Resolution naive");
		Button naiveUpgraded = new Button("Resolution naive optimisee");
		Button upgrade = new Button("Resolution gloutonne");
		Button print = new Button("Afficher");
		Button save = new Button("Save");
		Button quit = new Button("Fin");
		
		AfficherGraphStream a  = new AfficherGraphStream();
		
		selectFile.setOnAction(event->{
			File aggFile = explore.showOpenDialog(stage);
			if(aggFile!=null) {
				AgglomerationGUI.aggPath = aggFile.getAbsolutePath();
				erreur.setText(AgglomerationGUI.aggPath);
			}
			try {
				AgglomerationGUI.agg = ParseAgglomeration.parseAgg(AgglomerationGUI.aggPath);
			} catch (ExeptionChangesArea e) {
				AgglomerationGUI.agg = null;
				erreur.setText("Ficher incorect");
				e.printStackTrace();
			}
			System.out.println(AgglomerationGUI.aggPath);
		});
		naive.setOnAction(event->{
			//un bouton qui nous redirige vers une saisie
			if(AgglomerationGUI.agg!=null)
				stage.setScene(new Scene(new InputResNaivePane(stage),300,300));
		});
		
		naiveUpgraded.setOnAction(event->{
			if(AgglomerationGUI.agg!=null)
				stage.setScene(new Scene(new InputResNaiveOptiPane(stage),300,300));
		});
		
		upgrade.setOnAction(event->{
			if(AgglomerationGUI.agg!=null) {
				System.out.println("resolution gloutonne");
				//AgglomerationGUI.agg = ParseAgglomeration.parseAgg(AgglomerationGUI.aggPath);
				AgglomerationGUI.agg.algorithmeGlouton();
				stage.setScene(new Scene(new AfficherPane(stage,0)));
			}
			
		});
			
		print.setOnAction(event->{
			if(AgglomerationGUI.agg!=null) {
				//AgglomerationGUI.agg = ParseAgglomeration.parseAgg(AgglomerationGUI.aggPath);
				stage.setScene(new Scene(new AfficherPane(stage,0)));
			}
		});
		save.setOnAction(event->{
			System.out.println("save");
			File aggFile = explore.showOpenDialog(stage);
			String path = ""; 
			if(aggFile!=null) {
				path = aggFile.getAbsolutePath();
				ParseAgglomeration.writeCA(AgglomerationGUI.agg, path);
			}
		});
		
		quit.setOnAction(event->{
			try {
				a.start(stage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("je vais m'enerver");
				e.printStackTrace();
			}
			//System.exit(1);
		});
		this.getChildren().addAll(selectFile, erreur,naive,naiveUpgraded,upgrade,print,save,quit);
	}

}
