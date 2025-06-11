package br.pucpr.crud_java.views;

import br.pucpr.crud_java.alerts.Alerts;
import br.pucpr.crud_java.models.Boleto;
import br.pucpr.crud_java.persistencias.ArquivoBoleto;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;

public class ModalBoletoEdit {
    private Stage stage;
    private Scene cena;
    private Boleto boleto;

    private Stage stageOwner;

    public ModalBoletoEdit(Stage stageOwner, Boleto boleto
    ){
        this.stageOwner = stageOwner;
        this.boleto = boleto;
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

        Label labelNmrDoc = new Label("Número do Documento");
        TextField txtNmrDoc = new TextField(String.valueOf(boleto.getNumeroDocumento()));
        txtNmrDoc.setPromptText("Digite o nº");
        txtNmrDoc.setEditable(false);

        Label labelVal = new Label("Valor");
        TextField txtVal = new TextField(String.valueOf(boleto.getValor()));
        txtVal.setPromptText("Digite o valor");

        Label labelDataVenc = new Label("Data de vencimento");
        DatePicker datePickerVenc = new DatePicker(boleto.getVencimento());
        datePickerVenc.setPromptText("Escolha a data");

        Label labelCedente = new Label("Cedente");
        TextField txtCedente = new TextField("Tijucas Open");
        txtCedente.setEditable(false);

        Label labelBanco = new Label("Banco");
        TextField txtBanco = new TextField("Banco do Brasil");
        txtBanco.setEditable(false);

        Label labelLinhaDig = new Label("Linha digitável");
        TextField txtLinhaDig = new TextField(boleto.getLinhaDigitavel());
        txtLinhaDig.setPromptText("Preencha a linha digitável");

        Button btnEditar = new Button("Editar");
        btnEditar.setOnAction(e -> {
                    try {
                            long numDoc = Integer.parseInt(txtNmrDoc.getText());
                            double valor = Double.parseDouble(txtVal.getText());
                            LocalDate vencimento = datePickerVenc.getValue();
                            String cedente = txtCedente.getText();
                            String banco = txtBanco.getText();
                            String linhaDig = txtLinhaDig.getText();
                        if (numDoc >= 0 && valor >= 0 && vencimento != null && linhaDig != "") {

                            ArquivoBoleto.editarBoleto(numDoc, valor,
                                    vencimento, cedente, banco, linhaDig);

                            Alerts.alertInfo("Editado",
                                    "Boleto editado com sucesso");

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

        camposBol.getChildren().addAll(labelNmrDoc, txtNmrDoc, labelVal,
                txtVal, labelDataVenc, datePickerVenc, labelCedente,
                txtCedente, labelBanco, txtBanco, labelLinhaDig, txtLinhaDig,
                btnEditar);

        this.cena = new Scene(camposBol, 800, 500);
        this.stage.setScene(this.cena);
    }
}
