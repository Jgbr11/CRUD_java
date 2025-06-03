package br.pucpr.crud_java.persistencias;

import br.pucpr.crud_java.models.Locatario;
import br.pucpr.crud_java.models.Loja;
import java.io.*;
import java.util.ArrayList;

public class ArquivoLoja {
    private static final String CAMINHO_ARQUIVO = "lojas.dat";


    public static void salvarLista(ArrayList<Loja> lojas) {
        try {
            File arquivo = new File(CAMINHO_ARQUIVO);
            if (!arquivo.exists()) {
                arquivo.createNewFile();
            }
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo));
            oos.writeObject(lojas);
            oos.close();
            System.out.println("Lista de lojas salva com sucesso!");
        } catch (FileNotFoundException e) {
            System.err.println("Erro ao salvar lista de lojas:  " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erro ao salvar lista de lojas: " + e.getMessage());
        }
    }


    public static ArrayList<Loja> lerLista() {
        ArrayList<Loja> lista = new ArrayList<>();
        try {
            File arquivo = new File(CAMINHO_ARQUIVO);
            if (arquivo.exists() && arquivo.length() > 0) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(CAMINHO_ARQUIVO));
                lista = (ArrayList<Loja>) ois.readObject();
                ois.close();
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Erro ao ler lista de lojas:  - " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erro ao ler lista de lojas:   " + e.getMessage());
        }
        return lista;
    }

    public static void adicionarLoja(Loja novaLoja) {
        ArrayList<Loja> lojas = lerLista();

        for (Loja l : lojas) {
            if (l.getLojaId() == (novaLoja.getLojaId())) {
                System.out.println("Loja já existente! Loja não cadastrada!");
                return;
            }
        }

        lojas.add(novaLoja);
        salvarLista(lojas);

    }


}