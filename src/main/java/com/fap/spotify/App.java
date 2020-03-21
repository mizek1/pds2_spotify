
package com.fap.spotify;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

	private BorderPane pane;
	private HBox hTopo, hMedia;
	private VBox vBoxMenu;
	private static Stage stage;
	private Button btInicio, btBusca, btBiblioteca, btNovaPlaylist, btMusicasCurtidas, btConta, btSair;
	private Label lbSpotify;
	private TextField tfBusca;

	public void initComponents() {

		pane = new BorderPane();

		pane.setPrefSize(800, 500);

		hTopo = initHTopo();
		pane.setTop(hTopo);
		
		vBoxMenu = initVBoxMenu();
		pane.setLeft(vBoxMenu);
		
		hMedia = initHMedia();
		pane.setBottom(hMedia);
		
	}

	public HBox initHTopo() {

		HBox hTopo = new HBox(10);

		tfBusca = new TextField();
		tfBusca.setPromptText("Busque artistas, músicas ou podcasts...");
		tfBusca.setVisible(false);
		
		btConta = new Button("Conta");
		
		btSair = new Button("Sair");
		
		hTopo.getChildren().addAll(tfBusca, btConta, btSair);

		return hTopo;
	}

	public VBox initVBoxMenu() {

		VBox vBoxMenu = new VBox(25);

		lbSpotify = new Label("Spotify");
		vBoxMenu.getChildren().add(lbSpotify);

		btInicio = new Button("Início");
		btBusca = new Button("Buscar");
		btBiblioteca = new Button("Sua Biblioteca");
		btNovaPlaylist = new Button("Nova playlist");
		btMusicasCurtidas = new Button("Músicas curtidas");

		vBoxMenu.getChildren().addAll(btInicio, btBusca, btBiblioteca, btNovaPlaylist, btMusicasCurtidas);

		return vBoxMenu;
	}

	public HBox initHMedia() {

		HBox hMedia = new HBox(15);
		Label nomeMusica = new Label("Nome da música");
		Label nomeDisco = new Label("Nome do disco");

		hMedia.getChildren().addAll(nomeMusica, nomeDisco);

		Button btAleatorio = new Button("Aleatorio");
		Button btAnterior = new Button("<<");
		Button btPlay = new Button("i>");
		Button btProxima = new Button(">>");
		Button btRepete = new Button("Repete");

		hMedia.getChildren().addAll(btAleatorio, btAnterior, btPlay, btProxima, btRepete);

		return hMedia;
	}

	public void initLayout() {
		
		hTopo.setAlignment(Pos.TOP_RIGHT);
		vBoxMenu.setAlignment(Pos.BASELINE_LEFT);
		hMedia.setAlignment(Pos.BOTTOM_CENTER);
		
		tfBusca.setAlignment(Pos.BASELINE_LEFT);
		tfBusca.setPrefWidth(300);

		btConta.setAlignment(Pos.TOP_RIGHT);

		btSair.setAlignment(Pos.TOP_RIGHT);
		
		lbSpotify.setAlignment(Pos.TOP_CENTER);
		
		btInicio.setPrefWidth(140);
		btBusca.setPrefWidth(140);
		btBiblioteca.setPrefWidth(140);
		btNovaPlaylist.setPrefWidth(140);
		btMusicasCurtidas.setPrefWidth(140);
		
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