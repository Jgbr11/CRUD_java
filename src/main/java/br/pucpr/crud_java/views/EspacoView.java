package br.pucpr.crud_java.views;

import br.pucpr.crud_java.TelaInicial;
import br.pucpr.crud_java.alerts.Alerts;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

import static javafx.collections.FXCollections.observableArrayList;

public class EspacoView {
    private Stage stage;
    private Scene scene;

    private ObservableList<Espaco> espacosObservable = observableArrayList();

    public EspacoView(Stage stage) {
        this.stage = stage;
    }
    public void mostrar(){
        criarUI();
        this.stage.show();
    }
    private void criarUI() {
        stage.setTitle("Gestão de espaços");
        espacosObservable.setAll(ArquivoEspaco.lerLista());

        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-padding: 10;");

        HBox navBar = criarMenuNavegacao();
        borderPane.setTop(navBar);

        VBox painelFormulario = new VBox(10);
        painelFormulario.setStyle("-fx-padding: 10;");
        painelFormulario.setPrefWidth(250);

        Label labelID = new Label("ID do espaço");
        TextField txtID = new TextField();
        txtID.setPromptText("Digite o id do espaço");

        Label labelArea = new Label("Area do espaço");
        TextField txtArea = new TextField();
        txtArea.setPromptText("Digite a área do espaço");

        Label labelPiso = new Label("Piso do espaço");
        TextField txtPiso = new TextField();
        txtPiso.setPromptText("Digite o piso do espaço");

        Button btnCad = new Button("Cadastrar Espaço");
        btnCad.setMaxWidth(Double.MAX_VALUE);

        painelFormulario.getChildren().addAll(labelID, txtID, labelArea, txtArea, labelPiso, txtPiso, btnCad);
        borderPane.setLeft(painelFormulario);

        VBox painelTabela = new VBox(10);
        painelTabela.setStyle("-fx-padding: 10;");

        TableView<Espaco> EspacoTable = criarTabelaEspacos();
        Button btnRemover = new Button("Remover Selecionado");

        painelTabela.getChildren().addAll(EspacoTable, btnRemover);
        borderPane.setCenter(painelTabela);

        btnCad.setOnAction(e -> {
            try {
                int id = Integer.parseInt(txtID.getText());
                double area = Double.parseDouble(txtArea.getText());
                int piso = Integer.parseInt(txtPiso.getText()) ;

                if (id == 0 || area <= 0 || piso > 2 || piso <1 ){ // Validação simples
                    throw new IllegalArgumentException("Preencha todos os campos corretamente!");
                }

                Espaco novoEspaco = new Espaco(id, piso, area);
                ArquivoEspaco.adicionarEspaco(novoEspaco);
                espacosObservable.add(novoEspaco);

                txtID.clear();
                txtArea.clear();
                txtPiso.clear();
                Alerts.alertInfo("Sucesso", "Espaço cadastrada com sucesso!");

            } catch (IllegalArgumentException ex) {
                Alerts.alertError("Erro de Validação", ex.getMessage());
            }
        });

        btnRemover.setOnAction(e -> {
            Espaco espacoSelecionado = EspacoTable.getSelectionModel().getSelectedItem();
            if (espacoSelecionado == null) {
                Alerts.alertWarning("Nenhuma Seleção", "Por favor, selecione um espaço para remover.");
                return;
            }

            Alert confirmacao = new Alert(Alert.AlertType.CONFIRMATION, "Tem certeza?", ButtonType.YES, ButtonType.NO);
            confirmacao.setHeaderText("Remover o espaço de ID '" + espacoSelecionado.getId() + "'?");
            confirmacao.showAndWait().ifPresent(resposta -> {
                if (resposta == ButtonType.YES) {
                    ArquivoEspaco.excluirEspaco(espacoSelecionado.getId());
                    espacosObservable.remove(espacoSelecionado);
                    Alerts.alertInfo("Sucesso", "Espaço removida.");
                }
            });
        });

        Scene cena = new Scene(borderPane, 900, 600);
        this.stage.setScene(cena);
    }

    private TableView<Espaco> criarTabelaEspacos() {
        TableView<Espaco> table = new TableView<>(espacosObservable);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        TableColumn<Espaco, String> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(cell -> new SimpleStringProperty(String.valueOf(cell.getValue().getId())));

        TableColumn<Espaco, String> colArea = new TableColumn<>("Area");
        colArea.setCellValueFactory(cell -> new SimpleStringProperty(String.valueOf(cell.getValue().getArea())));

        TableColumn<Espaco, String> colPiso = new TableColumn<>("Piso");
        colPiso.setCellValueFactory(cell -> new SimpleStringProperty(String.valueOf( cell.getValue().getPiso())));

        table.getColumns().addAll(colId, colArea, colPiso);
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
        btnLocatarios.setOnAction(e -> new LocatarioView(stage).mostrar());

        Button btnContratos = new Button("Contratos");
        btnContratos.setStyle(styleBtn);
        btnContratos.setOnAction(e -> new ContratoView(stage).mostrar());

        Button btnBoletos = new Button("Boletos");
        btnBoletos.setStyle(styleBtn);
        btnBoletos.setOnAction(e -> new BoletoView(stage).mostrar());

        // Adicione aqui os outros botões quando tiver as telas prontas
        Button btnEspaco = new Button("Espaços");
        btnEspaco.setStyle(styleBtn);
        btnEspaco.setOnAction(e -> new EspacoView(stage).mostrar());

        Button btnEspacos = new Button("Espaços");
        btnEspacos.setStyle(styleBtn);
        btnEspacos.setOnAction(e -> this.mostrar());



        navBar.getChildren().addAll(btnHome, btnLocatarios, btnContratos, btnBoletos, btnEspaco, btnEspacos);
        return navBar;
    }
}

