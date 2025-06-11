//package br.pucpr.crud_java.regex;
//
//import br.pucpr.crud_java.alerts.Alerts;
//import javafx.scene.control.Alert;
//import javafx.scene.control.DatePicker;
//
//import java.time.DateTimeException;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.time.format.DateTimeParseException;
//import java.util.regex.Pattern;
//
//public class Regex {
//
//    public static void regexData(DatePicker datePicker){
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        datePicker.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
//            if (newValue != null) {
//                String digitos = newValue.replaceAll("\\D", "");
//
//                if (digitos.length() > 8){
//                    digitos = digitos.substring(0, 8);
//                }
//
//                String dataFormatada = digitos;
//
//                if (digitos.length() > 2){
//                    dataFormatada = digitos.substring(0, 2) + "/" + digitos.substring(2);
//                }
//                if (digitos.length() > 4){
//                    dataFormatada = digitos.substring(0, 5) + "/" + digitos.substring(4);
//                }
//
//                if (!newValue.equals(dataFormatada)){
//                    datePicker.getEditor().setText(dataFormatada);
//                }
//
//                try {LocalDate date = LocalDate.parse(dataFormatada, formatter);
//                    datePicker.setValue(date);
//            } catch (DateTimeParseException ignore) {
//                datePicker.setValue(null);
//                }
//        });
//
//        }
//    }
//}
//
