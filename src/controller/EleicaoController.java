package controller;

import model.Candidato;
import model.Eleicao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;

public class EleicaoController {
    private Eleicao eleicao;
    private ArrayList<Candidato> candidatos;

    public EleicaoController(Eleicao eleicao) {
        this.eleicao = eleicao;
        this.candidatos = eleicao.getCandidatos();
    }

    public boolean votar(int codigo) {
        Optional<Candidato> candidato = candidatos.stream()
                .filter(c -> c.getPartido().getCodigo() == codigo)
                .findFirst();

        return candidato.map(c -> {
            c.adicionarVoto();
            return true;
        }).orElse(false);
    }

    public Candidato getCandidatoMaisNovo() {
        return Collections.min(candidatos, Comparator.comparingInt(Candidato::getIdade));
    }

    public Candidato getCandidatoMaisVelho() {
        return Collections.max(candidatos, Comparator.comparingInt(Candidato::getIdade));
    }

    public Candidato getMaisVotado() {
        return Collections.max(candidatos, Comparator.comparingInt(Candidato::getNumVotos));
    }

    public Candidato getMenosVotado() {
        return Collections.min(candidatos, Comparator.comparingInt(Candidato::getNumVotos));
    }

    public int getTotalVotos() {
        return candidatos.stream().mapToInt(Candidato::getNumVotos).sum();
    }

    public double getMediaVotos() {
        int totalCandidatos = candidatos.size();
        int totalVotos = getTotalVotos();
        if (totalCandidatos > 0) {
            return (double) totalVotos / totalCandidatos;
        }
        return 0.0;
    }


    public Eleicao getEleicao() {
        return eleicao;
    }

    public void setEleicao(Eleicao eleicao) {
        this.eleicao = eleicao;
    }

    public ArrayList<Candidato> getCandidatos() {
        return candidatos;
    }

    public void setCandidatos(ArrayList<Candidato> candidatos) {
        this.candidatos = candidatos;
    }
}
