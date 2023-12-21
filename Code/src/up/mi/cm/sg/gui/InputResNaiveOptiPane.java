package up.mi.cm.sg.gui;

import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import up.mi.cm.sg.AgglomerationGUI;
import up.mi.cm.sg.ExeptionChangesArea;
import up.mi.cm.sg.ParseAgglomeration;

public class InputResNaiveOptiPane extends FlowPane{
	Button solve;
	public InputResNaiveOptiPane(Stage stage) {
		this.setOrientation(Orientation.VERTICAL);
		Button solve = new Button("Resoudre");
		TextField tf = new TextField();
		
		this.getChildren().addAll(new Label("Entrez le nombre d'iterations "),tf,solve);
		
		solve.setOnAction(event->{
			if(AgglomerationGUI.aggPath!=null && tf.getText()!=null) {
				try {
					AgglomerationGUI.agg = ParseAgglomeration.parseAgg(AgglomerationGUI.aggPath);
				} catch (ExeptionChangesArea e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				AgglomerationGUI.agg.naiveSolutions2(Integer.valueOf(tf.getText()));
			}
			stage.setScene(new Scene(new AfficherPane(stage,0)));
		});
	}
}
