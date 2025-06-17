package br.pucpr.crud_java.views;

import br.pucpr.crud_java.alerts.Alerts;
import br.pucpr.crud_java.models.Locatario;
import br.pucpr.crud_java.persistencias.ArquivoLocatario;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;

public class ModalLocatarioEdit {
    private Stage stage;
    private Scene cena;
    private Locatario locatario;

    private Stage stageOwner;

    public ModalLocatarioEdit(Stage stageOwner, Locatario locatario){
        this.stageOwner = stageOwner;
        this.locatario = locatario;
    }

    public void mostrar(){
        criarUI();
        stage.showAndWait();
    }

    public void criarUI() {
        this.stage = new Stage();
        Button btnVoltar = new Button("Voltar");
        btnVoltar.setOnAction(e -> {
            this.stage.close();
        });
        VBox camposBol = new VBox();
        camposBol.setStyle("-fx-padding: 10;");
        camposBol.setSpacing(5);






        Label labelCNPJ = new Label("CNPJ da Empresa");
        TextField txtCNPJ = new TextField(String.valueOf(locatario.getLocatario_cnpj()));
        txtCNPJ.setPromptText("Digite o CNPJ");
        txtCNPJ.setEditable(false);

        Label labelNome = new Label("Nome da Empresa");
        TextField txtNome = new TextField(String.valueOf(locatario.getLocatario_nome()));
        txtNome.setPromptText("Digite o nome da Empresa");
        txtNome.setEditable(false);

        Label labelEmail = new Label("Email");
        TextField txtEmail = new TextField(String.valueOf(locatario.getLocatario_email()));
        txtEmail.setPromptText("Digite o email da Empresa");

        Label labelTelefone = new Label("Telefone");
        TextField txtTelefone = new TextField(String.valueOf(locatario.getLocatario_telefone()));
        txtTelefone.setPromptText("(XX) XXXXX-XXXX");
        adicionarMascaraTelefone(txtTelefone);

        Button btnCadastrar = new Button("Cadastrar Locatário");
        btnCadastrar.setMaxWidth(Double.MAX_VALUE);

        Button btnEditar = new Button("Salvar");
        btnEditar.setOnAction(e -> {
                    try {
                        String cnpjEmpresa = txtCNPJ.getText();
                        String nomeEmpresa = txtNome.getText();
                        String emailEmpresa = txtEmail.getText();
                        String telefoneEmpresa = txtTelefone.getText();
                        if (emailEmpresa != "" && telefoneEmpresa != "") {

                            ArquivoLocatario.editarLocatario(cnpjEmpresa, nomeEmpresa, emailEmpresa, telefoneEmpresa);

                            Alerts.alertInfo("Editado",
                                    "Locatário editado com sucesso");

                            this.stage.close();
                        } else {
                            Alerts.alertError("Erro", "Preencha os campos " +
                                    "corretamente");
                        }
                    } catch (NumberFormatException ex) {
                        Alerts.alertError("Erro", "Insira dados válidos!");
                    }
                }
        );

        camposBol.getChildren().addAll(labelCNPJ, txtCNPJ, labelNome,
                txtNome, labelEmail, txtEmail, labelTelefone,
                txtTelefone,
                btnEditar);

        this.cena = new Scene(camposBol, 800, 500);
        this.stage.setScene(this.cena);
    }

    private void adicionarMascaraTelefone(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            String digitos = newValue.replaceAll("\\D", "");
            if (digitos.length() > 11) digitos = digitos.substring(0, 11);

            String textoFormatado = digitos;
            if (digitos.length() > 2) textoFormatado = "(" + digitos.substring(0, 2) + ") " + digitos.substring(2);
            if (digitos.length() > 7) textoFormatado = "(" + digitos.substring(0, 2) + ") " + digitos.substring(2, 7) + "-" + digitos.substring(7);
            else if (digitos.length() > 6) textoFormatado = "(" + digitos.substring(0, 2) + ") " + digitos.substring(2, 6) + "-" + digitos.substring(6);

            if (!newValue.equals(textoFormatado)) {
                textField.setText(textoFormatado);
                textField.positionCaret(textoFormatado.length());
            }
        });
    }
}
