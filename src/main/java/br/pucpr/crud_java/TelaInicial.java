package br.pucpr.crud_java; // CORRIGIDO: Deve estar no pacote 'views'

import br.pucpr.crud_java.views.BoletoView;
import br.pucpr.crud_java.views.ContratoView;
import br.pucpr.crud_java.views.LocatarioView;
import br.pucpr.crud_java.views.LojaView;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TelaInicial {
    private Stage stage;

    public TelaInicial(Stage stage) {
        this.stage = stage;
    }

    public void mostrar() {
        criarUI();
        this.stage.show();
    }

    private void criarUI() {
        stage.setTitle("Tela Inicial - Sistema de Gestão");

        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-padding: 10;");

        HBox navBar = criarMenuNavegacao();
        borderPane.setTop(navBar);

        // --- Conteúdo Central da Tela Inicial (permanece o mesmo) ---
        VBox centerContent = new VBox(20);
        centerContent.setAlignment(Pos.CENTER);

        Label welcomeLabel = new Label("Bem-vindo ao Sistema de Gestão");
        welcomeLabel.setFont(new Font("Montserrat", 32));

        Label infoLabel = new Label("Utilize o menu acima para navegar entre as seções.");
        infoLabel.setFont(new Font("Arial", 16));

        centerContent.getChildren().addAll(welcomeLabel, infoLabel);
        borderPane.setCenter(centerContent);

        // --- Configurando e mostrando a cena ---
        Scene cena = new Scene(borderPane, 900, 600);
        this.stage.setScene(cena);
    }

    /**
     * Helper para criar a barra de navegação superior.
     * Este método agora é idêntico ao da ContratoView.
     */
    private HBox criarMenuNavegacao() {
        HBox navBar = new HBox(15);
        // ESTILO ATUALIZADO: Fundo 'lightgrey' para combinar com a tela de Contratos.
        navBar.setStyle("-fx-padding: 10; -fx-alignment: center; -fx-background-color: lightgrey;");
        // ESTILO ATUALIZADO: Fonte em negrito para os botões.
        String styleBtn = "-fx-background-color: transparent; -fx-font-weight: bold;";

        Button btnHome = new Button("Home");
        btnHome.setStyle(styleBtn);
        // Ação para recarregar a própria tela
        btnHome.setOnAction(e -> this.mostrar());

        Button btnLocatarios = new Button("Locatários");
        btnLocatarios.setStyle(styleBtn);
        btnLocatarios.setOnAction(e -> new LocatarioView(stage).mostrar());

        Button btnContratos = new Button("Contratos");
        btnContratos.setStyle(styleBtn);
        btnContratos.setOnAction(e -> new ContratoView(stage).mostrar());

        Button btnBoletos = new Button("Boletos");
        btnBoletos.setStyle(styleBtn);
        btnBoletos.setOnAction(e -> new BoletoView(stage).mostrar());

        // Adicione aqui os outros botões quando tiver as telas prontas
        Button btnLojas = new Button("Lojas");
        btnLojas.setStyle(styleBtn);
        btnLojas.setOnAction(e -> new LojaView(stage).mostrar());

        Button btnEspacos = new Button("Espaços");
        btnEspacos.setStyle(styleBtn);

        navBar.getChildren().addAll(btnHome, btnLocatarios, btnContratos, btnBoletos, btnLojas, btnEspacos);
        return navBar;
    }
}