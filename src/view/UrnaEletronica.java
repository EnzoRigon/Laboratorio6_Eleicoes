package view;

import controller.EleicaoController;
import model.Candidato;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UrnaEletronica {
    private EleicaoController eleicaoController;

    public UrnaEletronica(EleicaoController eleicaoController) {
        this.eleicaoController = eleicaoController;
    }


    public void exibirMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            exibirMenuPrincipal();
            System.out.print("Digite a opção desejada: ");

            try {
                opcao = scanner.nextInt();
                System.out.println();

                switch (opcao) {
                    case 1:
                        votar();
                        break;
                    case 2:
                        exibirCandidatoMaisJovem();
                        break;
                    case 3:
                        exibirCandidatoMaisVelho();
                        break;
                    case 4:
                        exibirCandidatoMaisVotado();
                        break;
                    case 5:
                        exibirCandidatoMenosVotado();
                        break;
                    case 6:
                        exibirTotalVotos();
                        break;
                    case 7:
                        exibirMediaVotacao();
                        break;
                    case 0:
                        System.out.println("Encerrando a urna eletrônica...");
                        break;
                    default:
                        System.out.println("Opção inválida! Por favor, tente novamente.");
                        break;
                }

                System.out.println();
            } catch (InputMismatchException ex) {
                System.out.println("Digite um valor válido!");
                scanner.next();
                opcao = -1;
            }
        } while (opcao != 0);
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
