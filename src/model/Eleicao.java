package model;

import java.util.ArrayList;

public class Eleicao {
    private ArrayList<Candidato> candidatos;

    public Eleicao(ArrayList<Candidato> candidatos) {
        this.candidatos = candidatos;
    }

    public ArrayList<Candidato> getCandidatos() {
        return candidatos;
    }

    public void setCandidatos(ArrayList<Candidato> candidatos) {
        this.candidatos = candidatos;
    }
}
