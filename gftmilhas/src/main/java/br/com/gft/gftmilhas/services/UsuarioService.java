package br.com.gft.gftmilhas.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.gft.gftmilhas.email.EmailModel;
import br.com.gft.gftmilhas.email.EmailService;
import br.com.gft.gftmilhas.entities.Role;
import br.com.gft.gftmilhas.entities.Usuario;
import br.com.gft.gftmilhas.enums.RoleName;
import br.com.gft.gftmilhas.exception.UsuarioNotFoundException;
import br.com.gft.gftmilhas.repositories.RoleRepository;
import br.com.gft.gftmilhas.repositories.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository ur;

    @Autowired
    private RoleRepository rr;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private EmailService emailService;

    @Value("${spring.mail.username}")
    private String emailFrom;

    @Value("${recuperar_senha.url}")
    private String urlRecuperarSenha;

    @Value("${recuperar_senha.mensagem}")
    private String mensagemRecuperarSenha;

    public void cadastrar(Usuario usuario) throws Exception {
        boolean existeUsuarioCadastrado = ur.existsByQuatroLetras(usuario.getQuatroLetras());

        if (existeUsuarioCadastrado) {
            throw new Exception("Já existe usuário cadastrado com as quatro letras: " + usuario.getQuatroLetras());
        }

        Role role = rr.findByRoleName(RoleName.ROLE_USER)
                .orElseThrow(() -> new Exception("Não foi possível cadastrar o usuário com a role de user."));

        usuario.getRoles().add(role);
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        ur.save(usuario);
    }

    public void cadastrarAdm(Usuario usuario) throws Exception {
        boolean existeUsuarioCadastrado = ur.existsByQuatroLetras(usuario.getQuatroLetras());

        if (existeUsuarioCadastrado) {
            throw new Exception("Já existe usuário cadastrado com as quatro letras: " + usuario.getQuatroLetras());
        }

        Role role = rr.findByRoleName(RoleName.ROLE_ADMIN)
                .orElseThrow(() -> new Exception("Não foi possível cadastrar o usuário com a role de user."));

        usuario.getRoles().add(role);
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        ur.save(usuario);
    }

    public Usuario buscarUsuarioPelasQuatroLetras(String quatroLetras) throws UsuarioNotFoundException {
        return ur.findByQuatroLetras(quatroLetras)
                .orElseThrow(() -> new UsuarioNotFoundException("Usuario não cadastrado no sistema"));
    }

    public Usuario buscarPorId(Long id) throws UsuarioNotFoundException {
        return ur.findById(id).orElseThrow(() -> new UsuarioNotFoundException("Usuario não encontrado."));
    }

    public void recuperarSenha(String username) throws UsuarioNotFoundException {
        Usuario usuario = buscarUsuarioPelasQuatroLetras(username);

        String token = tokenService.gerarToken(usuario);

        String mensagem = "<h1>Troca de Senha</h1><br>" +
                "<p>"+ mensagemRecuperarSenha +"</p><br>" +
                "<a href=\"" + urlRecuperarSenha + token + "\">Clique Aqui</a>";

        System.out.println(mensagem);
        EmailModel email = new EmailModel(emailFrom, usuario.getEmail(), usuario.getNome(), mensagem);
        emailService.enviarEmail(email);
    }

    @Transactional
    public void alterarSenha(Long id, String novaSenha) throws UsuarioNotFoundException {


        Usuario usuario = buscarPorId(id);
        usuario.setSenha(new BCryptPasswordEncoder().encode(novaSenha));
        ur.save(usuario);
    }

    public boolean comparaSenha(String password, String passwordcompare) {
        return password.equals(passwordcompare);
    }

}
