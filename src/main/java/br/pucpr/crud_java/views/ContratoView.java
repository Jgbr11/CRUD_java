package br.pucpr.crud_java.views;
import br.pucpr.crud_java.TelaInicial;
import br.pucpr.crud_java.views.LocatarioView;

import br.pucpr.crud_java.models.Contrato;
import br.pucpr.crud_java.persistencias.ArquivoContrato;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
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

public class ContratoView {

    private Stage stage;
    private ObservableList<Contrato> contratosObservable = observableArrayList();

    public ContratoView(Stage stage) {
        this.stage = stage;
    }

    public void mostrar() {
        criarUI();
        this.stage.show();
    }

    private void criarUI() {
        stage.setTitle("Gestão de Contratos");

        // Carrega os dados do arquivo de Contratos
        ArrayList<Contrato> contratos = ArquivoContrato.lerLista();
        contratosObservable.setAll(contratos);

        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-padding: 10;");

        // --- MENU DE NAVEGAÇÃO ---
        HBox navBar = criarMenuNavegacao();
        borderPane.setTop(navBar);

        // --- FORMULÁRIO DE CADASTRO (LADO ESQUERDO) ---
        VBox painelFormulario = new VBox(10);
        painelFormulario.setStyle("-fx-padding: 10;");
        painelFormulario.setPrefWidth(250);

        // Campos do formulário
        Label labelEmpresaId = new Label("ID da Empresa");
        TextField txtEmpresaId = new TextField();
        txtEmpresaId.setPromptText("Digite o ID da empresa");

        Label labelEspacoId = new Label("ID do Espaço");
        TextField txtEspacoId = new TextField();
        txtEspacoId.setPromptText("Digite o ID do espaço");

        Label labelNomeEmpresa = new Label("Nome da Empresa");
        TextField txtNomeEmpresa = new TextField();
        txtNomeEmpresa.setPromptText("Digite o nome da empresa");

        Label labelDataInicio = new Label("Data de Início");
        DatePicker datePickerInicio = new DatePicker();

        Label labelValorMensal = new Label("Valor Mensal");
        TextField txtValorMensal = new TextField();
        txtValorMensal.setPromptText("Ex: 1500.50");

        Label labelStatus = new Label("Status do Contrato");
        CheckBox checkStatus = new CheckBox("Ativo");
        checkStatus.setSelected(true);

        Button btnCadastrar = new Button("Cadastrar Contrato");
        btnCadastrar.setMaxWidth(Double.MAX_VALUE);

        painelFormulario.getChildren().addAll(
                labelEmpresaId, txtEmpresaId,
                labelEspacoId, txtEspacoId,
                labelNomeEmpresa, txtNomeEmpresa,
                labelDataInicio, datePickerInicio,
                labelValorMensal, txtValorMensal,
                labelStatus, checkStatus,
                btnCadastrar
        );
        borderPane.setLeft(painelFormulario);

        // --- TABELA DE CONTRATOS (LADO DIREITO) ---
        VBox painelTabela = new VBox(10);
        painelTabela.setStyle("-fx-padding: 10;");

        TableView<Contrato> contratoTable = criarTabelaContratos();

        Button btnRemover = new Button("Remover Selecionado");
        painelTabela.getChildren().addAll(contratoTable, btnRemover);
        borderPane.setCenter(painelTabela);

        // --- AÇÕES DOS BOTÕES ---
        btnCadastrar.setOnAction(e -> {
            try {
                // Coleta e valida os dados do formulário
                int empresaId = Integer.parseInt(txtEmpresaId.getText());
                int espacoId = Integer.parseInt(txtEspacoId.getText());
                String nomeEmpresa = txtNomeEmpresa.getText();
                LocalDate dataInicio = datePickerInicio.getValue();
                double valorMensal = Double.parseDouble(txtValorMensal.getText());
                boolean status = checkStatus.isSelected();

                if (nomeEmpresa.isEmpty() || dataInicio == null) {
                    throw new IllegalArgumentException("Nome e Data de Início são obrigatórios.");
                }

                // Cria e salva o novo contrato
                Contrato novoContrato = new Contrato(empresaId, espacoId, nomeEmpresa, dataInicio, valorMensal, status);
                ArquivoContrato.adicionarContrato(novoContrato);
                contratosObservable.add(novoContrato);

                // Limpa o formulário e exibe sucesso
                txtEmpresaId.clear();
                txtEspacoId.clear();
                txtNomeEmpresa.clear();
                datePickerInicio.setValue(null);
                txtValorMensal.clear();
                checkStatus.setSelected(true);
                exibirAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Contrato cadastrado com sucesso!");

            } catch (NumberFormatException ex) {
                exibirAlerta(Alert.AlertType.ERROR, "Erro de Formato", "IDs e Valor Mensal devem ser números válidos.");
            } catch (IllegalArgumentException ex) {
                exibirAlerta(Alert.AlertType.ERROR, "Erro de Validação", ex.getMessage());
            }
        });

        btnRemover.setOnAction(e -> {
            Contrato contratoSelecionado = contratoTable.getSelectionModel().getSelectedItem();
            if (contratoSelecionado == null) {
                exibirAlerta(Alert.AlertType.WARNING, "Nenhuma Seleção", "Por favor, selecione um contrato na tabela para remover.");
                return;
            }

            Alert confirmacao = new Alert(Alert.AlertType.CONFIRMATION, "Tem certeza que deseja remover o contrato selecionado?", ButtonType.YES, ButtonType.NO);
            confirmacao.showAndWait().ifPresent(resposta -> {
                if (resposta == ButtonType.YES) {
                    ArquivoContrato.removerContrato(contratoSelecionado.getIdContrato()); // Supondo que Contrato tenha um getId()
                    contratosObservable.remove(contratoSelecionado);
                    exibirAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Contrato removido com sucesso!");
                }
            });
        });

        // --- CONFIGURAÇÃO FINAL DA CENA ---
        Scene cena = new Scene(borderPane, 900, 600);
        this.stage.setScene(cena);
    }

    /**
     * Helper para criar a tabela de contratos, mantendo o código organizado.
     */
    private TableView<Contrato> criarTabelaContratos() {
        TableView<Contrato> table = new TableView<>();
        table.setItems(contratosObservable);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Contrato, String> colEmpresaId = new TableColumn<>("Empresa ID");
        colEmpresaId.setCellValueFactory(cell -> new SimpleStringProperty(String.valueOf(cell.getValue().getEmpresaid())));

        TableColumn<Contrato, String> colEspacoId = new TableColumn<>("Espaço ID");
        colEspacoId.setCellValueFactory(cell -> new SimpleStringProperty(String.valueOf(cell.getValue().getEspacoid())));

        TableColumn<Contrato, String> colNomeEmpresa = new TableColumn<>("Nome Empresa");
        colNomeEmpresa.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getNomeLocatario()));

        TableColumn<Contrato, String> colDataInicio = new TableColumn<>("Data de Início");
        colDataInicio.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getDataInicio().toString()));

        TableColumn<Contrato, String> colValor = new TableColumn<>("Valor Mensal");
        colValor.setCellValueFactory(cell -> new SimpleStringProperty(String.format("R$ %.2f", cell.getValue().getValorMensal())));

        TableColumn<Contrato, String> colStatus = new TableColumn<>("Status");
        colStatus.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().isAtivo() ? "Ativo" : "Inativo"));

        table.getColumns().addAll(colEmpresaId, colEspacoId, colNomeEmpresa, colDataInicio, colValor, colStatus);
        return table;
    }

    /**
     * Helper para criar a barra de navegação superior.
     */
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
        btnContratos.setOnAction(e -> this.mostrar()); // Recarrega a tela atual

        // Adicione aqui os outros botões quando tiver as telas prontas
        Button btnLojas = new Button("Lojas");
        btnLojas.setStyle(styleBtn);
        btnLojas.setOnAction(e -> new LojaView(stage).mostrar());
        Button btnEspacos = new Button("Espaços");
        btnEspacos.setStyle(styleBtn);

        navBar.getChildren().addAll(btnHome, btnLocatarios, btnContratos, btnLojas, btnEspacos);
        return navBar;
    }

    /**
     * Helper para exibir alertas de forma padronizada.
     */
    private void exibirAlerta(Alert.AlertType tipo, String titulo, String conteudo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(conteudo);
        alerta.showAndWait();
    }
}