package view;

import controller.EleicaoController;
import model.Candidato;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UrnaEletronica {
    private EleicaoController eleicaoController;
    private static final int OP_VOTAR = 1;
    private static final int OP_MAIS_JOVEM = 2;
    private static final int OP_MAIS_VELHO = 3;
    private static final int OP_MAIS_VOTADO = 4;
    private static final int OP_MENOS_VOTADO = 5;
    private static final int OP_TOTAL_VOTOS = 6;
    private static final int OP_MEDIA_VOTOS = 7;
    private static final int OP_SAIR = 0;
    private static final int OP_INVALIDA = -1;
    private static Scanner scanner;

    public UrnaEletronica(EleicaoController eleicaoController) {
        this.eleicaoController = eleicaoController;
    }

    public void iniciar(){
        scanner = new Scanner(System.in);
        int opcao;
        do {
            exibirMenuPrincipal();
            opcao = aceitaOperacao();
            executaOperacao(opcao);
        } while (opcao != OP_SAIR);
    }

    public void executaOperacao(int opcao) {
        switch (opcao) {
            case OP_VOTAR -> votar();
            case OP_MAIS_JOVEM -> exibirCandidatoMaisJovem();
            case OP_MAIS_VELHO -> exibirCandidatoMaisVelho();
            case OP_MAIS_VOTADO -> exibirCandidatoMaisVotado();
            case OP_MENOS_VOTADO -> exibirCandidatoMenosVotado();
            case OP_TOTAL_VOTOS -> exibirTotalVotos();
            case OP_MEDIA_VOTOS -> exibirMediaVotacao();
            case OP_SAIR -> System.out.println("Encerrando a urna eletrônica...");
        }
    }
    private void exibirMenuPrincipal() {
        System.out.println("---- Urna Eletrônica ----");
        System.out.println("1. Votar");
        System.out.println("2. Candidato mais jovem");
        System.out.println("3. Candidato mais velho");
        System.out.println("4. Candidato mais votado");
        System.out.println("5. Candidato menos votado");
        System.out.println("6. Total de votos");
        System.out.println("7. Média de votação");
        System.out.println("0. Sair");
    }

    private void exibirUrna() {
        System.out.println(" _______________________________");
        System.out.println("|                               |");
        System.out.println("|         URNA ELETRÔNICA       |");
        System.out.println("|_______________________________|");
        System.out.println("|                               |");
        System.out.println("|    CANDIDATOS E CÓDIGOS       |");
        System.out.println("|_______________________________|");

        for (Candidato candidato : eleicaoController.getCandidatos()) {
            System.out.printf("|   %s   |   Código: %02d(%s)   |\n", candidato.getNome(), candidato.getPartido().getCodigo(), candidato.getPartido().getNome());
        }

        System.out.println("|_______________________________|");
    }

    private static int aceitaOperacao() {
        int opcao;
        do {
            try {
                System.out.print("Digite a opção desejada: ");
                opcao = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Digite um valor válido!");
                scanner.next();
                opcao = OP_INVALIDA;
            }
        } while (opcao == OP_INVALIDA);

        return opcao;
    }

    private void votar() {
        Scanner scanner = new Scanner(System.in);
        exibirUrna();
        System.out.print("Digite o código do candidato: ");
        try {
            int codigo = scanner.nextInt();

            boolean votou = eleicaoController.votar(codigo);

            if (votou) {
                System.out.println("Voto registrado com sucesso!");
            } else {
                System.out.println("Candidato não encontrado. Voto não registrado.");
            }
        }catch (InputMismatchException ex){
            System.out.println("Digite um valor válido!");
        }
    }

    private void exibirCandidatoMaisJovem() {
        Candidato candidatoMaisJovem = eleicaoController.getCandidatoMaisNovo();
        System.out.printf("Candidato mais jovem: %s(%s)\nIdade: %d\n", candidatoMaisJovem.getNome(), candidatoMaisJovem.getPartido().getNome(), candidatoMaisJovem.getIdade());
    }

    private void exibirCandidatoMaisVelho() {
        Candidato candidatoMaisVelho = eleicaoController.getCandidatoMaisVelho();
        System.out.printf("Candidato mais velho: %s(%s)\nIdade: %d\n", candidatoMaisVelho.getNome(), candidatoMaisVelho.getPartido().getNome(), candidatoMaisVelho.getIdade());
    }

    private void exibirCandidatoMaisVotado() {
        Candidato candidatoMaisVotado = eleicaoController.getMaisVotado();
        System.out.printf("Candidato mais votado: %s(%s)\nVotos: %d\n", candidatoMaisVotado.getNome(), candidatoMaisVotado.getPartido().getNome(), candidatoMaisVotado.getNumVotos());
    }

    private void exibirCandidatoMenosVotado() {
        Candidato candidatoMenosVotado = eleicaoController.getMenosVotado();
        System.out.printf("Candidato menos votado: %s(%s)\nVotos: %d\n", candidatoMenosVotado.getNome(), candidatoMenosVotado.getPartido().getNome(), candidatoMenosVotado.getNumVotos());
    }
    private void exibirTotalVotos() {
        int totalVotos = eleicaoController.getTotalVotos();
        System.out.println("Total de votos: " + totalVotos);
    }

    private void exibirMediaVotacao() {
        double mediaVotacao = eleicaoController.getMediaVotos();
        System.out.println("Média de votação: " + mediaVotacao);
    }
}
