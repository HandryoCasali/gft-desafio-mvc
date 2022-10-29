package br.com.gft.gftmilhas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.gft.gftmilhas.entities.Evento;
import br.com.gft.gftmilhas.entities.PresencaParticipante;
import br.com.gft.gftmilhas.entities.PresencaParticipanteID;

@Repository
public interface PresencaParticipanteRepository extends JpaRepository<PresencaParticipante, PresencaParticipanteID> {
    @Query("select p from PresencaParticipante p where p.id.evento = :evento and p.id.diaEvento = :dia")
    List<PresencaParticipante> findPorEventoEDia(@Param("evento") Evento evento, @Param("dia") Integer dia);

    @Query("delete from PresencaParticipante p where p.id.evento = :evento and p.id.diaEvento = :dia")
    void deletePorEventoEDia(@Param("evento") Evento evento, @Param("dia") Integer dia);
}   
