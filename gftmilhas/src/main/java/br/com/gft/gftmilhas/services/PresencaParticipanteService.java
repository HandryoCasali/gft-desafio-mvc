package br.com.gft.gftmilhas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gft.gftmilhas.entities.Evento;
import br.com.gft.gftmilhas.entities.PresencaParticipante;
import br.com.gft.gftmilhas.repositories.PresencaParticipanteRepository;

@Service
public class PresencaParticipanteService {
    @Autowired
    private PresencaParticipanteRepository presencaRepository;

    public void cadastrar(PresencaParticipante presencaParticipante){
        presencaRepository.save(presencaParticipante);
    }

    public List<PresencaParticipante> listarPorDia(Evento evento, Integer dia){
        return presencaRepository.findPorEventoEDia(evento, dia); 
    }
     
    public void deletaPorDia(Evento evento, Integer dia){
        presencaRepository.deletePorEventoEDia(evento, dia);
    }

    public void cadastrarTodos(List<PresencaParticipante> presencas) {
        presencaRepository.saveAll(presencas);
    }
}
