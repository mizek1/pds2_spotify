
package com.fap.spotify;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

	private BorderPane pane;
	private static Stage stage;
	private Button btInicio, btBiblioteca, btBusca, btNovaPlaylist, btMusicasCurtidas, btUsuario;
	private TextField tfBusca;
	private Label lbSpotify; 
	
	public void initComponents() {
		pane = new BorderPane();
		pane.setPrefSize(800, 600);		
	}
	
	public void initLayout() {
		
	}
	
	public void initListeners() {
		
	}
	
	public void initStage(Stage stage) {
		Scene scene = new Scene(pane);
    	stage.setScene(scene);
    	stage.setResizable(true);
    	stage.setTitle("Spotify");
    	stage.show();
	}
	
    @Override
    public void start(Stage stage) {
    	initComponents();
    	initListeners();
    	initStage(stage);
    	initLayout();
    	stage = App.stage;
    }

    public static void main(String[] args) {
        launch();
    }

}