package br.pucpr.crud_java;

import br.pucpr.crud_java.views.BoletoView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception{
        BoletoView boletoView = new BoletoView(stage);
        boletoView.mostrar();
    }
}
