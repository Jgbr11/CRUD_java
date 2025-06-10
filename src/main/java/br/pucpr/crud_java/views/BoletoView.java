package br.pucpr.crud_java.views;

import br.pucpr.crud_java.TelaInicial;
import br.pucpr.crud_java.models.Boleto;
import br.pucpr.crud_java.models.Locatario;
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

    public static ObservableList<Boleto> boletosObservable = observableArrayList();

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

        HBox navBar = criarMenuNavegacao();

        Separator separadorNav = new Separator();

        HBox pageContent = new HBox();


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

        pageContent.getChildren().add(boletosTable);

        borderPane.setTop(navBar);
        borderPane.setBottom(pageContent);

        this.cena = new Scene(borderPane, 800, 500);
        this.stage.setScene(this.cena);
    }

    private TableView<Locatario> criarTabelaLocatarios() {
        TableView<Locatario> table = new TableView<>(boletosObservable);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Locatario, String> colCNPJ = new TableColumn<>("CNPJ");
        colCNPJ.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getLocatario_cnpj()));

        TableColumn<Locatario, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getLocatario_nome()));

        TableColumn<Locatario, String> colEmail = new TableColumn<>("Email");
        colEmail.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getLocatario_email()));

        TableColumn<Locatario, String> colTelefone = new TableColumn<>("Telefone");
        colTelefone.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getLocatario_telefone()));

        table.getColumns().addAll(colCNPJ, colNome, colEmail, colTelefone);
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        return table;
    }

    private HBox criarMenuNavegacao() {
        HBox navBar = new HBox(15);
        navBar.setStyle("-fx-padding: 10; -fx-alignment: center; -fx-background-color: lightgrey;");
        String styleBtn = "-fx-background-color: transparent; -fx-font-weight: bold;";
        Button btnHome = new Button("Home");
        btnHome.setStyle(styleBtn);
        btnHome.setOnAction(e -> new TelaInicial(stage).mostrar());

        Button btnLocatarios = new Button("Locatários");
        btnLocatarios.setStyle(styleBtn);
        btnLocatarios.setOnAction(e -> this.mostrar());

        Button btnBoletos = new Button("Boletos");
        btnBoletos.setStyle(styleBtn);
        btnBoletos.setOnAction(e -> new BoletoView(stage).mostrar());

        Button btnContratos = new Button("Contratos");
        btnContratos.setStyle(styleBtn);
        btnContratos.setOnAction(e -> new ContratoView(stage).mostrar());

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

}
