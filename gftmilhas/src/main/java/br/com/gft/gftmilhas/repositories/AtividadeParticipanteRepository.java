package br.com.gft.gftmilhas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gft.gftmilhas.entities.AtividadeParticipante;
import br.com.gft.gftmilhas.entities.AtividadeParticipanteID;

@Repository
public interface AtividadeParticipanteRepository extends JpaRepository<AtividadeParticipante, AtividadeParticipanteID> {

}
