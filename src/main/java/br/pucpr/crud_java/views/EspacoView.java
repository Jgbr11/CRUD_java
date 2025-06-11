package br.pucpr.crud_java.views;

import br.pucpr.crud_java.models.Espaco;
import br.pucpr.crud_java.persistencias.ArquivoEspaco;
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

public class EspacoView {
    private Stage stage;
    private Scene scene;

    private ObservableList<Espaco> espacosObservable = observableArrayList();

    public EspacoView(Stage stage, Scene scene) {
        this.stage = stage;
    }
    public void mostrar(){
        criarUI();
        this.stage.show();
    }
    private void criarUI(){
        stage.setTitle("Espaços");
        ArrayList<Espaco> espacos = ArquivoEspaco.lerLista();
        espacosObservable.setAll(espacos);
        String styleBtn = "-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: black; -fx-font-size: 16px;";

        BorderPane borderPane = new BorderPane();

        HBox navBar = new HBox();
        navBar.setStyle("-fx-padding: 10; -fx-alignment: center; -fx-background-color: grey");
        navBar.setSpacing(30);

        Button btnHome = new Button("Home");
        Button btnLocatarios = new Button("Locatários");
        Button btnContratos = new Button("Contratos");
        Button btnBoletos = new Button("Boletos");
        Button btnLojas = new Button("Lojas");
        Button btnEspacos = new Button("Espaços");

        btnHome.setFont(new Font("Montserrat", 18));
        btnHome.setStyle(styleBtn);
        btnLocatarios.setFont(new Font("Montserrat", 18));
        btnLocatarios.setStyle(styleBtn);
        btnContratos.setFont(new Font("Montserrat", 18));
        btnContratos.setStyle(styleBtn);
        btnBoletos.setFont(new Font("Montserrat", 18));
        btnBoletos.setStyle(styleBtn);
        btnLojas.setFont(new Font("Montserrat", 18));
        btnLojas.setStyle(styleBtn);
        btnEspacos.setFont(new Font("Montserrat", 18));
        btnEspacos.setStyle(styleBtn);

        navBar.getChildren().add(btnHome);
        navBar.getChildren().add(btnLocatarios);
        navBar.getChildren().add(btnContratos);
        navBar.getChildren().add(btnBoletos);
        navBar.getChildren().add(btnLojas);
        navBar.getChildren().add(btnEspacos);

        Separator separadorNav = new Separator();

        HBox pageContent = new HBox();
        VBox camposEspaco = new VBox();
        camposEspaco.setStyle("-fx-padding: 10;");
        camposEspaco.setSpacing(5);

        Label labelArea = new Label("Área do espaço");
        TextField txtArea = new TextField();
        txtArea.setPromptText("Digite a área do espaço");

        Label labelPiso = new Label("Piso do espaço");
        TextField txtPiso = new TextField();
        txtPiso.setPromptText("Digite o andar do espaço");

        Button btnCad = new Button("Cadastrar");
        btnCad.setOnAction(e -> {
            try{
                double area = Double.parseDouble(txtArea.getText());
                int piso = Integer.parseInt(txtPiso.getText());

                Espaco novoEspaco = new Espaco(piso, area);
                ArquivoEspaco.adicionarEspaco(novoEspaco);
                espacosObservable.add(novoEspaco);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Cadastro");
                alert.setHeaderText(null);
                alert.setContentText("Cadastrado com sucesso!");
                alert.showAndWait();

                txtArea.clear();
                txtPiso.clear();
            } catch (NumberFormatException ex){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText(null);
                alert.setContentText("Insira dados válidos!");
                alert.showAndWait();
            }
        }
        );
        camposEspaco.getChildren().addAll(separadorNav, labelArea, txtArea, labelPiso, txtPiso);
        TableView<Espaco> espacoTable = new TableView<>();

        TableColumn<Espaco, String> colArea = new TableColumn<>("Area");
        colArea.setCellValueFactory(cellData -> new SimpleStringProperty(String.format("%.2f", cellData.getValue().getArea())));

        TableColumn<Espaco, String> colPiso = new TableColumn<>("Piso");
        colPiso.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPiso())));
        espacoTable.getColumns().addAll(colArea, colPiso);
        espacoTable.setItems(espacosObservable);

        espacoTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        espacoTable.setPrefWidth(500);
        espacoTable.setPrefHeight(400);

        pageContent.getChildren().add(espacoTable);

        borderPane.setTop(navBar);
        borderPane.setLeft(camposEspaco);
        borderPane.setRight(pageContent);

        this.scene = new Scene(borderPane, 800, 500);
        this.stage.setScene(this.scene);
    }

}
