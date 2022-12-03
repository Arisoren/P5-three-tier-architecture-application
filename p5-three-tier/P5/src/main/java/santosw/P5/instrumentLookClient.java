package santosw.P5;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/***************************************************************
 * Student Name: Wilver Santos
 * File Name: instrumentLookClient.java
 * Assignment number: Project 5
 *
 * the instrumentLookClient class is a GUI client that will connect to a local server hosting a database of musical instruments,
 * user will be able to interact with said GUI to search for Instruments with different filtering methods.  
 ***************************************************************/

public class instrumentLookClient extends Application 
{

    @Override
    public void start(Stage stage) 
    {
        ClientSceneBuilder test = new ClientSceneBuilder();
        Scene someScene = test.getScene();
        
        stage.setTitle("Instrument Lookup");
        
        stage.setScene(someScene);
        
        stage.show();
    }

    public static void main(String[] args) 
    {
        launch();
    }

}