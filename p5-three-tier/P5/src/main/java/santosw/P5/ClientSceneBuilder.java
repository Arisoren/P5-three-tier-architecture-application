package santosw.P5;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ClientSceneBuilder 
{
	private Scene clientScene;
	private VBox windowBox;
	private ChoiceBox<String> instruments;
	private ChoiceBox<String> instrumentBrands;
	private TextField price;
	private ChoiceBox<String> storage;
	private Button submitB;
	
	public ClientSceneBuilder()
	{	
		//builds instrument selection area
		instruments = new ChoiceBox<String>();
	    Label instrumentLabel = new Label("Instrument Type: ");
	    instruments.getItems().addAll("all","guitar","bass","keyboard");
	    instruments.getSelectionModel().selectFirst();
	    
	    instruments.setOnAction(instrumentSelected());
	    
	  //creates, formats, and populates HBox for instrument selection area
	    HBox instrumentBox = new HBox(10);
	    instrumentBox.setAlignment(Pos.CENTER);
		instrumentBox.getChildren().addAll(instrumentLabel, instruments);
		
		//builds instrument brand selection area in default state, will update with instrument choice.
		instrumentBrands = new ChoiceBox<String>();
	    Label brandLabel = new Label("Instrument Brand: ");
	    instrumentBrands.getItems().addAll("all", "yamaha", "gibson", "fender", "roland", "alesis");
	    instrumentBrands.getSelectionModel().selectFirst();
	    
	  //creates, formats, and populates HBox for brand selection area
	    HBox brandBox = new HBox(10);
	    brandBox.setAlignment(Pos.CENTER);
		brandBox.getChildren().addAll(brandLabel, instrumentBrands);
		
		//builds cost specification area
		price = new TextField();
		Label costLabel = new Label("Maximum cost: ");
     	
		//creates, formats, and populates HBox for price specification area
		HBox priceBox = new HBox(10);
		priceBox.setAlignment(Pos.CENTER);
		priceBox.getChildren().addAll(costLabel,price);
		
		//builds warehouse selection area
		storage = new ChoiceBox<String>();
	    Label storeLabel = new Label("Warehouse Location: ");
	    storage.getItems().addAll("all", "PNS", "CLT", "DFW");
	    storage.getSelectionModel().selectFirst();
	    
	  //creates, formats, and populates HBox for warehouse selection area
	    HBox storeBox = new HBox(10);
	    storeBox.setAlignment(Pos.CENTER);
	    storeBox.getChildren().addAll(storeLabel, storage);
		
	    //builds submit button
	    submitB = new Button("Submit Request");
		//submitB.setOnAction(new TextButtonListener());
		
	    //creates, formats, and populates HBox for submit button
	    HBox buttonBox = new HBox(10);
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.getChildren().addAll(submitB);
		
		//create and add all nodes to VBox
		windowBox = new VBox(40);
		windowBox.setPadding(new Insets(20, 20, 20, 20));
		windowBox.getChildren().addAll(instrumentBox, brandBox, priceBox, storeBox, buttonBox);
		
		//create scene
		clientScene = new Scene(windowBox);
	}

	public Scene getScene()
	{ return clientScene; }
	
	//updates instrumentBrands choiceBox according to the currently selected instrument.
	public void updateBrands()
	   {
	     instrumentBrands.getItems().clear();
	     if(instruments.getValue() == "guitar")
	     {
	    	 instrumentBrands.getItems().addAll("all","yamaha","gibson");
	     }
	     else if(instruments.getValue() == "keyboard")
	     {
	    	 instrumentBrands.getItems().addAll("all","roland","alesis");
	     }
	     else if(instruments.getValue() == "bass")
	     {
	    	 instrumentBrands.getItems().addAll("all","fender");
	     }
	     
	     instrumentBrands.getSelectionModel().selectFirst();
	   }
	
	//when an instrument is selected, update brand choicebox to only display brands relevant to instrument type
	private EventHandler<ActionEvent> instrumentSelected()
	{
		EventHandler<ActionEvent> instrumentSelected = new EventHandler<ActionEvent>()
				{
					@Override
					public void handle(ActionEvent event) 
					{ updateBrands(); }
				};
				
				return instrumentSelected;
	}
	
}

