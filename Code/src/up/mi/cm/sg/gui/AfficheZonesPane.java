package up.mi.cm.sg.gui;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import up.mi.cm.sg.AgglomerationGUI;

public class AfficheZonesPane extends FlowPane{
	public AfficheZonesPane(Stage s) {
		TextArea ta = new TextArea();
		Button b = new Button("Menu");
		ta.setText(AgglomerationGUI.agg.allZones());
		
		
		b.setOnAction(event->{
			s.setScene(AgglomerationGUI.menu2);
			
		});
		this.getChildren().addAll(ta,b);
	}
}
