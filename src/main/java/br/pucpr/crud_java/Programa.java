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
        Loja l1 = new Loja("CoxaStore", "(41) 985143977", "X", "Roupas");
        Loja l2 = new Loja("Vivara", "(41) 9845756584", "X", "Joias");
        Locatario empresa1 = new Locatario("12838494838", "CoritibaFC", "41984838444", "coritiba@gmail.com");
        Locatario empresa2 = new Locatario("83748393849", "AhtleticoPR", "4199384738", "athelticopr@gmail.com");
        Espaco espaco1 = new Espaco(2, 22, true);
        Espaco espaco2 = new Espaco(1, 24, false);
        System.out.println("\nLogs do Sistema:");
        System.out.println("-----------------------------------------------------------");

        ArquivoBoleto.adicionarBoleto(b1);
        ArquivoBoleto.adicionarBoleto(b2);
        ArquivoLoja.adicionarLoja(l1);
        ArquivoLoja.adicionarLoja(l2);
        ArquivoLocatario.adicionarLocatario(empresa1);
        ArquivoLocatario.adicionarLocatario(empresa2);

        ArquivoLocatario.editarLocatario("12838494838", "Coxudao", "41984838444", "coritiba@gmail.com");
        ArquivoLoja.editarLoja(22, "GABRIEL", "(41) 9863327822", "Esporte","XY");


        ArquivoLoja.excluirLoja(2);


        ArrayList<Boleto> boletos = ArquivoBoleto.lerLista();
        ArrayList<Loja> lojas = ArquivoLoja.lerLista();
        ArrayList<Locatario> locatarios = ArquivoLocatario.lerLista();

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





        System.out.println("Locatarios no arquivo: \n");
        for (Locatario locatario : locatarios) {
            System.out.println(locatario);
        }
        System.out.println("-----------------------------------------------------------\n\n");


    }
}
