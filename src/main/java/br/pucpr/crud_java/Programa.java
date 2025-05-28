package br.pucpr.crud_java;

import br.pucpr.crud_java.models.Boleto;
import br.pucpr.crud_java.persistencias.ArquivoBoleto;

import java.util.ArrayList;

public class Programa {
    public static void main(String[] args){
        Boleto b1 = new Boleto();
        Boleto b2 = new Boleto();

        ArquivoBoleto.adicionarBoleto(b1);
        ArquivoBoleto.adicionarBoleto(b2);

        ArrayList<Boleto> boletos = ArquivoBoleto.lerLista();

        System.out.println("Boletos no arquivo: ");
        for (Boleto boleto : boletos){
            System.out.println(boleto);
        }
    }
}
