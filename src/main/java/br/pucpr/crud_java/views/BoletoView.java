package br.pucpr.crud_java.views;

import br.pucpr.crud_java.models.Boleto;
import br.pucpr.crud_java.persistencias.ArquivoBoleto;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;

import static javafx.collections.FXCollections.observableArrayList;

public class BoletoView {
    private Stage stage;
    private Scene cena;

    private ObservableList<Boleto> boletosObservable = observableArrayList();

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
        boletosObservable.setAll(boletos);
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

        Button btnCad = new Button("Cadastrar");
        btnCad.setOnAction(e -> {
            try {
                    int numDoc = Integer.parseInt(txtNmrDoc.getText());
                    double valor = Double.parseDouble(txtVal.getText());
                    String vencimento = txtVenc.getText();
                    String cedente = txtCedente.getText();
                    String banco = txtBanco.getText();
                    String linhaDig = txtLinhaDig.getText();

                    Boleto novoBoleto = new Boleto(numDoc, valor, vencimento, cedente, banco, linhaDig);
                    ArquivoBoleto.adicionarBoleto(novoBoleto);
                    boletosObservable.add(novoBoleto);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Cadastro");
                    alert.setHeaderText(null);
                    alert.setContentText("Cadastro efetuado com sucesso!");
                    alert.showAndWait();

                    txtNmrDoc.clear();
                    txtVal.clear();
                    txtLinhaDig.clear();
                } catch (NumberFormatException ex){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erro");
                    alert.setHeaderText(null);
                    alert.setContentText("Insira dados válidos!");
                    alert.showAndWait();
            }
        }
        );

        camposBol.getChildren().addAll(separadorNav, labelNmrDoc, txtNmrDoc, labelVal, txtVal, labelVenc, txtVenc, labelCedente,
                txtCedente, labelBanco, txtBanco, labelLinhaDig, txtLinhaDig, btnCad);

        TableView<Boleto> boletosTable = new TableView<>();

        TableColumn<Boleto, String> colNmrDoc = new TableColumn<>("Nº Doc");
        colNmrDoc.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getNumeroDocumento())));

        TableColumn<Boleto, String> colValor = new TableColumn<>("Valor");
        colValor.setCellValueFactory(cellData -> new SimpleStringProperty((String.format("%.2f", cellData.getValue().getValor()))));

        TableColumn<Boleto, String> colData = new TableColumn<>("Vencimento");
        colData.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVencimento()));

        TableColumn<Boleto, String> colCed = new TableColumn<>("Cedente");
        colCed.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCedente()));

        TableColumn<Boleto, String> colBanco = new TableColumn<>("Banco");
        colBanco.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBanco()));

        TableColumn<Boleto, String> colDig = new TableColumn<>("Linha digitável");
        colDig.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLinhaDigitavel()));

        boletosTable.getColumns().addAll(colNmrDoc, colValor, colData, colCed, colBanco, colDig);

        boletosTable.setItems(boletosObservable);

        boletosTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        boletosTable.setPrefWidth(500);
        boletosTable.setPrefHeight(400);

        pageContent.getChildren().add(boletosTable);

        borderPane.setTop(navBar);
        borderPane.setLeft(camposBol);
        borderPane.setRight(pageContent);

        this.cena = new Scene(borderPane, 800, 500);
        this.stage.setScene(this.cena);

    }

}
