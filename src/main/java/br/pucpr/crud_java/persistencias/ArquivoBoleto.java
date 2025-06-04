package br.pucpr.crud_java.persistencias;

import br.pucpr.crud_java.models.Boleto;
import br.pucpr.crud_java.models.Contrato;
import br.pucpr.crud_java.models.Locatario;
import br.pucpr.crud_java.models.Loja;

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

        for (Boleto boleto : boletos) {
            if (novoBoleto.getIdBoleto() == boleto.getIdBoleto()){
                System.out.println("Boleto já existente!");
                return;
            }
        }
        boletos.add(novoBoleto);
        salvarLista(boletos);
    }

    public static void excluirBoleto(int id){
        ArrayList<Boleto> boletos = lerLista();

        for (Boleto b : boletos){
            if (b.getIdBoleto() == id){
                boletos.remove(b);
                salvarLista(boletos);
            } else {
                System.out.println("Boleto não existe!");
            }
        }
    }

    public static void editarBoleto(int id, int numeroDocumento, String vencimento, String codBarras, String linhaDigitavel, Contrato contrato){
        ArrayList<Boleto> boletos = lerLista();

        for (Boleto b : boletos){
            if (id > 0 && id == b.getIdBoleto()){
                b.setNumeroDocumento(numeroDocumento);
                b.setVencimento(vencimento);
                b.setCodBarras(codBarras);
                b.setLinhaDigitavel(linhaDigitavel);
                b.setContrato(contrato);
                salvarLista(boletos);
                System.out.println("Boleto atualizado com sucesso!");
                return;
            }
        System.out.println("Boleto inexistente ou não encontrado");
        }
    }
}
