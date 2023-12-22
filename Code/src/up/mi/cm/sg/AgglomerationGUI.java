package up.mi.cm.sg;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import up.mi.cm.sg.gui.MainPane;
import up.mi.cm.sg.gui.SecondMenuPane;

/**
 * Classe qui permet de lancer Javafx
 * 
 */

public class AgglomerationGUI extends Application{
	public static CA agg=null;
	public static Scene menuStart = null;
	public static Scene menu1 = null;
	public static Scene menu2 = null;
	public static Scene menuAuto = null;
	public static String aggPath = null;
	public void start(Stage stage) {
		menuStart = new Scene(new MainPane(stage),300,300);
		menu2 = new Scene(new SecondMenuPane(stage),300,300);
		
		stage.setTitle("Communaute d'agglomeration");
		stage.setScene(menuStart);
		stage.sizeToScene();
		stage.show();
	}
	
	public static void main(String [] args) {
		launch(args);
	}
}
