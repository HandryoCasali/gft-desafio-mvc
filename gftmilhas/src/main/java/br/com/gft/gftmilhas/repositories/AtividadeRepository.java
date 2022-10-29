package br.com.gft.gftmilhas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gft.gftmilhas.entities.Atividade;

@Repository
public interface AtividadeRepository extends JpaRepository<Atividade, Long>{
    
}
