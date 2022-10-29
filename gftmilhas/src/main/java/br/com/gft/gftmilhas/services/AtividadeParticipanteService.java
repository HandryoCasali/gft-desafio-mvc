package br.com.gft.gftmilhas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gft.gftmilhas.entities.AtividadeParticipante;
import br.com.gft.gftmilhas.repositories.AtividadeParticipanteRepository;

@Service
public class AtividadeParticipanteService {

    @Autowired
    private AtividadeParticipanteRepository atividadeParticipanteRepository;

    public void cadastrar(AtividadeParticipante atividadeParticipante){
        atividadeParticipanteRepository.save(atividadeParticipante);
    }

    public void cadastrarTodos(List<AtividadeParticipante> atividadesParticipantes){
        atividadeParticipanteRepository.saveAll(atividadesParticipantes);
    }
}
