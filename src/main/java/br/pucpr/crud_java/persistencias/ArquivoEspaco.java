package br.pucpr.crud_java.persistencias;
import br.pucpr.crud_java.models.Espaco;
import java.io.*;
import java.util.ArrayList;

public class ArquivoEspaco {
    private static final String CAMINHO_ARQUIVO = "espacos.dat";
    public static void salvarLista(ArrayList<Espaco> espacos) {
        FileOutputStream f;
        try{
            File arquivo = new File(CAMINHO_ARQUIVO);
            if (!arquivo.exists()) {
                arquivo.createNewFile();
            }
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo));
            oos.writeObject(espacos);
            oos.close();
            System.out.println("Lista de espaços Salvo com sucesso!");
        } catch (FileNotFoundException e) {
            System.err.println("Erro ao salvar sua lista: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erro ao salvar sua lista: " + e.getMessage());
        }
    }
    public static ArrayList<Espaco> lerLista() {
        ArrayList<Espaco> lista = new ArrayList<>();
        try{
            File arquivo = new File(CAMINHO_ARQUIVO);
            if (arquivo.exists()) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(CAMINHO_ARQUIVO));
                lista = (ArrayList<Espaco>) ois.readObject();
                ois.close();
            }
        } catch (ClassNotFoundException e){
            System.err.println("Erro ao ler sua lista: " + e.getMessage());
        } catch (IOException e){
            System.err.println("Erro ao ler sua lista: " + e.getMessage());
        }
        return lista;
    }
    public static void adicionarEspaco(Espaco novoEspaco) {
        ArrayList<Espaco> espacos = lerLista();
        espacos.add(novoEspaco);
        salvarLista(espacos);
    }
}
