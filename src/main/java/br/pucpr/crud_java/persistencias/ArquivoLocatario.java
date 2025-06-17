package br.pucpr.crud_java.persistencias;

import br.pucpr.crud_java.models.Locatario;

import java.io.*;
import java.util.ArrayList;

public class ArquivoLocatario {
    private static final String CAMINHO_ARQUIVO = "locatarios.dat";

    public static void salvarLista(ArrayList<Locatario> locatarios){
        try {
            File arquivo = new File(CAMINHO_ARQUIVO);
            if (!arquivo.exists()){
                arquivo.createNewFile();
            }
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo));
            oos.writeObject(locatarios);
            oos.close();
            System.out.println("Lista de locatarios salva com sucesso!");
        } catch (FileNotFoundException e){
            System.err.println("Erro ao salvar lista: " + e.getMessage());
        } catch (IOException e){
            System.err.println("Erro ao salvar lista: " + e.getMessage());
        }
    }

    public static ArrayList<Locatario> lerLista(){
        ArrayList<Locatario> lista = new ArrayList<>();
        try {
            File arquivo = new File(CAMINHO_ARQUIVO);
            if (arquivo.exists()) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(CAMINHO_ARQUIVO));
                lista = (ArrayList<Locatario>) ois.readObject();
                ois.close();
            }
        } catch (ClassNotFoundException e){
            System.err.println("Erro ao ler lista: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erro ao ler lista: " + e.getMessage());
        }
        return lista;
    }


    public static void adicionarLocatario(Locatario novoLocatario) {
        ArrayList<Locatario> locatarios = lerLista();

        for (Locatario loc : locatarios) {
            if (loc.getLocatario_cnpj().equals(novoLocatario.getLocatario_cnpj())) {
                System.out.println("CNPJ já cadastrado. Locatário não adicionado.");
                return;
            }
        }

        locatarios.add(novoLocatario);
        salvarLista(locatarios);
    }

    public static void editarLocatario(String cnpj, String novo_nome, String novo_email, String novo_telefone) {
        ArrayList<Locatario> locatarios = lerLista();
        for (Locatario loc : locatarios) {
            if (cnpj != null && cnpj.equals(loc.getLocatario_cnpj())) {
                loc.setLocatario_nome(novo_nome);
                loc.setLocatario_telefone(novo_telefone);
                loc.setLocatario_email(novo_email);
                salvarLista(locatarios);
                System.out.println("Locatário atualizado com sucesso!");
                return;
            }
        }
        System.out.println("CNPJ não encontrado. Nenhuma alteração feita.");
    }


    public static void removerLocatario(String cnpj) {
        ArrayList<Locatario> locatarios = lerLista();
        boolean removido = false;

        for (Locatario loc : locatarios) {
            if (cnpj != null && cnpj.equals(loc.getLocatario_cnpj())) {
                locatarios.remove(loc);
                removido = true;
                break;
            }

        }
        if (removido) {
            salvarLista(locatarios);
            System.out.println("Locatário removido com sucesso!");
        } else {
            System.out.println("CNPJ não encontrado. Nenhuma remoção feita.");
        }
    }
}
