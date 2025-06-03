package br.pucpr.crud_java;

import br.pucpr.crud_java.models.Boleto;
import br.pucpr.crud_java.models.Locatario;
import br.pucpr.crud_java.persistencias.ArquivoBoleto;
import br.pucpr.crud_java.models.Loja;
import br.pucpr.crud_java.persistencias.ArquivoLoja;
import java.util.ArrayList;

public class Programa {
    public static void main(String[] args){
        Boleto b1 = new Boleto();
        Boleto b2 = new Boleto();
        Loja l1 = new Loja(1, "CoxaStore", "(41) 985143977", "X", "1º Andar", "Roupas");
        Loja l2 = new Loja(4, "Vivara", "(41) 9845756584", "X", "2º Andar", "Joias");


        ArquivoBoleto.adicionarBoleto(b1);
        ArquivoBoleto.adicionarBoleto(b2);
        ArquivoLoja.adicionarLoja(l1);
        ArquivoLoja.adicionarLoja(l2);


        ArrayList<Boleto> boletos = ArquivoBoleto.lerLista();
        ArrayList<Loja> lojas = ArquivoLoja.lerLista();


        System.out.println("Boletos no arquivo: ");
        for (Boleto boleto : boletos) {
            System.out.println(boleto);
        }

        System.out.println("Lojas no arquivo: ");
        for (Loja loja : lojas) {
            System.out.println(loja);
        }

    }
}
