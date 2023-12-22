package up.mi.cm.sg.gui;

import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import up.mi.cm.sg.AgglomerationGUI;

public class AddCityPane extends FlowPane{
	public AddCityPane(Stage stage) {
		this.setOrientation(Orientation.VERTICAL);
		Button add = new Button("Ajouter");
		//afficher le message d'erreur
		Label msg = new Label("");
		
		TextField city1 = new TextField();
		TextField city2 = new TextField();
		
		FlowPane fCity1 = new FlowPane();
		FlowPane fCity2 = new FlowPane();
		
		fCity1.getChildren().addAll(new Label("Nom de la premiere ville "),city1);
		fCity2.getChildren().addAll(new Label("Nom de la seconde ville "),city2);
		
		add.setOnAction(event->{
			//revoir ici la condition
			boolean nonNull = AgglomerationGUI.agg.getCity(city1.getText())!= null && AgglomerationGUI.agg.getCity(city2.getText())!=null;
			boolean differentCity = !(city1.getText().equals(city2.getText()));
			if(nonNull && differentCity){
				AgglomerationGUI.agg.getCity(city1.getText()).addNeighbour(AgglomerationGUI.agg.getCity(city2.getText()));
			}
			else {
				if(nonNull)
					System.out.println("Ville introuvable. Veuillez saisir un nom de ville valide.");
				if(!differentCity)
					System.out.println("Une ville ne peut pas etre voisine d'elle meme.");
			}
			//on revient sur le menu de depart
			stage.setScene(AgglomerationGUI.menu1);
		});
		
		this.getChildren().addAll(fCity1,fCity2,msg,add);
	}
}
