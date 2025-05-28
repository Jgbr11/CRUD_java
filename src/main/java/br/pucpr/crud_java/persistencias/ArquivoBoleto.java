package br.pucpr.crud_java.persistencias;

import br.pucpr.crud_java.models.Boleto;

import java.io.*;
import java.util.ArrayList;

public class ArquivoBoleto {
    private static final String CAMINHO_ARQUIVO = "boletos.dat";

    public static void salvarLista(ArrayList<Boleto> boletos){
        FileOutputStream f;
        try {
            File arquivo = new File(CAMINHO_ARQUIVO);
            if (!arquivo.exists()){
                arquivo.createNewFile();
            }
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo));
            oos.writeObject(boletos);
            oos.close();
            System.out.println("Lista de boletos salva com sucesso!");
        } catch (FileNotFoundException e){
            System.err.println("Erro ao salvar lista: " + e.getMessage());
        } catch (IOException e){
            System.err.println("Erro ao salvar lista: " + e.getMessage());
        }
    }

    public static ArrayList<Boleto> lerLista(){
        ArrayList<Boleto> lista = new ArrayList<>();
        try {
            File arquivo = new File(CAMINHO_ARQUIVO);
            if (arquivo.exists()) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(CAMINHO_ARQUIVO));
                lista = (ArrayList<Boleto>) ois.readObject();
                ois.close();
            }
        } catch (ClassNotFoundException e){
            System.err.println("Erro ao ler lista: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erro ao ler lista: " + e.getMessage());
        }
        return lista;
    }

    public static void adicionarBoleto(Boleto novoBoleto) {
        ArrayList<Boleto> boletos = lerLista();
        boletos.add(novoBoleto);
        salvarLista(boletos);
    }
}
