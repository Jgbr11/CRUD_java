package br.pucpr.crud_java;

import br.pucpr.crud_java.models.Boleto;
import br.pucpr.crud_java.models.Locatario;
import br.pucpr.crud_java.persistencias.ArquivoBoleto;
import br.pucpr.crud_java.models.Loja;
import br.pucpr.crud_java.persistencias.ArquivoLocatario;
import br.pucpr.crud_java.persistencias.ArquivoLoja;
import br.pucpr.crud_java.models.Espaco;
import br.pucpr.crud_java.persistencias.ArquivoEspaco;
import java.util.ArrayList;

public class Programa {
    public static void main(String[] args){
        Boleto b1 = new Boleto();
        Boleto b2 = new Boleto();

        Locatario empresa1 = new Locatario("12838494838", "CoritibaFC", "41984838444", "coritiba@gmail.com");
        Locatario empresa2 = new Locatario("83748393849", "AhtleticoPR", "4199384738", "athelticopr@gmail.com");
        Espaco espaco1 = new Espaco(2, 22);
        Espaco espaco2 = new Espaco(1, 24);
        System.out.println("\nLogs do Sistema:");
        System.out.println("-----------------------------------------------------------");

        ArquivoBoleto.adicionarBoleto(b1);
        ArquivoBoleto.adicionarBoleto(b2);

        ArquivoLocatario.adicionarLocatario(empresa1);
        ArquivoLocatario.adicionarLocatario(empresa2);
        ArquivoEspaco.adicionarEspaco(espaco1);
        ArquivoEspaco.adicionarEspaco(espaco2);


        ArquivoEspaco.editarEspaco(1, 3, 25);
        ArquivoEspaco.editarEspaco(4, 4, 28);



        ArquivoEspaco.excluirEspaco(1);


        ArrayList<Boleto> boletos = ArquivoBoleto.lerLista();
        ArrayList<Loja> lojas = ArquivoLoja.lerLista();
        ArrayList<Locatario> locatarios = ArquivoLocatario.lerLista();
        ArrayList<Espaco> espacos = ArquivoEspaco.lerLista();

        System.out.println("-----------------------------------------------------------\n\n");

        System.out.println("-----------------------------------------------------------");
        System.out.println("Boletos no arquivo: \n");
        for (Boleto boleto : boletos) {
            System.out.println(boleto);
        }
        System.out.println("-----------------------------------------------------------\n\n");
        System.out.println("-----------------------------------------------------------");
        System.out.println("Lojas no arquivo: \n");
        for (Loja loja : lojas) {
            System.out.println(loja);
        }
        System.out.println("-----------------------------------------------------------\n\n");
        System.out.println("-----------------------------------------------------------");
        System.out.println("Locatarios no arquivo: \n");
        for (Locatario locatario : locatarios) {
            System.out.println(locatario);
        }
        System.out.println("-----------------------------------------------------------\n\n");
        System.out.println("-----------------------------------------------------------");
        System.out.println("Espaços no arquivo: \n");
        for (Espaco espaco: espacos) {
            System.out.println(espaco);
        }
        System.out.println("-----------------------------------------------------------\n\n");





        System.out.println("Locatarios no arquivo: \n");
        for (Locatario locatario : locatarios) {
            System.out.println(locatario);
        }
        System.out.println("-----------------------------------------------------------\n\n");


    }
}
