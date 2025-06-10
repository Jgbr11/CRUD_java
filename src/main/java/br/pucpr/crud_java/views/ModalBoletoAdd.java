package br.pucpr.crud_java.views;

import br.pucpr.crud_java.alerts.Alerts;
import br.pucpr.crud_java.models.Boleto;
import br.pucpr.crud_java.persistencias.ArquivoBoleto;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ModalBoletoAdd {
    private Stage stage;
    private Scene cena;

    private Stage stageOwner;

    public ModalBoletoAdd(Stage stageOwner){
        this.stageOwner = stageOwner;
    }

    public void mostrar(){
        criarUI();
        stage.show();
    }

    public void criarUI() {

        Button btnVoltar = new Button("Voltar");
        btnVoltar.setOnAction(e -> {
            this.stage.close();
        });
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
                        BoletoView.boletosObservable.add(novoBoleto);

                        Alerts.alertInfo("Cadastrado", "Boleto cadastrado com sucesso");

                        txtNmrDoc.clear();
                        txtVal.clear();
                        txtLinhaDig.clear();
                    } catch (NumberFormatException ex) {
                        Alerts.alertError("Erro", "Insira dados válidos!");
                    }
                }
        );

        camposBol.getChildren().addAll(labelNmrDoc, txtNmrDoc, labelVal, txtVal, labelVenc, txtVenc, labelCedente,
                txtCedente, labelBanco, txtBanco, labelLinhaDig, txtLinhaDig, btnCad);

        this.cena = new Scene(camposBol, 500, 200);
        this.stage.setScene(this.cena);
    }
}
