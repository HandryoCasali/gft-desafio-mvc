package br.com.gft.gftmilhas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.gft.gftmilhas.entities.GrupoParticipante;
import br.com.gft.gftmilhas.entities.Participante;
import br.com.gft.gftmilhas.services.GrupoParticipanteService;
import br.com.gft.gftmilhas.services.ParticipanteService;

@Controller
@RequestMapping("participante")
public class ParticipanteController {

    @Autowired
    private ParticipanteService participanteService;

    @Autowired
    private GrupoParticipanteService grupoParticipanteService;

    @GetMapping("cadastrar")
    public ModelAndView getForm(@RequestParam("id") Long idGrupo) {
        ModelAndView mv = new ModelAndView("participante/form.html");

        boolean existeGrupo = grupoParticipanteService.existe(idGrupo);
        if (!existeGrupo) {
            mv.addObject("erroBuscar", "Não foi encontrado evento com id: " + idGrupo);
            return mv;
        }

        mv.addObject("idGrupo", idGrupo);
        mv.addObject("participante", new Participante());
        return mv;
    }

    @PostMapping("cadastrar")
    public ModelAndView cadastrar(Long idGrupo, Participante participante, RedirectAttributes redirAtt) {
        ModelAndView mv = new ModelAndView("redirect:/grupo/buscar?id=" + idGrupo);
        participante.setNivel(participante.getNivel().toUpperCase());
        try {
            GrupoParticipante grupo = grupoParticipanteService.buscarPorId(idGrupo); 
            participante.setGrupo(grupo);
            participanteService.cadastrar(participante);
        
            redirAtt.addFlashAttribute("mensagem", "Participante adicionado com sucesso!");
        } catch (Exception e) {
            redirAtt.addFlashAttribute("erro", e.getMessage());
            return new ModelAndView("redirect:/participante/cadastrar?id=" + idGrupo);
        }
        
        return mv;
    }

    @GetMapping("editar")
    public ModelAndView editar(@RequestParam Long id) {
        ModelAndView mv = new ModelAndView("participante/form.html");
        try {
            Participante participante = participanteService.buscarPorId(id);
            Long idGrupo = participante.getGrupo().getId();
            mv.addObject("idGrupo", idGrupo);
            mv.addObject("participante", participante);
        } catch (Exception e) {
            mv.addObject("participante", new Participante());
        }

        return mv;
    }

    @GetMapping("deletar")
    public String deletar(@RequestParam Long id, RedirectAttributes redirAtt) {
        long idGrupo = 0;
        try {
            idGrupo = participanteService.buscarPorId(id).getGrupo().getId();
            participanteService.deletar(id);
            redirAtt.addFlashAttribute("mensagem", "Participante excluído com sucesso!");
        } catch (Exception e) {
            redirAtt.addFlashAttribute("erro", e.getMessage());
        }

        return "redirect:/grupo/buscar?id=" + idGrupo;
    }
}
