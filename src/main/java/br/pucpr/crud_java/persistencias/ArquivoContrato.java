package br.pucpr.crud_java.persistencias;

import br.pucpr.crud_java.models.Boleto;
import br.pucpr.crud_java.models.Contrato;
import java.io.*;
import java.util.ArrayList;

public class ArquivoContrato {
    private static final String CAMINHO_ARQUIVO = "contratos.dat";
    public static void salvarLista(ArrayList<Contrato> contratos){
        try {
            File arquivo = new File(CAMINHO_ARQUIVO);
            if (!arquivo.exists()){
                arquivo.createNewFile();
            }
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo));
            oos.writeObject(contratos);
            oos.close();
            System.out.println("Lista de contratos salva com sucesso!");
        } catch (FileNotFoundException e){
            System.err.println("Erro ao salvar lista de contratos: " + e.getMessage());
        } catch (IOException e){
            System.err.println("Erro ao salvar lista de contratos: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Contrato> lerLista(){
        ArrayList<Contrato> lista = new ArrayList<>();
        try {
            File arquivo = new File(CAMINHO_ARQUIVO);
            if (arquivo.exists() && arquivo.length() > 0) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(CAMINHO_ARQUIVO));
                lista = (ArrayList<Contrato>) ois.readObject();
                ois.close();
            }
        } catch (EOFException e) {
            System.err.println("Arquivo de contratos vazio ou corrompido. Iniciando com lista vazia.");
        } catch (ClassNotFoundException e){
            System.err.println("Erro ao ler lista de contratos: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erro ao ler lista de contratos: " + e.getMessage());
        }
        return lista;
    }

    public static void adicionarContrato(Contrato novoContrato) {
        ArrayList<Contrato> contratos = lerLista();
        for (Contrato c : contratos) {
            if (novoContrato.equals(c)) {
                System.out.println("ID do contrato já cadastrado. Contrato não adicionado.");
                return;
            }
        }
        contratos.add(novoContrato);
        salvarLista(contratos);
    }

    public static Contrato buscarContratoPorId(Contrato contrato) {
        ArrayList<Contrato> contratos = lerLista();
        for (Contrato c : contratos) {
            if (contrato.equals(c)) {
                return c;
            }
        }
        return null;
    }

    public static void removerContrato(Contrato contrato){
        ArrayList<Contrato> contratos = lerLista();

        for (Contrato c : contratos){
            if (contrato.equals(c)) {
                contratos.remove(contrato);
                salvarLista(contratos);
            } else {
                System.out.println("Contrato não existe!");
            }
        }
    }

}