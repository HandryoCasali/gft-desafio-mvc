package br.com.gft.gftmilhas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.gft.gftmilhas.entities.Evento;
import br.com.gft.gftmilhas.services.EventoService;
import br.com.gft.gftmilhas.services.GrupoParticipanteService;

@Controller
@RequestMapping
public class RankingController {
    @Autowired
    private EventoService eventoService;

    @Autowired
    private GrupoParticipanteService grupoService;

    @GetMapping("/")
    public String home(){
        return "redirect:/ranking";
    }

    @GetMapping("ranking")
    public ModelAndView getEventos() {
        ModelAndView mv = new ModelAndView("ranking/eventos.html");
        mv.addObject("eventos", eventoService.listar());
        return mv;
    }

    @GetMapping("ranking/evento")
    public ModelAndView detalhesEvento(@RequestParam long id) {
        ModelAndView mv = new ModelAndView("ranking/ranking.html");

        try {
            Evento evento = eventoService.buscarPorId(id);
            mv.addObject("evento", evento);
            evento.getGrupos().forEach(g -> {
                g.setPontuacao(grupoService.calcularPontuacao(g));
                try {
                    grupoService.cadastrar(g);
                } catch (Exception e) {
                    mv.addObject("mensagem", e.getMessage());
                }
            });

            mv.addObject("listaGruposRank", grupoService.listarPorPontos(evento));
        } catch (Exception e) {
            mv.addObject("erroBuscar", e.getMessage());
        }
        
        return mv;
    }
}
