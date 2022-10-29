package br.com.gft.gftmilhas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.gft.gftmilhas.entities.Evento;
import br.com.gft.gftmilhas.entities.GrupoParticipante;
import br.com.gft.gftmilhas.entities.Participante;
import br.com.gft.gftmilhas.services.EventoService;
import br.com.gft.gftmilhas.services.GrupoParticipanteService;

@Controller
@RequestMapping("grupo")
public class GrupoParticipanteController {

    @Autowired
    private GrupoParticipanteService grupoParticipanteService;

    @Autowired
    private EventoService eventoService;

    @GetMapping("buscar")
    public ModelAndView detalhesGrupo(@RequestParam Long id) {
        ModelAndView mv = new ModelAndView("grupo/detalhes.html");
        GrupoParticipante grupo = null;
        
        //TODO: gambiarra feia setando pontuacao na hora de buscar por id
        try {
            grupo = grupoParticipanteService.buscarPorId(id);
            grupo.setPontuacao(grupoParticipanteService.calcularPontuacao(grupo));
            grupoParticipanteService.cadastrar(grupo);
        } catch (Exception e) {
            mv.addObject("erro", e.getMessage());
        }
        
        mv.addObject("grupo", grupo);
        mv.addObject("participante", new Participante());
      
        return mv;
    }

    @GetMapping("cadastrar")
    public ModelAndView getForm(@RequestParam("id") Long idEvento){
        ModelAndView mv = new ModelAndView("grupo/form.html");
        
        boolean existeEvento = eventoService.existe(idEvento);
        if(!existeEvento){
            mv.addObject("erroBuscar", "Não foi encontrado evento com id: " + idEvento);
            return mv;
        }
        
        mv.addObject("idEvento", idEvento);
        mv.addObject("grupoParticipante", new GrupoParticipante());
        return mv;
    }

    @PostMapping("cadastrar")
    public ModelAndView cadastrar(Long idEvento, GrupoParticipante grupo, RedirectAttributes redirAtt) {
        ModelAndView mv = new ModelAndView("redirect:/evento/buscar?id=" + idEvento);
        
        try {
            Evento evento = eventoService.buscarPorId(idEvento);
            grupo.setEvento(evento);
            grupoParticipanteService.cadastrar(grupo);
            redirAtt.addFlashAttribute("mensagem", "Grupo cadastrado com sucesso!");
        } catch (Exception e) {
            redirAtt.addFlashAttribute("erro", e.getMessage());
            return new ModelAndView("redirect:/grupo/cadastrar?id=" + idEvento);
        }
        return mv;
    }

    @GetMapping("editar")
    public ModelAndView editar(@RequestParam Long id){
        ModelAndView mv = new ModelAndView("grupo/form.html");
        try {
            GrupoParticipante grupo = grupoParticipanteService.buscarPorId(id);
            Long idEvento = grupo.getEvento().getId();
            mv.addObject("idEvento", idEvento);
            mv.addObject("grupoParticipante", grupo);
        } catch (Exception e) {
            mv.addObject("grupoParticipante", new GrupoParticipante());
        }

        return mv;
    }

    @GetMapping("deletar")
    public String deletar(@RequestParam Long id, RedirectAttributes redirAtt) {
        long idEvento = 0;
        try {
            idEvento = grupoParticipanteService.buscarPorId(id).getEvento().getId();
            grupoParticipanteService.deletar(id);
            redirAtt.addFlashAttribute("mensagem", "Grupo excluído com sucesso!");
        } catch (Exception e) {
            redirAtt.addFlashAttribute("erro", e.getMessage());
        }
        
        return "redirect:/evento/buscar?id=" + idEvento;
    }
    
   
}
