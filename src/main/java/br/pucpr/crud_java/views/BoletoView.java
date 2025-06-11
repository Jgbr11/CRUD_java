package br.pucpr.crud_java.views;

import br.pucpr.crud_java.TelaInicial;
import br.pucpr.crud_java.alerts.Alerts;
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

import java.time.LocalDate;
import java.util.ArrayList;

import static javafx.collections.FXCollections.observableArrayList;

public class BoletoView {
    private Stage stage;
    private Scene cena;

    public ObservableList<Boleto> boletosObservable = observableArrayList();

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


        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-padding: 10;");

        HBox navBar = criarMenuNavegacao();
        borderPane.setTop(navBar);

        VBox painelFormulario = new VBox(10);
        painelFormulario.setStyle("-fx-padding: 10;");
        painelFormulario.setPrefWidth(250);

        Label labelNmrDoc = new Label("Número do Documento");
        TextField txtNmrDoc = new TextField();
        txtNmrDoc.setPromptText("Digite o nº");

        Label labelVal = new Label("Valor");
        TextField txtVal = new TextField();
        txtVal.setPromptText("Digite o valor");

        Label labelDataVenc = new Label("Data de vencimento");
        DatePicker datePickerVenc = new DatePicker();
        datePickerVenc.setPromptText("Escolha a data");

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
        btnCad.setMaxWidth(Double.MAX_VALUE);
        btnCad.setOnAction(e -> {
                    try {
                        long numDoc = Integer.parseInt(txtNmrDoc.getText());
                        double valor = Double.parseDouble(txtVal.getText());
                        LocalDate vencimento = datePickerVenc.getValue();
                        String cedente = txtCedente.getText();
                        String banco = txtBanco.getText();
                        String linhaDig = txtLinhaDig.getText();

                        if (numDoc >= 0 && valor >= 0 && vencimento != null && linhaDig != "") {

                            Boleto novoBoleto =
                                    new Boleto(numDoc, valor, vencimento,
                                            cedente, banco, linhaDig);
                            ArquivoBoleto.adicionarBoleto(novoBoleto);
                            boletosObservable.add(novoBoleto);

                            Alerts.alertInfo("Cadastrado",
                                    "Boleto cadastrado com sucesso");

                            txtNmrDoc.clear();
                            txtVal.clear();
                            datePickerVenc.setValue(null);
                            txtLinhaDig.clear();
                        } else {
                            Alerts.alertError("Erro", "Preencha os campos " +
                                    "corretamente");
                        }
                    } catch (NumberFormatException ex) {
                        Alerts.alertError("Erro", "Insira dados válidos!");
                    }
                }
        );

        painelFormulario.getChildren().addAll(labelNmrDoc, txtNmrDoc, labelVal, txtVal,
                labelDataVenc, datePickerVenc, labelCedente,
                txtCedente, labelBanco, txtBanco, labelLinhaDig, txtLinhaDig, btnCad);

        borderPane.setLeft(painelFormulario);

        VBox painelTabela = new VBox(10);
        painelTabela.setStyle("-fx-padding: 10;");

        TableView<Boleto> boletosTable = criarTabelaBoletos();

        Button btnRemover = new Button("Remover Selecionado");
        btnRemover.setOnAction(e -> {
            try {
                Boleto boletoSelecionado =
                        boletosTable.getSelectionModel().getSelectedItem();
                if (boletoSelecionado != null) {
                    long numeroDocumento =
                            boletoSelecionado.getNumeroDocumento();
                    ArquivoBoleto.removerBoleto(numeroDocumento);
                    boletosObservable.remove(boletoSelecionado);
                    Alerts.alertInfo("Sucesso", "Boleto apagado com sucesso!");
                } else {
                    Alerts.alertError("Erro", "Selecione um boleto para " +
                            "apagar");
                }
            } catch (NullPointerException ex) {
                Alerts.alertError("Erro",
                        "Nenhum boleto selecionado. Erro: " + ex.getMessage());
            }
        });

        Button btnEditar = new Button("Editar Selecionado");
        btnEditar.setOnAction(e -> {
            try {
                Boleto boletoSelecionado =
                        boletosTable.getSelectionModel().getSelectedItem();
                if (boletoSelecionado != null){
                    new ModalBoletoEdit(stage, boletoSelecionado).mostrar();
                    boletosObservable.setAll(ArquivoBoleto.lerLista());
                } else {
                    Alerts.alertError("Erro", "Selecione um boleto para " +
                            "editar");
                }
            } catch (NullPointerException ex){
                Alerts.alertError("Erro",
                        "Nenhum boleto selecionado. Erro: " + ex.getMessage());
            }
        });

        painelTabela.getChildren().addAll(boletosTable, btnRemover, btnEditar);
        borderPane.setCenter(painelTabela);

        this.cena = new Scene(borderPane, 900, 600);
        this.stage.setScene(this.cena);
    }

    private TableView<Boleto> criarTabelaBoletos() {
        TableView<Boleto> boletosTable = new TableView<>();

        TableColumn<Boleto, String> colNmrDoc = new TableColumn<>("Nº Doc");
        colNmrDoc.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getNumeroDocumento())));

        TableColumn<Boleto, String> colValor = new TableColumn<>("Valor");
        colValor.setCellValueFactory(cellData -> new SimpleStringProperty((String.format("%.2f", cellData.getValue().getValor()))));

        TableColumn<Boleto, String> colData = new TableColumn<>("Vencimento");
        colData.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVencimento().toString()));

        TableColumn<Boleto, String> colCed = new TableColumn<>("Cedente");
        colCed.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCedente()));

        TableColumn<Boleto, String> colBanco = new TableColumn<>("Banco");
        colBanco.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBanco()));

        TableColumn<Boleto, String> colDig = new TableColumn<>("Linha digitável");
        colDig.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLinhaDigitavel()));

        boletosTable.getColumns().addAll(colNmrDoc, colValor, colData, colCed, colBanco, colDig);

        boletosTable.setItems(boletosObservable);

        boletosTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        boletosTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        return boletosTable;
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
        btnLocatarios.setOnAction(e -> new LocatarioView(stage).mostrar());

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
        btnEspacos.setOnAction(e -> new EspacoView(stage).mostrar());

        navBar.getChildren().addAll(btnHome, btnLocatarios, btnContratos, btnBoletos, btnLojas, btnEspacos);
        return navBar;
    }

}



















