package controller;

import model.Candidato;
import model.Eleicao;
import model.Partido;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class EleicaoControllerTest {

    private Eleicao eleicao;
    private EleicaoController eleicaoController;
    private Candidato candidato1;
    private Candidato candidato2;
    private Candidato candidato3;

    @org.junit.Before
    public void setUp() throws Exception {
        ArrayList<Candidato> candidatos = new ArrayList<>();

        Partido partido1 = new Partido("PCdoB", 51);
        Partido partido2 = new Partido("PT", 13);
        Partido partido3 = new Partido("PL", 22);

        candidato1 = new Candidato("João",  65, partido1, 3500);
        candidato2 = new Candidato("Maria",  43, partido2, 534);
        candidato3 = new Candidato("Pedro", 82, partido3,54);

        candidatos.add(candidato1);
        candidatos.add(candidato2);
        candidatos.add(candidato3);

        eleicao = new Eleicao(candidatos);

        eleicaoController = new EleicaoController(eleicao);
    }

    @Test
    public void testVotar_CandidatoExistente() {
        boolean votoRegistrado = eleicaoController.votar(51);
        assertTrue(votoRegistrado);
    }

    @Test
    public void testVotar_CandidatoInexistente() {
         boolean votoRegistrado = eleicaoController.votar(1234);
         assertFalse(votoRegistrado);
    }

    @Test
    public void testGetCandidatoMaisNovo() {
        assertEquals(candidato2, eleicaoController.getCandidatoMaisNovo());
    }

    @Test
    public void testGetCandidatoMaisVelho() {
        assertEquals(candidato3, eleicaoController.getCandidatoMaisVelho());
    }

    @Test
    public void testGetMaisVotado() {
        assertEquals(candidato1, eleicaoController.getMaisVotado());
    }

    @Test
    public void testGetMenosVotado() {
        assertEquals(candidato3, eleicaoController.getMenosVotado());
    }

    @Test
    public void testGetTotalVotos() {
        assertEquals(4088, eleicaoController.getTotalVotos());
    }

    @Test
    public void testGetMediaVotos() {
        assertEquals(1363, eleicaoController.getMediaVotos(), 1);
    }
}