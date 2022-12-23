package br.com.gft.gftmilhas.services;

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
import br.com.gft.gftmilhas.entities.PresencaParticipante;
import br.com.gft.gftmilhas.entities.PresencaParticipanteID;
import br.com.gft.gftmilhas.repositories.ParticipanteRepository;

@Service
public class ParticipanteService {
    
    @Autowired
    private ParticipanteRepository participanteRepository;

    @Autowired
    private AtividadeParticipanteService atividadeParticipanteService;

    @Autowired 
    private PresencaParticipanteService presencaService;
    
    public List<Participante> listar(){
        return participanteRepository.findAll();
    }

    public Participante buscarPorId(Long id) throws Exception{
        Optional<Participante> optParticipante = participanteRepository.findById(id);

        if(optParticipante.isEmpty()){
            throw new Exception("Não foi encontrado participante com id: " + id);
        }

        return optParticipante.get();
    }

    public void cadastrar(Participante participante) throws Exception{
        List<Participante> participantes = participanteRepository.findByQuatroLetras(participante.getQuatroLetras());
        GrupoParticipante grupo = participante.getGrupo(); 
        Evento evento = grupo.getEvento();
        if(participantes != null){
            boolean jaExisteEventoComOParticipante = false;
            for(Participante p : participantes){
                jaExisteEventoComOParticipante =
                        p.getGrupo().getEvento().getId() == evento.getId();

                if(jaExisteEventoComOParticipante){
                    throw new Exception("Esse participante já está cadastrado nesse evento.");
                }
            }
        }
        
        participanteRepository.save(participante);

        List<Atividade> atividades = evento.getAtividades();
        atividades.forEach(a -> atividadeParticipanteService.cadastrar(new AtividadeParticipante(new AtividadeParticipanteID(a, participante))));

        int dias = evento.getDiasEvento();
        for(int i = 1; i <= dias; i++){
            presencaService.cadastrar(new PresencaParticipante(new PresencaParticipanteID(participante, evento, i)));
        }
    }

    @Transactional
    public void editar(Participante participante) throws Exception{
        Participante participanteBuscado = buscarPorId(participante.getId());

        participanteBuscado.setNome(participante.getNome());
        participanteBuscado.setUrlFoto(participante.getUrlFoto());
        participanteBuscado.setNivel(participante.getNivel());
        participanteRepository.save(participanteBuscado);
    }

    public void deletar(Long id){
        participanteRepository.deleteById(id);
    }
}
