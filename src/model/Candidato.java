package model;

public class Candidato {
    private String nome;
    private int idade;
    private Partido partido;
    private int numVotos;

    public Candidato(String nome, int idade, Partido partido, int numVotos) {
        this.nome = nome;
        this.idade = idade;
        this.partido = partido;
        this.numVotos = numVotos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    public int getNumVotos() {
        return numVotos;
    }

    public void setNumVotos(int numVotos) {
        this.numVotos = numVotos;
    }

    public void adicionarVoto(){
        numVotos++;
    }
}
