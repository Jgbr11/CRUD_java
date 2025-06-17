package br.pucpr.crud_java.views;

import br.pucpr.crud_java.alerts.Alerts;
import br.pucpr.crud_java.models.Loja;
import br.pucpr.crud_java.persistencias.ArquivoLoja;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class ModalLojaEdit {
    private Stage stage;
    private final Loja loja;

    public ModalLojaEdit(Stage stageOwner, Loja loja) {
        this.loja = loja;
        this.stage = new Stage();
    }

    public void mostrar() {
        criarUI();
        stage.showAndWait();
    }

    public void criarUI() {
        stage.setTitle("Editar Loja");

        VBox camposLoja = new VBox();
        camposLoja.setPadding(new Insets(15));
        camposLoja.setSpacing(10);

        Label labelNomeLoja = new Label("Nome da Loja:");
        TextField textFieldNomeLoja = new TextField(loja.getLojaNome());
        textFieldNomeLoja.setPromptText("Nome da Loja");

        Label labelTel = new Label("Telefone:");
        TextField txtTel = new TextField(loja.getLojaTelefone());
        txtTel.setPromptText("Telefone");

        Label labelTipo = new Label("Tipo da Loja:");
        ComboBox<String> cbTipo = new ComboBox<>();
        cbTipo.getItems().addAll("Roupas", "Joias", "Esportes", "Restaurantes", "Livros");
        cbTipo.setValue(loja.getLojaTipo());
        cbTipo.setMaxWidth(Double.MAX_VALUE);

        Button btnEditar = new Button("Salvar Alterações");


        btnEditar.setOnAction(e -> {
            try {
                String nomeOriginal = loja.getLojaNome();
                String novoNome = textFieldNomeLoja.getText().trim();
                String novoTelefone = txtTel.getText().trim();
                String novoTipo = cbTipo.getValue();
                if (novoNome.isEmpty() || novoTelefone.length() < 14 || novoTipo == null) {
                    throw new IllegalArgumentException("Preencha todos os campos corretamente! O telefone deve estar completo (ex: (XX) XXXXX-XXXX).");
                }

                ArquivoLoja.editarLoja(nomeOriginal, novoNome, novoTelefone, novoTipo);

                Alerts.alertInfo("Sucesso", "Loja editada com sucesso!");
                this.stage.close();

            } catch (IllegalArgumentException ex) {
                Alerts.alertError("Erro de Validação", ex.getMessage());
            } catch (Exception ex) {
                Alerts.alertError("Erro Inesperado", "Ocorreu um erro inesperado ao tentar editar a loja: " + ex.getMessage());
            }
        });

        Button btnVoltar = new Button("Cancelar");
        btnVoltar.setOnAction(e -> this.stage.close());

        camposLoja.getChildren().addAll(
                labelNomeLoja, textFieldNomeLoja,
                labelTel, txtTel,
                labelTipo, cbTipo,
                btnEditar,
                btnVoltar
        );


        Scene cena = new Scene(camposLoja, 400, 350);
        this.stage.setScene(cena);
    }
}