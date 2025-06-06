module br.pucpr.crud_java {
    requires javafx.controls;
    requires javafx.fxml;


    opens br.pucpr.crud_java to javafx.fxml;
    exports br.pucpr.crud_java;
}