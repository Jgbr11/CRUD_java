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
        for (Espaco espaco : espacos) {
            if (espaco.getIdEspaco() == (novoEspaco.getIdEspaco())) {
                System.out.println("Espaco já existente! Espaço não cadastrado");
                return;
            }
        }
        espacos.add(novoEspaco);
        salvarLista(espacos);
    }
    public static void editarEspaco(int idEspaco, int novoPiso, int novaArea) {
        ArrayList<Espaco> espacos = lerLista();
        for (Espaco espaco : espacos) {
            if (espaco.getIdEspaco() == idEspaco) {
                espaco.setPiso(novoPiso);
                espaco.setArea(novaArea);
                salvarLista(espacos);
                System.out.println("Espaço atualizado com sucesso!");
                return;
            }
        }
        System.out.println("ID não encontrado. Nenhuma alteração feita.");
    }
    public static void excluirEspaco(int idEspaco) {
        ArrayList<Espaco> espacos = lerLista();
        for (Espaco espaco : espacos) {
            if (espaco.getIdEspaco() == idEspaco) {
                espacos.remove(espaco);
                salvarLista(espacos);
                System.out.println("Espaço removido com sucesso!");
                return;
            }
        }
        System.out.println("O ID do espaço não foi encontrado. Não foi possível excluir o espaço!");
    }
}
