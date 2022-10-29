package br.com.gft.gftmilhas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gft.gftmilhas.entities.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long>{
}
