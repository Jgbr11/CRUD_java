package br.pucpr.crud_java.views;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class BoletoView {
    private Stage stage;
    private Scene cena;

    public BoletoView(Stage stage){
        this.stage = stage;
    }

    public void mostrar(){
        criarUI();
        this.stage.show();
    }

    private void criarUI(){
        stage.setTitle("Boletos");

        HBox navBar = new HBox();

        Button btnHome = new Button("Home");
        Button btnLocatarios = new Button("Locatários");
        Button btnContratos = new Button("Contratos");
        Button btnLojas = new Button("Lojas");
        Button btnEspacos = new Button("Espaços");

        navBar.getChildren().add(btnHome);
        navBar.getChildren().add(btnLocatarios);
        navBar.getChildren().add(btnContratos);
        navBar.getChildren().add(btnLojas);
        navBar.getChildren().add(btnEspacos);

        this.cena = new Scene(navBar, 800, 500);
        this.stage.setScene(this.cena);

    }

}
