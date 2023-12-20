package up.mi.cm.sg.gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.File;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.layout.FlowPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import up.mi.cm.sg.AgglomerationGUI;
import up.mi.cm.sg.ExeptionChangesArea;
import up.mi.cm.sg.ParseAgglomeration;

public class MainPane extends FlowPane{

	public MainPane(Stage stage) {
		this.setOrientation(Orientation.VERTICAL);
		this.setAlignment(Pos.CENTER);
		
		FileChooser explore = new FileChooser();
		explore.setTitle("Choisissez un fichier");
		
		Button selectFile = new Button("Selectionner un fichier");
		Label erreur = new Label("");
		Button man = new Button("Resoudre manuellement");
		Button auto = new Button("Resoudre automatiquement");
		Button save = new Button("Sauvegarder");
		Button print = new Button("Afficher");
		Button end = new Button("Fin");
		
		selectFile.setOnAction(event->{
			File aggFile = explore.showOpenDialog(stage);
			if(aggFile!=null) {
				AgglomerationGUI.aggPath = aggFile.getAbsolutePath();
			}
			try {
				AgglomerationGUI.agg = ParseAgglomeration.parseAgg(AgglomerationGUI.aggPath);
				erreur.setText(AgglomerationGUI.aggPath);
			} catch (ExeptionChangesArea e) {
				AgglomerationGUI.agg = null;
				erreur.setText("Ficher incorect");
				e.printStackTrace();
			}
			System.out.println(AgglomerationGUI.aggPath);
		});
		
		man.setOnAction(event->{
			if(AgglomerationGUI.agg!=null) {
				//redirection vers combien de villes menu
				stage.setScene(new Scene(new SecondMenuPane(stage),300,300));
			}
		});
		
		auto.setOnAction(event->{
			if(AgglomerationGUI.agg!=null) {
				System.out.println("resolution gloutonne");
				//AgglomerationGUI.agg = ParseAgglomeration.parseAgg(AgglomerationGUI.aggPath);
				AgglomerationGUI.agg.algorithmeGlouton();
				stage.setScene(new Scene(new AfficherPane(stage,3)));
			}
		});
		
		//pour le save on selectinne aussi un fichier
		save.setOnAction(event->{
			if(AgglomerationGUI.agg!=null) {
				System.out.println("save");
				File aggFile = explore.showOpenDialog(stage);
				String path = ""; 
				if(aggFile!=null) {
					path = aggFile.getAbsolutePath();
					ParseAgglomeration.writeCA(AgglomerationGUI.agg, path);
				}
			}
		});
		
		print.setOnAction(event->{
			if(AgglomerationGUI.agg!=null) {
				//AgglomerationGUI.agg = ParseAgglomeration.parseAgg(AgglomerationGUI.aggPath);
				stage.setScene(new Scene(new AfficherPane(stage,3)));
			}
		});
		AfficherGraphStream graph  = new AfficherGraphStream();
		end.setOnAction(event->{
			if (AgglomerationGUI.agg!=null) {
				try {
					graph.start(stage);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("je vais m'enerver");
					e.printStackTrace();
				}
			}
			//System.exit(1);
		});
		
		
		this.getChildren().addAll(selectFile, erreur,man,auto,save,print,end);
	}
	
}
