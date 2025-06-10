package br.pucpr.crud_java.views;

import br.pucpr.crud_java.models.Loja;
import br.pucpr.crud_java.persistencias.ArquivoLoja;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;      //  lista que atualiza a tela sozinha quando ela alterada.
import javafx.scene.Scene;                     // define o conteúdo que vai dentro da janela.
import javafx.scene.control.*;                 // puxa os componentes como botões, textos, tabelas, etc.
import javafx.scene.layout.BorderPane;         // organiza a tela em 5 áreas (centro, topo, direita, etc.).
import javafx.scene.layout.HBox;               // organiza os itens um do lado do outro (em linha).
import javafx.scene.layout.VBox;               // Empilha os itens um em cima do outro (em coluna).
import javafx.scene.text.Font;                 // Fonte
import javafx.stage.Stage;                     // janela principal do programa

import java.util.ArrayList;

import static javafx.collections.FXCollections.observableArrayList;

public class LojaView {
    private Stage stage;
    private Scene cena;

    private ObservableList<Loja> lojasObservable = observableArrayList();

    public LojaView(Stage stage) {
        this.stage = stage;
    }

    public void mostrar() {
        criarUI();
        this.stage.show();
    }

    private void criarUI() {
        stage.setTitle("Lojas");
        ArrayList<Loja> lojas = ArquivoLoja.lerLista();
        lojasObservable.setAll(lojas);
        String styleBtn = "-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: black; -fx-font-size: 16px;";

        BorderPane borderPane = new BorderPane();

        HBox navBar = new HBox();
        navBar.setStyle("-fx-padding: 10; -fx-alignment: center; -fx-background-color: grey");
        navBar.setSpacing(30);

        Button btnHome = new Button("Home");
        Button btnLocatarios = new Button("Locatários");
        Button btnContratos = new Button("Contratos");
        Button btnBoletos = new Button("Boletos");
        Button btnEspacos = new Button("Espaços");

        btnHome.setFont(new Font("Montserrat", 18));
        btnHome.setStyle(styleBtn);
        btnLocatarios.setFont(new Font("Montserrat", 18));
        btnLocatarios.setStyle(styleBtn);
        btnContratos.setFont(new Font("Montserrat", 18));
        btnContratos.setStyle(styleBtn);
        btnBoletos.setFont(new Font("Montserrat", 18));
        btnBoletos.setStyle(styleBtn);
        btnEspacos.setFont(new Font("Montserrat", 18));
        btnEspacos.setStyle(styleBtn);

        navBar.getChildren().addAll(btnHome);
        navBar.getChildren().add(btnLocatarios);
        navBar.getChildren().add(btnContratos);
        navBar.getChildren().add(btnBoletos);
        navBar.getChildren().add(btnEspacos);

        Separator separadorNav = new Separator();

        HBox pageContent = new HBox();
        VBox camposLoja = new VBox();
        camposLoja.setStyle("-fx-padding: 10;");
        camposLoja.setSpacing(5);

        Label labelNome = new Label("Nome da Loja");
        TextField txtNome = new TextField();
        txtNome.setPromptText("Digite o nome da loja");

        Label labelTelefone = new Label("Telefone da Loja");
        TextField txtTelefone = new TextField();
        txtTelefone.setPromptText("Digite o telefone da loja");
        adicionarMascaraTelefone(txtTelefone);

        Label labelTipo = new Label("Tipo da Loja");
        ComboBox<String> cbTipo = new ComboBox<>();
        cbTipo.getItems().addAll("Roupas", "Joias", "Esportes", "Restaurantes", "Livros");
        cbTipo.setPromptText("Selecione o tipo");

        Button btnCad = new Button("Cadastrar");
        btnCad.setOnAction(e -> {
            try {
                String nome = txtNome.getText();
                String telefone = txtTelefone.getText();
                String tipo = cbTipo.getValue();


                if (nome.isEmpty() || telefone.isEmpty() || tipo == null) {
                    throw new IllegalArgumentException("Preencha todos os campos corretamente!");
                }


                Loja novaLoja = new Loja(nome, telefone, tipo);
                ArquivoLoja.adicionarLoja(novaLoja);
                lojasObservable.add(novaLoja);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Cadastro");
                alert.setHeaderText(null);
                alert.setContentText("Loja cadastrada com sucesso!");
                alert.showAndWait();

                txtNome.clear();
                txtTelefone.clear();
                cbTipo.setValue(null);

            } catch (IllegalArgumentException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText(null);
                alert.setContentText(ex.getMessage());
                alert.showAndWait();
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro inesperado");
                alert.setHeaderText(null);
                alert.setContentText("Ocorreu um erro ao cadastrar a loja.");
                alert.showAndWait();
                ex.printStackTrace();
            }
        });


        camposLoja.getChildren().addAll(separadorNav, labelNome, txtNome,
                labelTelefone, txtTelefone, labelTipo, cbTipo, btnCad);

        TableView<Loja> lojasTable = new TableView<>();

        TableColumn<Loja, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLojaNome()));

        TableColumn<Loja, String> colTel = new TableColumn<>("Telefone");
        colTel.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLojaTelefone()));


        TableColumn<Loja, String> colTipo = new TableColumn<>("Tipo");
        colTipo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLojaTipo()));

        lojasTable.getColumns().addAll(colNome, colTel, colTipo);
        lojasTable.setItems(lojasObservable);
        lojasTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        lojasTable.setPrefWidth(500);
        lojasTable.setPrefHeight(400);

        pageContent.getChildren().add(lojasTable);

        borderPane.setTop(navBar);
        borderPane.setLeft(camposLoja);
        borderPane.setRight(pageContent);

        this.cena = new Scene(borderPane, 800, 500);
        this.stage.setScene(this.cena);

    }








        private void adicionarMascaraTelefone(TextField textField) {

            textField.textProperty().addListener((observable, oldValue, newValue) -> {

                String digitos = newValue.replaceAll("\\D", ""); //apenas numeros


                if (digitos.length() > 11) {
                    digitos = digitos.substring(0, 11);
                }

                String textoFormatado;


                if (digitos.length() <= 2) {
                    textoFormatado = "(" + digitos;
                } else if (digitos.length() <= 6) {
                    textoFormatado = "(" + digitos.substring(0, 2) + ") " + digitos.substring(2);
                } else if (digitos.length() <= 10) {
                    textoFormatado = "(" + digitos.substring(0, 2) + ") " + digitos.substring(2, 6) + "-" + digitos.substring(6);
                } else { // Para 11 dígitos (celular)
                    textoFormatado = "(" + digitos.substring(0, 2) + ") " + digitos.substring(2, 7) + "-" + digitos.substring(7);
                }


                if (!newValue.equals(textoFormatado)) {

                    textField.setText(textoFormatado);
                    textField.positionCaret(textoFormatado.length()); // move o cursor
                }
            });
        }

}
