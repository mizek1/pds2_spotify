
package com.fap.spotify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
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
	private VBox vBoxMenu, painelPrincipal;
	private static Stage stage;
	private Button btInicio, btBusca, btBiblioteca, btNovaPlaylist, btMusicasCurtidas, btConta, btSair, 
	btAddMusica, btAtualizar, btPlay, btAleatorio, btProxima, btAnterior, btRepete;
	private Label lbSpotify, lbTitle;
	private TextField tfBusca, tfTituloMusica, tfArtista;
	private List<Musica> musicasCurtidas = new ArrayList<Musica>();
	private ListView<Musica> listaMusicasCurtidas;

	public void initComponents() {

		pane = new BorderPane();

		pane.setPrefSize(800, 500);

		hTopo = initHTopo();
		pane.setTop(hTopo);

		vBoxMenu = initVBoxMenu();
		pane.setLeft(vBoxMenu);

		hMedia = initHMedia();
		pane.setBottom(hMedia);
		
		painelPrincipal = initPrincipal();
		pane.setCenter(painelPrincipal);

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

		btAleatorio = new Button("Aleatorio");
		btAnterior = new Button("<<");
		btPlay = new Button("►");
		btProxima = new Button(">>");
		btRepete = new Button("Repete");

		hMedia.getChildren().addAll(btAleatorio, btAnterior, btPlay, btProxima, btRepete);

		return hMedia;
	}
	
	public VBox initPrincipal() {
		
		painelPrincipal = new VBox(15);
		
		lbTitle = new Label("Músicas curtidas");
		
		tfArtista = new TextField("Nome do artista");
		tfTituloMusica = new TextField("Titulo da música");
		
		btAddMusica = new Button("Adicionar");
		btAtualizar = new Button("Atualizar lista");
		
		listaMusicasCurtidas = new ListView<Musica>();
		
		painelPrincipal.getChildren().addAll(lbTitle, tfTituloMusica, tfArtista, btAddMusica, btAtualizar, listaMusicasCurtidas);
		
		return painelPrincipal;
	}

	public void initLayout() {

		hTopo.setAlignment(Pos.TOP_RIGHT);
		vBoxMenu.setAlignment(Pos.BASELINE_LEFT);
		hMedia.setAlignment(Pos.BOTTOM_CENTER);
		painelPrincipal.setAlignment(Pos.CENTER);

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

		lbTitle.setAlignment(Pos.TOP_CENTER);
		tfTituloMusica.setPrefWidth(140);
		tfArtista.setPrefWidth(140);
		btAddMusica.setPrefWidth(140);
		btAtualizar.setPrefWidth(190);
	}

	public void initListeners() {

		btPlay.setOnAction(new EventHandler<ActionEvent>() {
			int flag = 0;
			@Override
			public void handle(ActionEvent event) {
				if (flag == 0) {
					btPlay.setText("||");
					flag = 1;
				} else {
					btPlay.setText("►");
					flag = 0;
				}
			}
		});
		
		btBusca.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (!tfBusca.isVisible()) {
					tfBusca.setVisible(true);
				} else {
					tfBusca.setVisible(false);
				}
			}
		});

		btSair.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (confirmarSair() == true)
					fechar();
			}
		});

		btInicio.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				funcaoIndisponivel();
			}
		});
		
		btAddMusica.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(!tfTituloMusica.getText().isEmpty() && !tfArtista.getText().isEmpty()) {
					Musica musica = new Musica(tfTituloMusica.getText(), tfArtista.getText());
					adicionarMusica(musica);
				} else {
					camposVazios();
				} 
			}
		});
		
		btAtualizar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				atualizarListaMusicas();
			}
		});
		
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

	public boolean confirmarSair() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Sair");
		alert.setHeaderText("Sair da aplicação");
		alert.setContentText("Você deseja sair?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK)
			return true;
		else
			return false;
	}
	
	public void funcaoIndisponivel() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Indisponível");
		alert.setHeaderText("Função indisponível :(");
		alert.setContentText("Esperamos poder disponibilizá-la em breve!");
		alert.showAndWait();
	}
	
	public void camposVazios() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Campos vazios");
		alert.setHeaderText("Preencha os campos!");
		alert.setContentText("Há 1 ou mais campos vazios.");
		alert.showAndWait();
	}
	
	public void adicionarMusica(Musica musica) {
		musicasCurtidas.add(musica);
	}
	
	public void atualizarListaMusicas() {
		listaMusicasCurtidas.getItems().clear();
		listaMusicasCurtidas.getItems().addAll(musicasCurtidas);
	}

	public void fechar() {
		System.exit(0);
	}

}