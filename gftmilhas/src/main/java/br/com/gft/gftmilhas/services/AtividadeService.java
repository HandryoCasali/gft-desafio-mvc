package br.com.gft.gftmilhas.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gft.gftmilhas.entities.Atividade;
import br.com.gft.gftmilhas.entities.AtividadeParticipante;
import br.com.gft.gftmilhas.entities.AtividadeParticipanteID;
import br.com.gft.gftmilhas.entities.Evento;
import br.com.gft.gftmilhas.entities.GrupoParticipante;
import br.com.gft.gftmilhas.entities.Participante;
import br.com.gft.gftmilhas.repositories.AtividadeRepository;

@Service
@Transactional
public class AtividadeService {
    @Autowired
    private AtividadeRepository atividadeRepository;

    @Autowired
    private AtividadeParticipanteService atividadeParticipanteService;

    public List<Atividade> listar(){
        return atividadeRepository.findAll();
    }

    public Atividade buscarPorId(Long id) throws Exception{
        Optional<Atividade> optParticipante = atividadeRepository.findById(id);

        if(optParticipante.isEmpty()){
            throw new Exception("Não foi encontrado atividade com id: " + id);
        }

        return optParticipante.get();
    }

    public void cadastrar(Atividade atividade){
        Atividade atividadeSalva = atividadeRepository.save(atividade);

        Evento evento = atividadeSalva.getEvento();
        List<GrupoParticipante> grupos = evento.getGrupos();
        List<Participante> participantes = new ArrayList<>();
        grupos.forEach(g -> participantes.addAll(g.getParticipantes()));
        
        participantes.forEach(p -> 
            atividadeParticipanteService
                        .cadastrar(new AtividadeParticipante(new AtividadeParticipanteID(atividadeSalva, p))));
    }

    public void deletar(Long id){
        atividadeRepository.deleteById(id);
    }
}
