package br.pucpr.crud_java.persistencias;

import br.pucpr.crud_java.models.Locatario;

import java.io.*;
import java.util.ArrayList;

public class ArquivoLocatario {
    private static final String CAMINHO_ARQUIVO = "locatarios.dat";

    public static void salvarLista(ArrayList<Locatario> locatarios){
        FileOutputStream f;
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

        // Verifica se já existe um locatário com o mesmo CNPJ
        for (Locatario loc : locatarios) {
            if (loc.getLocatario_cpj().equals(novoLocatario.getLocatario_cpj())) {
                System.out.println("CNPJ já cadastrado. Locatário não adicionado.");
                return;
            }
        }

        locatarios.add(novoLocatario);
        salvarLista(locatarios);
    }

}
