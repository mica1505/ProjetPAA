package up.mi.cm.sg.gui;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import up.mi.cm.sg.AgglomerationGUI;
/**
 * Classe qui represente le panneau qui contient le second menu, dans le cas d'une resolution manuelle
 * @author 
 *
 */
public class SecondMenuPane extends FlowPane{
	public SecondMenuPane(Stage stage) {
		this.setOrientation(Orientation.VERTICAL);
		this.setAlignment(Pos.CENTER);
		
		Button add = new Button("Ajouter une zone");
		Button remove = new Button("Supprimer une zone");
		Button print = new Button("Afficher");
		Button end = new Button("Fin");
		AfficherGraphStream a  = new AfficherGraphStream();
		//boutton pour ajouter une zone de recharge
		add.setOnAction(event->{
			stage.setScene(new Scene(new AddZone(stage),300,300));
		});
		//boutton pour supprimer une zone de recharge
		remove.setOnAction(event->{
			stage.setScene(new Scene(new RemoveZone(stage),300,300));
		});
		//boutton pour revenir au MainPane (menu principal)
		end.setOnAction(event->{
			stage.setScene(AgglomerationGUI.menuStart);
			//System.exit(1);
		});
		//boutton pour afficher l'aglomeration
		print.setOnAction(event->{
			stage.setScene(new Scene(new AfficherPane(stage,2)));
		});
		
		this.getChildren().addAll(add,remove,print,end);
	}
}
