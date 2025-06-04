package br.pucpr.crud_java;

import java.util.Scanner;

import br.pucpr.crud_java.models.*;
import br.pucpr.crud_java.persistencias.*;

import java.util.ArrayList;

public class Programa {
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args){

        exibirMenuPrincipal();

        input.close();
        System.out.println("Programa encerrado.");
    }

    // METODO DE MENU PRINCIPAL
    public static void exibirMenuPrincipal() {
        int opcao;
        do {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Gerenciar Locatários");
            System.out.println("2. Gerenciar Contratos");
            System.out.println("3. Gerenciar Lojas"); //
            System.out.println("4. Gerenciar Boletos");
            System.out.println("5. Gerenciar Espaços");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = input.nextInt();
            input.nextLine();

            switch (opcao) {
                case 1:
                    gerenciarLocatarios();
                    break;
                case 2:
                    System.out.println("Gerenciar Contratos (em desenvolvimento)...");
                    // Implementar gerenciarContratos()
                    break;
                case 3:
                    gerenciarLojas();
                    break;
                case 4:
                    gerenciarBoletos();
                    break;
                case 5:
                    gerenciarEspacos();
                    break;
                case 0:
                    System.out.println("Saindo do programa.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    // METODO DE GERENCIAMENTO DE LOCATÁRIOS
    public static void gerenciarLocatarios() {
        int opcao;
        do {
            System.out.println("\n--- GERENCIAR LOCATÁRIOS ---");
            System.out.println("1. Cadastrar Locatário(s)");
            System.out.println("2. Editar Locatário");
            System.out.println("3. Excluir Locatário");
            System.out.println("4. Listar Locatários");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            opcao = input.nextInt();
            input.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarLocatarios();
                    break;
                case 2:
                    editarLocatarioPeloMenu();
                    break;
                case 3:
                    excluirLocatarioPeloMenu();
                    break;
                case 4:
                    listarTodosLocatarios();
                    break;
                case 0:
                    System.out.println("Voltando ao Menu Principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    // METODO DE ADICIONAR LOCATÁRIOS
    public static void cadastrarLocatarios() {
        String adicionarOutro = "s";
        while (adicionarOutro.equalsIgnoreCase("s")) {
            System.out.println("\n--- CADASTRAR NOVO LOCATÁRIO ---");
            System.out.print("Digite o CNPJ do locatário: ");
            String empresa_cnpj = input.nextLine();
            System.out.print("Digite o nome do locatário: ");
            String empresa_nome = input.nextLine();
            System.out.print("Digite o telefone do locatário: ");
            String empresa_telefone = input.nextLine();
            System.out.print("Digite o email do locatário: ");
            String empresa_email = input.nextLine();

            Locatario novoLocatario = new Locatario(empresa_cnpj, empresa_nome, empresa_telefone, empresa_email);
            ArquivoLocatario.adicionarLocatario(novoLocatario);

            System.out.print("Deseja cadastrar outro locatário? (s/n): ");
            adicionarOutro = input.nextLine();
        }
    }

    // METODO DE EDITAR LOCATÁRIOS
    public static void editarLocatarioPeloMenu() {
        System.out.println("\n--- EDITAR LOCATÁRIO ---");
        System.out.print("Digite o CNPJ do locatário que deseja editar: ");
        String cnpjParaEditar = input.nextLine();

        Locatario locatarioExistente = null;
        ArrayList<Locatario> locatarios = ArquivoLocatario.lerLista();
        for (Locatario loc : locatarios) {
            if (loc.getLocatario_cnpj().equals(cnpjParaEditar)) {
                locatarioExistente = loc;
                break;
            }
        }

        if (locatarioExistente == null) {
            System.out.println("Locatário com CNPJ " + cnpjParaEditar + " não encontrado.");
            return;
        }

        System.out.println("\nLocatário encontrado: " + locatarioExistente.getLocatario_nome());
        System.out.println("Deixe o campo em branco para manter o valor atual.");

        String novoNome;
        System.out.print("Digite o NOVO nome do locatário (atual: " + locatarioExistente.getLocatario_nome() + "): ");
        String entradaNome = input.nextLine();
        if (entradaNome.trim().isEmpty()) {
            novoNome = locatarioExistente.getLocatario_nome();
        } else {
            novoNome = entradaNome;
        }

        String novoTelefone;
        System.out.print("Digite o NOVO telefone do locatário (atual: " + locatarioExistente.getLocatario_telefone() + "): ");
        String entradaTelefone = input.nextLine();
        if (entradaTelefone.trim().isEmpty()) {
            novoTelefone = locatarioExistente.getLocatario_telefone();
        } else {
            novoTelefone = entradaTelefone;
        }

        String novoEmail;
        System.out.print("Digite o NOVO email do locatário (atual: " + locatarioExistente.getLocatario_email() + "): ");
        String entradaEmail = input.nextLine();
        if (entradaEmail.trim().isEmpty()) {
            novoEmail = locatarioExistente.getLocatario_email();
        } else {
            novoEmail = entradaEmail;
        }

        ArquivoLocatario.editarLocatario(cnpjParaEditar, novoNome, novoTelefone, novoEmail);
    }

    // METODO DE EXCLUIR LOCATÁRIOS
    public static void excluirLocatarioPeloMenu() {
        System.out.println("\n--- EXCLUIR LOCATÁRIO ---");
        System.out.print("Digite o CNPJ do locatário que deseja excluir: ");
        String cnpjParaExcluir = input.nextLine();

        ArquivoLocatario.removerLocatario(cnpjParaExcluir);
    }

    // METODO DE LISTAR LOCATÁRIOS
    public static void listarTodosLocatarios() {
        System.out.println("\n--- LISTA DE LOCATÁRIOS ---");
        ArrayList<Locatario> locatarios = ArquivoLocatario.lerLista();
        if (locatarios.isEmpty()) {
            System.out.println("Nenhum locatário cadastrado.");
        } else {
            for (Locatario locatario : locatarios) {
                System.out.println(locatario);
            }
        }
        System.out.println("---------------------------");
    }










    //METODOS DE GERENCIAMENTO DE LOJAS
    public static void gerenciarLojas() {
        int opcao;
        do {
            System.out.println("\n--- GERENCIAR LOJAS ---");
            System.out.println("1. Cadastrar Loja(s)");
            System.out.println("2. Editar Loja");
            System.out.println("3. Excluir Loja");
            System.out.println("4. Listar Lojas");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            opcao = input.nextInt();
            input.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1:
                    cadastrarLojas();
                    break;
                case 2:
                    editarLojaPeloMenu();
                    break;
                case 3:
                    excluirLojaPeloMenu();
                    break;
                case 4:
                    listarTodasLojas();
                    break;
                case 0:
                    System.out.println("Voltando ao Menu Principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    // METODO DE ADICIONAR LOJAS
    public static void cadastrarLojas() {
        String adicionarOutra = "s";
        while (adicionarOutra.equalsIgnoreCase("s")) {
            System.out.println("\n--- CADASTRAR NOVA LOJA ---");
            // Nota: Se o ID da Loja for autoincrementado, não peça ao usuário.
            // Se precisar do ID aqui, adapte.
            System.out.print("Digite o nome da loja: ");
            String nome = input.nextLine();
            System.out.print("Digite o telefone da loja: ");
            String telefone = input.nextLine();
            System.out.print("Digite o tipo de loja: ");
            String tipo = input.nextLine();
            System.out.print("Digite a descrição da loja: ");
            String descricao = input.nextLine();


            Loja novaLoja = new Loja(nome, telefone, tipo, descricao);
            ArquivoLoja.adicionarLoja(novaLoja);

            System.out.print("Deseja cadastrar outra loja? (s/n): ");
            adicionarOutra = input.nextLine();
        }
    }

    // METODO DE EDITAR LOJAS
    public static void editarLojaPeloMenu() {
        System.out.println("\n--- EDITAR LOJA ---");
        System.out.print("Digite o ID da loja que deseja editar: ");
        int idParaEditar = input.nextInt();
        input.nextLine();

        Loja lojaExistente = null;
        ArrayList<Loja> lojas = ArquivoLoja.lerLista();
        for (Loja l : lojas) {
            if (l.getLojaId() == idParaEditar) {
                lojaExistente = l;
                break;
            }
        }

        if (lojaExistente == null) {
            System.out.println("Loja com ID " + idParaEditar + " não encontrada.");
            return;
        }

        System.out.println("\nLoja encontrada: " + lojaExistente.getLojaNome());
        System.out.println("Deixe o campo em branco para manter o valor atual.");

        String novoNome;
        System.out.print("Digite o NOVO nome da loja (atual: " + lojaExistente.getLojaNome() + "): ");
        String entradaNome = input.nextLine();
        if (entradaNome.trim().isEmpty()) {
            novoNome = lojaExistente.getLojaNome();
        } else {
            novoNome = entradaNome;
        }

        String novoTelefone;
        System.out.print("Digite o NOVO telefone da loja (atual: " + lojaExistente.getLojaTelefone() + "): ");
        String entradaTelefone = input.nextLine();
        if (entradaTelefone.trim().isEmpty()) {
            novoTelefone = lojaExistente.getLojaTelefone();
        } else {
            novoTelefone = entradaTelefone;
        }

        String novoTipo;
        System.out.print("Digite o NOVO tipo da loja (atual: " + lojaExistente.getLojaTipo() + "): ");
        String entradaTipo = input.nextLine();
        if (entradaTipo.trim().isEmpty()) {
            novoTipo = lojaExistente.getLojaTipo();
        } else {
            novoTipo = entradaTipo;
        }

        String novaLogo;
        System.out.print("Digite a NOVA logo da loja (atual: " + lojaExistente.getLojaLogo() + "): ");
        String entradaLogo = input.nextLine();
        if (entradaLogo.trim().isEmpty()) {
            novaLogo = lojaExistente.getLojaLogo();
        } else {
            novaLogo = entradaLogo;
        }

        ArquivoLoja.editarLoja(idParaEditar, novoNome, novoTelefone, novoTipo, novaLogo);
    }

    // METODO DE EXCLUIR LOJAS
    public static void excluirLojaPeloMenu() {
        System.out.println("\n--- EXCLUIR LOJA ---");
        System.out.print("Digite o ID da loja que deseja excluir: ");
        int idParaExcluir = input.nextInt();
        input.nextLine(); // Consumir a quebra de linha

        ArquivoLoja.excluirLoja(idParaExcluir);
    }

    // METODO DE LISTAR LOJAS
    public static void listarTodasLojas() {
        System.out.println("\n--- LISTA DE LOJAS ---");
        ArrayList<Loja> lojas = ArquivoLoja.lerLista();
        if (lojas.isEmpty()) {
            System.out.println("Nenhuma loja cadastrada.");
        } else {
            for (Loja loja : lojas) {
                System.out.println(loja);
            }
        }
        System.out.println("----------------------");
    }












    //METODOS DE GERENCIAMENTO DE BOLETOS
    public static void gerenciarBoletos() {
        int opcao;
        do {
            System.out.println("\n--- GERENCIAR BOLETOS ---");
            System.out.println("1. Cadastrar Boleto(s)");
            System.out.println("2. Editar Boleto"); // Se houver edição de Boleto
            System.out.println("3. Excluir Boleto");
            System.out.println("4. Listar Boletos");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            opcao = input.nextInt();
            input.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1:
                    cadastrarBoletos();
                    break;
                case 2:
                    editarBoletosPeloMenu();
                    break;
                case 3:
                    excluirBoletoPeloMenu();
                    break;
                case 4:
                    listarTodosBoletos();
                    break;
                case 0:
                    System.out.println("Voltando ao Menu Principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    // METODO DE ADICIONAR BOLETOS
    public static void cadastrarBoletos() {
        String adicionarOutro = "s";
        while (adicionarOutro.equalsIgnoreCase("s")) {
            System.out.println("\n--- CADASTRAR NOVO BOLETO ---");

            Boleto novoBoleto = new Boleto();

            ArquivoBoleto.adicionarBoleto(novoBoleto);

            System.out.print("Deseja cadastrar outro boleto? (s/n): ");
            adicionarOutro = input.nextLine();
        }
    }


    // METODO DE EDITAR BOLETOS
    public static void editarBoletosPeloMenu() {
        System.out.println("\n--- EDITAR BOLETO ---");
        System.out.print("Digite o ID do Boleto que deseja editar: ");
        int idParaEditar = input.nextInt();
        input.nextLine(); // Consumir a quebra de linha

        Boleto boletoExistente = null;
        ArrayList<Boleto> boletos = ArquivoBoleto.lerLista();
        for (Boleto b : boletos) {
            if (b.getIdBoleto() == idParaEditar) {
                boletoExistente = b;
                break;
            }
        }

        if (boletoExistente == null) {
            System.out.println("Boleto com ID " + idParaEditar + " não encontrado.");
            return;
        }

        System.out.println("\nBoleto encontrado (ID: " + boletoExistente.getIdBoleto() + ").");
        System.out.println("--- Dados Atuais ---");
        System.out.println("Número Documento: " + boletoExistente.getNumeroDocumento());
        System.out.println("Vencimento: " + boletoExistente.getVencimento());
        System.out.println("Código Barras: " + boletoExistente.getCodBarras());
        System.out.println("Linha Digitável: " + boletoExistente.getLinhaDigitavel());
        System.out.println("Contrato Associado ID: " + (boletoExistente.getContrato() != null ? boletoExistente.getContrato().getIdContrato() : "Nenhum"));
        System.out.println("--------------------");
        System.out.println("Deixe o campo em branco para manter o valor atual.");


        // Atributos editáveis: numeroDocumento, vencimento, codBarras, linhaDigitavel, contrato
        int novoNumeroDocumento;
        System.out.print("Digite o NOVO número do documento (atual: " + boletoExistente.getNumeroDocumento() + "): ");
        String entradaNumeroDocumento = input.nextLine();
        if (entradaNumeroDocumento.trim().isEmpty()) {
            novoNumeroDocumento = boletoExistente.getNumeroDocumento();
        } else {
            try {
                novoNumeroDocumento = Integer.parseInt(entradaNumeroDocumento);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida para número do documento. Mantendo valor atual.");
                novoNumeroDocumento = boletoExistente.getNumeroDocumento();
            }
        }

        String novoVencimento;
        System.out.print("Digite o NOVO vencimento (atual: " + boletoExistente.getVencimento() + "): ");
        String entradaVencimento = input.nextLine();
        if (entradaVencimento.trim().isEmpty()) {
            novoVencimento = boletoExistente.getVencimento();
        } else {
            novoVencimento = entradaVencimento;
        }

        String novoCodBarras;
        System.out.print("Digite o NOVO código de barras (atual: " + boletoExistente.getCodBarras() + "): ");
        String entradaCodBarras = input.nextLine();
        if (entradaCodBarras.trim().isEmpty()) {
            novoCodBarras = boletoExistente.getCodBarras();
        } else {
            novoCodBarras = entradaCodBarras;
        }

        String novaLinhaDigitavel;
        System.out.print("Digite a NOVA linha digitável (atual: " + boletoExistente.getLinhaDigitavel() + "): ");
        String entradaLinhaDigitavel = input.nextLine();
        if (entradaLinhaDigitavel.trim().isEmpty()) {
            novaLinhaDigitavel = boletoExistente.getLinhaDigitavel();
        } else {
            novaLinhaDigitavel = entradaLinhaDigitavel;
        }

        // Lógica para Contrato
        Contrato contratoAssociado = boletoExistente.getContrato(); // Começa com o contrato atual do boleto

        System.out.print("Deseja alterar/associar um Contrato? (s/n, atual: " + (contratoAssociado != null ? contratoAssociado.getIdContrato() : "Nenhum") + "): ");
        String respostaContrato = input.nextLine();

        if (respostaContrato.equalsIgnoreCase("s")) {
            System.out.print("Digite o ID do Contrato para associar (0 para desassociar): ");
            int idContratoParaAssociar = input.nextInt();
            input.nextLine();

            if (idContratoParaAssociar == 0) {
                contratoAssociado = null;
                System.out.println("Contrato desassociado com sucesso.");
            } else {
                Contrato contratoEncontrado = ArquivoContrato.buscarContratoPorId(idContratoParaAssociar);

                if (contratoEncontrado != null) {
                    contratoAssociado = contratoEncontrado;
                    System.out.println("Contrato ID " + idContratoParaAssociar + " associado com sucesso!");
                } else {
                    System.out.println("Contrato com ID " + idContratoParaAssociar + " não encontrado. Mantendo contrato anterior ou nulo.");
                }
            }
        }

        ArquivoBoleto.editarBoleto(idParaEditar, novoNumeroDocumento, novoVencimento, novoCodBarras, novaLinhaDigitavel, contratoAssociado);
    }




    // METODO DE EXCLUIR BOLETOS
    public static void excluirBoletoPeloMenu() {
        System.out.println("\n--- EXCLUIR BOLETO ---");
        System.out.print("Digite o ID do boleto que deseja excluir: ");
        int idParaExcluir = input.nextInt();
        input.nextLine();

        ArquivoBoleto.excluirBoleto(idParaExcluir);
    }

    // METODO DE LISTAR BOLETOS
    public static void listarTodosBoletos() {
        System.out.println("\n--- LISTA DE BOLETOS ---");
        ArrayList<Boleto> boletos = ArquivoBoleto.lerLista();
        if (boletos.isEmpty()) {
            System.out.println("Nenhum boleto cadastrado.");
        } else {
            for (Boleto boleto : boletos) {
                System.out.println(boleto);
            }
        }
        System.out.println("------------------------");
    }

    //METODOS PARA GERENCIAR CONTRATOS

    // METODO DE ADICIONAR CONTRATOS
    // METODO DE EDITAR CONTRATOS
    // METODO DE EXCLUIR CONTRATOS
    // METODO DE LISTAR CONTRATOS



    //METODOS PARA GERENCIAR ESPACOS
    public static void gerenciarEspacos() {
        int opcao;
        do {
            System.out.println("\n--- GERENCIAR ESPACOS ---");
            System.out.println("1. Cadastrar Espaco(s)");
            System.out.println("2. Editar Espaco"); // Se houver edição de Boleto
            System.out.println("3. Excluir Espaco");
            System.out.println("4. Listar Espaco");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            opcao = input.nextInt();
            input.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarEspacos();
                    break;
                case 2:
                    editarEspacosPeloMenu();
                    break;
                case 3:
                    excluirEspacoPeloMenu();
                    break;
                case 4:
                    listarTodosEspacos();
                    break;
                case 0:
                    System.out.println("Voltando ao Menu Principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    // METODO DE ADICIONAR ESPACOS
    public static void cadastrarEspacos() {
        String adicionarOutro = "s";
        while (adicionarOutro.equalsIgnoreCase("s")) {
            System.out.println("\n--- CADASTRAR NOVO ESPACO ---");

            System.out.print("Digite o piso do espaço: ");
            int piso = input.nextInt();
            input.nextLine(); // Consumir a quebra de linha

            System.out.print("Digite a área do espaço: ");
            double area = input.nextDouble();
            input.nextLine(); // Consumir a quebra de linha

            Espaco novoEspaco = new Espaco(piso, area); // Criar Espaco com os dados fornecidos
            ArquivoEspaco.adicionarEspaco(novoEspaco);

            System.out.print("Deseja cadastrar outro espaço? (s/n): ");
            adicionarOutro = input.nextLine();
        }
    }




    // METODO DE EDITAR ESPACOS
    public static void editarEspacosPeloMenu() {
        System.out.println("\n--- EDITAR ESPACO ---");
        System.out.print("Digite o ID do espaco que deseja editar: ");
        int idParaEditar = input.nextInt();
        input.nextLine(); // Consumir a quebra de linha após ler o int

        Espaco espacoExistente = null;
        ArrayList<Espaco> espacos = ArquivoEspaco.lerLista();
        for (Espaco esp : espacos) {
            if (esp.getIdEspaco() == idParaEditar) {
                espacoExistente = esp;
                break;
            }
        }

        if (espacoExistente == null) {
            System.out.println("Espaco com ID " + idParaEditar + " não encontrado.");
            return;
        }

        System.out.println("\nEspaco encontrado!");
        System.out.println("Deixe o campo em branco para manter o valor atual.");

        int novoPiso;
        System.out.print("Digite o NOVO piso do espaco (atual: " + espacoExistente.getPiso() + "): ");
        String entradaPiso = input.nextLine();
        if (entradaPiso.trim().isEmpty()) {
            novoPiso = espacoExistente.getPiso();
        } else {
            try {
                novoPiso = Integer.parseInt(entradaPiso);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida para piso. Mantendo valor atual.");
                novoPiso = espacoExistente.getPiso();
            }
        }

        double novaArea;
        System.out.print("Digite a NOVA área do espaco (atual: " + espacoExistente.getArea() + "): ");
        String entradaArea = input.nextLine();
        if (entradaArea.trim().isEmpty()) {
            novaArea = espacoExistente.getArea();
        } else {
            try {
                novaArea = Double.parseDouble(entradaArea);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida para área. Mantendo valor atual.");
                novaArea = espacoExistente.getArea();
            }
        }

        ArquivoEspaco.editarEspaco(idParaEditar, novoPiso, novaArea);
    }

    // METODO DE EXCLUIR ESPACOS
    public static void excluirEspacoPeloMenu() {
        System.out.println("\n--- EXCLUIR ESPACO ---");
        System.out.print("Digite o ID do espaco que deseja excluir: ");
        int idParaExcluir = input.nextInt();
        input.nextLine();

        ArquivoEspaco.excluirEspaco(idParaExcluir);
    }


    // METODO DE LISTAR ESPACOS
    public static void listarTodosEspacos() {
        System.out.println("\n--- LISTA DE ESPACOS ---");
        ArrayList<Espaco> espacos = ArquivoEspaco.lerLista();
        if (espacos.isEmpty()) {
            System.out.println("Nenhum espaco cadastrado.");
        } else {
            for (Espaco esp : espacos) {
                System.out.println(esp);
            }
        }
        System.out.println("------------------------");
    }


}