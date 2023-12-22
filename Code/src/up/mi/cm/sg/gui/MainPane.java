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
/**
 * Classe qui represente le panneau qui contient le menu de depart
 * @author 
 *
 */
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
		
		
		//on selectionne un fichier dpeuis l'explorateur de fichiers
		selectFile.setOnAction(event->{
			File aggFile = explore.showOpenDialog(stage);
			if(aggFile!=null) {
				AgglomerationGUI.aggPath = aggFile.getAbsolutePath();
				try {
					AgglomerationGUI.agg = ParseAgglomeration.parseAgg(AgglomerationGUI.aggPath);
					erreur.setText(aggFile.getName());
				} catch (ExeptionChangesArea e) {
					AgglomerationGUI.agg = null;
					erreur.setText("Ficher incorect");
					e.printStackTrace();
				}
				System.out.println(AgglomerationGUI.aggPath);
			}
		});
		//on lance la resolution manuelle
		man.setOnAction(event->{
			if(AgglomerationGUI.agg!=null) {
				//redirection vers le second menu
				stage.setScene(new Scene(new SecondMenuPane(stage),300,300));
			}
		});
		
		//on lance la resolution automatique
		auto.setOnAction(event->{
			if(AgglomerationGUI.agg!=null) {
				System.out.println("resolution gloutonne");
				//AgglomerationGUI.agg = ParseAgglomeration.parseAgg(AgglomerationGUI.aggPath);
				AgglomerationGUI.agg.algorithmeGlouton();
				stage.setScene(new Scene(new AfficherPane(stage,3)));
			}
		});
		
		//Pour la sauvegarde on selectionne un fichier depuis l'explorateur de fichiers
		save.setOnAction(event->{
			if(AgglomerationGUI.agg!=null) {
				System.out.println("save");
				File aggFile = explore.showOpenDialog(stage);
				String path = null; 
				if(aggFile!=null) {
					path = aggFile.getAbsolutePath();
					if(path != null) {
						ParseAgglomeration.writeCA(AgglomerationGUI.agg, path);
					}
				}
			}
		});
		//on affiche l'agglomeration
		print.setOnAction(event->{
			if(AgglomerationGUI.agg!=null) {
				stage.setScene(new Scene(new AfficherPane(stage,3)));
			}
		});
		//on affiche le graphe avec graph stream
		AfficherGraphStream graph  = new AfficherGraphStream();
		
		end.setOnAction(event->{
			if (AgglomerationGUI.agg!=null) {
				try {
					graph.start(stage);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		this.getChildren().addAll(selectFile, erreur,man,auto,save,print,end);
	}
	
}
