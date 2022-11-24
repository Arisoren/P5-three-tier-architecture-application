package santosw.P5;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
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