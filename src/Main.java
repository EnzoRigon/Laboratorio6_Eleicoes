import controller.EleicaoController;
import model.Candidato;
import model.Eleicao;
import model.Partido;
import view.UrnaEletronica;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Candidato> candidatos = new ArrayList<>();

        Partido partido1 = new Partido("PCdoB", 51);
        Partido partido2 = new Partido("PT", 13);
        Partido partido3 = new Partido("PL", 22);

        Candidato candidato1 = new Candidato("João",  65, partido1, 3500);
        Candidato candidato2 = new Candidato("Maria",  43, partido2, 534);
        Candidato candidato3 = new Candidato("Pedro", 82, partido3,54);

        candidatos.add(candidato1);
        candidatos.add(candidato2);
        candidatos.add(candidato3);

        Eleicao eleicao = new Eleicao(candidatos);

        EleicaoController eleicaoController = new EleicaoController(eleicao);

        UrnaEletronica urna = new UrnaEletronica(eleicaoController);
        urna.iniciar();
    }
}