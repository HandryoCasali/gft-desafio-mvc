package br.com.gft.gftmilhas.controllers;

import java.util.ArrayList;

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
import br.com.gft.gftmilhas.services.EventoService;

@Controller
@RequestMapping("evento")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @GetMapping
    public ModelAndView getEventos() {
        ModelAndView mv = new ModelAndView("evento/listaEventos.html");
        mv.addObject("eventos", eventoService.listar());
        return mv;
    }

    @GetMapping("cadastrar")
    public ModelAndView formulario() {
        ModelAndView mv = new ModelAndView("evento/form.html");
        mv.addObject("evento", new Evento());
        return mv;
    }

    @PostMapping("cadastrar")
    public ModelAndView cadastrar(Evento evento, RedirectAttributes redirAtt) {
        ModelAndView mv = new ModelAndView("redirect:/evento");

        if (evento.getGrupos() == null) {
            evento.setGrupos(new ArrayList<>());
        }

        eventoService.cadastrar(evento);

        redirAtt.addFlashAttribute("mensagem", "Evento cadastrado com sucesso!");
        return mv;
    }


    @GetMapping("buscar")
    public ModelAndView descricao(@RequestParam("id") Long idEvento) {
        ModelAndView mv = new ModelAndView("evento/detalhes.html");
        Evento evento = null;
        try {
            evento = eventoService.buscarPorId(idEvento);
        } catch (Exception e) {
            mv.addObject("erroBusca", e.getMessage());
        }

        mv.addObject("evento", evento);
        mv.addObject("grupoParticipante", new GrupoParticipante());
        return mv;
    }

    @GetMapping("deletar")
    public ModelAndView deletar(@RequestParam Long id, RedirectAttributes redirAtt){
        ModelAndView mv = new ModelAndView("redirect:/evento");
        try {
            eventoService.deletar(id);
            redirAtt.addFlashAttribute("mensagem", "Evento deletado com sucesso!");
        } catch (Exception e) {
            redirAtt.addFlashAttribute("erroDeletar", e.getMessage());
        }
        return mv;
    }

}
