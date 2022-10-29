package br.com.gft.gftmilhas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gft.gftmilhas.entities.Evento;
import br.com.gft.gftmilhas.entities.GrupoParticipante;

@Repository
public interface GrupoParticipanteRepository extends JpaRepository<GrupoParticipante, Long> {
    List<GrupoParticipante> findByNome(String nome);

    List<GrupoParticipante> findByEventoOrderByPontuacaoDesc(Evento evento);
}
