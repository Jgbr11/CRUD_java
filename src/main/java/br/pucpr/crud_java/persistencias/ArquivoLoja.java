package br.pucpr.crud_java.persistencias;

import br.pucpr.crud_java.models.Locatario;
import br.pucpr.crud_java.models.Loja;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

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
            if (novaLoja.equals(l)) {
                System.out.println("Loja já existente! Loja não cadastrada!");
                return;
            }
        }



        lojas.add(novaLoja);
        salvarLista(lojas);

    }

    public static void editarLoja(Loja novaloja, String novoNome, String novoTelefone, String novoTipo) {
        ArrayList<Loja> lojas = lerLista();
        for (Loja l : lojas) {
            if (novaloja.equals(l)) {
                l.setLojaNome(novoNome);
                l.setLojaTelefone(novoTelefone);
                l.setLojaTipo(novoTipo);
                salvarLista(lojas);
                System.out.println("Loja atualizada com sucesso!");
                return;
            }
        }
        System.out.println("O ID da loja não foi encontrado, não foi possível atualizar!");

    }

    public static void removerLoja(String lojaNome) {
        ArrayList<Loja> lojas = lerLista();
        for (Loja l : lojas) {
            if (Objects.equals(l.getLojaNome(), lojaNome)) {
                lojas.remove(l);
                salvarLista(lojas);
                System.out.println("Loja removida com sucesso!");
                return;
            }
        }
        System.out.println("O ID da loja não foi encontrado, não foi possível excluir!");
    }


}