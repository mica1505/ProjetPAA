package up.mi.cm.sg.gui;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import up.mi.cm.sg.AgglomerationGUI;

public class AfficherPane extends FlowPane{
	public AfficherPane(Stage s,int numMenu) {
		TextArea ta = new TextArea();
		Button b = new Button("Menu");
		ta.setText(AgglomerationGUI.agg.toString());
		this.getChildren().addAll(ta,b);
		
		b.setOnAction(event->{
			if(numMenu == 0) {
				s.setScene(AgglomerationGUI.menuAuto);
			}
			if(numMenu == 1) {
				s.setScene(AgglomerationGUI.menu1);
			}
			if(numMenu == 2) {
				s.setScene(AgglomerationGUI.menu2);
			}
		});
	}
}