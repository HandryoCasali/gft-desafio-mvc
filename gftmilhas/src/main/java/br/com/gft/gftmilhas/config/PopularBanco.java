package br.com.gft.gftmilhas.config;

import java.text.SimpleDateFormat;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.com.gft.gftmilhas.entities.Atividade;
import br.com.gft.gftmilhas.entities.Evento;
import br.com.gft.gftmilhas.entities.GrupoParticipante;
import br.com.gft.gftmilhas.entities.Participante;
import br.com.gft.gftmilhas.entities.Role;
import br.com.gft.gftmilhas.entities.Usuario;
import br.com.gft.gftmilhas.enums.RoleName;
import br.com.gft.gftmilhas.repositories.RoleRepository;
import br.com.gft.gftmilhas.repositories.UsuarioRepository;
import br.com.gft.gftmilhas.services.AtividadeService;
import br.com.gft.gftmilhas.services.EventoService;
import br.com.gft.gftmilhas.services.GrupoParticipanteService;
import br.com.gft.gftmilhas.services.ParticipanteService;

@Component
@Order(1)
public class PopularBanco implements ApplicationRunner{

    @Autowired private EventoService eventoService;
    @Autowired private RoleRepository roleRepository;
    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private AtividadeService atividadeService;
    @Autowired private GrupoParticipanteService grupoService;
    @Autowired private ParticipanteService participanteService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        SimpleDateFormat sdf1= new SimpleDateFormat("dd/MM/yyyy");

        Role roleAdmin = new Role();
        roleAdmin.setRoleName(RoleName.ROLE_ADMIN);
        roleAdmin = roleRepository.save(roleAdmin);

        Role roleUser = new Role();
        roleUser.setRoleName(RoleName.ROLE_USER);
        roleUser = roleRepository.save(roleUser);

        Usuario usuarioAdmin = new Usuario();
        usuarioAdmin.setNome("Admin");
        usuarioAdmin.setEmail("admin@gft.com");
        usuarioAdmin.setQuatroLetras("user");
        usuarioAdmin.setSenha("$2a$10$98P8lNdVUsgaqsqo1S.0IeGJ7RqMpFlXMb8yGkSl.DVPfX935uX3i");
        usuarioAdmin.getRoles().add(roleAdmin);
        usuarioRepository.save(usuarioAdmin);


        Usuario usuarioUser = new Usuario();
        usuarioUser.setNome("Handryo Casali");
        usuarioUser.setEmail("handryocasalis2@gmail.com");
        usuarioUser.setQuatroLetras("hoci");
        usuarioUser.setSenha("$2a$10$lggJvQhRZDyNwqkfmvjQUuoAVDnj6Z0fvZjMIcR4U5wOJr9yCaBi2");
        usuarioUser.getRoles().add(roleUser);
        usuarioRepository.save(usuarioUser);

        Evento evento = new Evento();
        evento.setNome("MasterClass HumanSkills");
        evento.setDescricao("Mussum Ipsum, cacilds vidis litro abertis. Per aumento de cachacis, eu reclamis.Interessantiss quisso pudia ce receita de bolis, mais bolis eu num gostis.Copo furadis é disculpa de bebadis, arcu quam euismod magna.Mé faiz elementum girarzis.");
        evento.setDataInicio(sdf1.parse("25/10/2022"));
        evento.setDataFinal(sdf1.parse("31/10/2022"));
        evento.setUrlFoto("");
        evento = eventoService.cadastrar(evento);
       
        Atividade atividade1 = new Atividade();
        atividade1.setNome("Atividade 1 - AutoConhecimento");
        atividade1.setDescricao("cacilds vidis litro abertis. Per aumento de cachacis, eu reclamis.Interessantiss quisso pudia ce receita de bolis, mais bolis eu num gostis.Copo furadis é disculpa de bebadis");
        atividade1.setDataInicio(sdf1.parse("25/10/2022"));
        atividade1.setDataFinal(sdf1.parse("26/10/2022"));
        atividade1.setEvento(evento);
        atividadeService.cadastrar(atividade1);

        Atividade atividade2 = new Atividade();
        atividade2.setNome("Atividade 2 - O que são Human Skills");
        atividade2.setDescricao("cacilds vidis litro abertis. Per aumento de cachacis, eu reclamis.Interessantiss quisso pudia ce receita de bolis, mais bolis eu num gostis.Copo furadis é disculpa de bebadis");
        atividade2.setDataInicio(sdf1.parse("26/10/2022"));
        atividade2.setDataFinal(sdf1.parse("27/10/2022"));
        atividade2.setEvento(evento);
        atividadeService.cadastrar(atividade2);

        evento = eventoService.buscarPorId(1L);

        GrupoParticipante grupo1 = new GrupoParticipante();
        grupo1.setNome("Grupo Verde");
        grupo1.setEvento(evento);
        grupo1.setUrlFoto("");
        grupo1 = grupoService.cadastrar(grupo1);
        
        GrupoParticipante grupo2 = new GrupoParticipante();
        grupo2.setNome("Grupo Azul");
        grupo2.setUrlFoto("");
        grupo2.setEvento(evento);
        grupo2 = grupoService.cadastrar(grupo2);

       

        Participante p1 = new Participante(
            "João Silva",
            "josi@gft.com",
            "josi",
            "L4",
            grupo1,
            "");

        Participante p2 = new Participante(
            "Handryo Casali",
            "hoci@gft.com",
            "hoci",
            "L0",
            grupo1,
            "");

        Participante p3 = new Participante(
            "Cecília Pereira",
            "ceci@gft.com",
            "ceci",
            "L2",
            grupo1,
            "");
    
        Participante p4 = new Participante(
            "Esther Pires",
            "erps@gft.com",
            "erps",
            "L3",
            grupo2,
            "");
        
        Participante p5 = new Participante(
            "Murilo Souza",
            "mosa@gft.com",
            "mosa",
            "L1",
            grupo2,
            "");

        Participante p6 = new Participante(
            "Maria Julia Campos",
            "macs@gft.com",
            "macs",
            "L3",
            grupo2,
            "");

        participanteService.cadastrar(p1);
        participanteService.cadastrar(p2);
        participanteService.cadastrar(p3);
        participanteService.cadastrar(p4);
        participanteService.cadastrar(p5);
        participanteService.cadastrar(p6);
    }
    
}
