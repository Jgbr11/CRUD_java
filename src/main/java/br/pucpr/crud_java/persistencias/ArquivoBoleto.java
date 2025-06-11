package br.pucpr.crud_java.persistencias;

import br.pucpr.crud_java.models.Boleto;
import br.pucpr.crud_java.models.Contrato;
import br.pucpr.crud_java.models.Locatario;
import br.pucpr.crud_java.models.Loja;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

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
            if (novoBoleto.getNumeroDocumento() == boleto.getNumeroDocumento()){
                System.out.println("Boleto já existente!");
                return;
            }
        }
        boletos.add(novoBoleto);
        salvarLista(boletos);
    }

    public static void removerBoleto(long numeroDocumento){
        ArrayList<Boleto> boletos = lerLista();
        Boolean boletoRemovido = false;
        for (Boleto b : boletos){
            if (numeroDocumento >= 0 && b.getNumeroDocumento() ==
                    numeroDocumento){
                boletos.remove(b);
                boletoRemovido = true;
                break;
            }
        }
        if(boletoRemovido) {
            salvarLista(boletos);
            System.out.println("Boleto removido com sucesso!");
        } else {
            System.err.println("Boleto não encontrado ou inexistente!");
        }
    }

    public static void editarBoleto(long numeroDocumento,
                                    double valor,
                                    LocalDate vencimento, String cedente,
                                    String banco, String linhaDigitavel){
        ArrayList<Boleto> boletos = lerLista();

        for (Boleto b : boletos){
            if (b.getNumeroDocumento() == numeroDocumento){
                b.setNumeroDocumento(numeroDocumento);
                b.setValor(valor);
                b.setVencimento(vencimento);
                b.setCedente(cedente);
                b.setBanco(banco);
                b.setLinhaDigitavel(linhaDigitavel);
                salvarLista(boletos);
                System.out.println("Boleto atualizado com sucesso!");
                return;
            }
        }
        System.out.println("Boleto inexistente ou não encontrado");
    }





}
