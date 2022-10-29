package br.com.gft.gftmilhas.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.gft.gftmilhas.entities.Role;
import br.com.gft.gftmilhas.entities.Usuario;
import br.com.gft.gftmilhas.enums.RoleName;
import br.com.gft.gftmilhas.repositories.RoleRepository;
import br.com.gft.gftmilhas.repositories.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository ur;

    @Autowired
    private RoleRepository rr;

    public void cadastrar(Usuario usuario) throws Exception{
        boolean existeUsuarioCadastrado = ur.existsByQuatroLetras(usuario.getQuatroLetras());

        if(existeUsuarioCadastrado){
            throw new Exception("Já existe usuário cadastrado com as quatro letras: " + usuario.getQuatroLetras());
        }        

        Role role = rr.findByRoleName(RoleName.ROLE_USER)
                        .orElseThrow(() -> new Exception("Não foi possível cadastrar o usuário com a role de user."));    

                        
        usuario.getRoles().add(role);
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        ur.save(usuario);
    }

    public void cadastrarAdm(Usuario usuario) throws Exception{
        boolean existeUsuarioCadastrado = ur.existsByQuatroLetras(usuario.getQuatroLetras());

        if(existeUsuarioCadastrado){
            throw new Exception("Já existe usuário cadastrado com as quatro letras: " + usuario.getQuatroLetras());
        }        

        Role role = rr.findByRoleName(RoleName.ROLE_ADMIN)
                        .orElseThrow(() -> new Exception("Não foi possível cadastrar o usuário com a role de user."));    

                        
        usuario.getRoles().add(role);
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        ur.save(usuario);
    }
}
