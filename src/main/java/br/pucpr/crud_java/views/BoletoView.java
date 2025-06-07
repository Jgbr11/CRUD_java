package br.pucpr.crud_java.views;

import br.pucpr.crud_java.models.Boleto;
import br.pucpr.crud_java.persistencias.ArquivoBoleto;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;

public class BoletoView {
    private Stage stage;
    private Scene cena;

    public BoletoView(Stage stage) {
        this.stage = stage;
    }

    public void mostrar() {
        criarUI();
        this.stage.show();
    }

    private void criarUI() {
        stage.setTitle("Boletos");
        ArrayList<Boleto> boletos = ArquivoBoleto.lerLista();
        String styleBtn = "-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: black; -fx-font-size: 16px;";

        BorderPane borderPane = new BorderPane();

        HBox navBar = new HBox();
        navBar.setStyle("-fx-padding: 10; -fx-alignment: center; -fx-background-color: grey");
        navBar.setSpacing(30);

        Button btnHome = new Button("Home");
        Button btnLocatarios = new Button("Locatários");
        Button btnContratos = new Button("Contratos");
        Button btnLojas = new Button("Lojas");
        Button btnEspacos = new Button("Espaços");

        btnHome.setFont(new Font("Montserrat", 18));
        btnHome.setStyle(styleBtn);
        btnLocatarios.setFont(new Font("Montserrat", 18));
        btnLocatarios.setStyle(styleBtn);
        btnContratos.setFont(new Font("Montserrat", 18));
        btnContratos.setStyle(styleBtn);
        btnLojas.setFont(new Font("Montserrat", 18));
        btnLojas.setStyle(styleBtn);
        btnEspacos.setFont(new Font("Montserrat", 18));
        btnEspacos.setStyle(styleBtn);

        navBar.getChildren().add(btnHome);
        navBar.getChildren().add(btnLocatarios);
        navBar.getChildren().add(btnContratos);
        navBar.getChildren().add(btnLojas);
        navBar.getChildren().add(btnEspacos);

        Separator separadorNav = new Separator();

        HBox pageContent = new HBox();
        VBox camposBol = new VBox();
        camposBol.setStyle("-fx-padding: 10;");
        camposBol.setSpacing(5);

        Label labelNmrDoc = new Label("Número do Documento");
        TextField txtNmrDoc = new TextField();
        txtNmrDoc.setPromptText("Digite o nº");

        Label labelVal = new Label("Valor");
        TextField txtVal = new TextField();
        txtVal.setPromptText("Digite o valor");

        Label labelVenc = new Label("Data de vencimento");
        TextField txtVenc = new TextField("18/06/2026");
        txtVenc.setEditable(false);

        Label labelCedente = new Label("Cedente");
        TextField txtCedente = new TextField("Tijucas Open");
        txtCedente.setEditable(false);

        Label labelBanco = new Label("Banco");
        TextField txtBanco = new TextField("Banco do Brasil");
        txtBanco.setEditable(false);

        Label labelLinhaDig = new Label("Linha digitável");
        TextField txtLinhaDig = new TextField();
        txtLinhaDig.setPromptText("Preencha a linha digitável");

        Label labelContrato = new Label("Contrato");
        TextField txtContrato = new TextField();
        txtContrato.setPromptText("Digite o ID");

        Button btnCad = new Button("Cadastrar");

        camposBol.getChildren().addAll(separadorNav, labelNmrDoc, txtNmrDoc, labelVal, txtVal, labelVenc, txtVenc, labelCedente,
                txtCedente, labelBanco, txtBanco, labelLinhaDig, txtLinhaDig, labelContrato, txtContrato, btnCad);

        TableView<Boleto> boletosTable = new TableView<>();


        borderPane.setTop(navBar);
        borderPane.setLeft(camposBol);

        this.cena = new Scene(borderPane, 800, 500);
        this.stage.setScene(this.cena);

    }

}
