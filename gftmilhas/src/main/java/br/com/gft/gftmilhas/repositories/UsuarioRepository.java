package br.com.gft.gftmilhas.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gft.gftmilhas.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByQuatroLetras(String quatroLetras);
    boolean existsByQuatroLetras(String quatroLetras);
}
