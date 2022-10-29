package br.com.gft.gftmilhas.controllers;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.gft.gftmilhas.dto.FormPresencaParticipanteDTO;
import br.com.gft.gftmilhas.entities.Evento;
import br.com.gft.gftmilhas.enums.Presenca;
import br.com.gft.gftmilhas.services.EventoService;
import br.com.gft.gftmilhas.services.PresencaParticipanteService;

@Controller
@RequestMapping("presenca")
public class PresencaParticipanteController {
    
    @Autowired
    private EventoService eventoService;

    @Autowired
    private PresencaParticipanteService presencaService;

    @GetMapping("listar")
    public ModelAndView listar(@RequestParam long idEvento, @RequestParam int dia , RedirectAttributes redirAtt){
        ModelAndView mv = new ModelAndView("presenca/listarPresencas.html");
        try {
            Evento evento = eventoService.buscarPorId(idEvento);

            List<Integer> diasDoEvento = new ArrayList<>();
            for(int d = 1; d <= evento.getDiasEvento(); d++ ){
                diasDoEvento.add(d);
            }
            
            FormPresencaParticipanteDTO formPresencaDTO = new FormPresencaParticipanteDTO(presencaService.listarPorDia(evento, dia));
            formPresencaDTO.getPresencas();

            mv.addObject("evento", evento);
            mv.addObject("formPresencaDTO", formPresencaDTO);           
            mv.addObject("diasDoEvento", diasDoEvento);
            mv.addObject("diaAtual", dia);
            mv.addObject("statusPresenca", Presenca.values());
        } catch (Exception e) {
            redirAtt.addFlashAttribute("erroBuscar", e.getMessage());
            return new ModelAndView("redirect:/evento");     
        }
        
        return mv;
    }

    @PostMapping("editar")
    public ModelAndView editar(FormPresencaParticipanteDTO formPresencaDTO, Long idEvento, Long diaDoEvento, RedirectAttributes redirAtt){
        
        presencaService.cadastrarTodos(formPresencaDTO.getPresencas());

        ModelAndView mv = new ModelAndView("redirect:/presenca/listar?idEvento="+idEvento+"&dia="+diaDoEvento);
        redirAtt.addFlashAttribute("mensagem", "Atualizado com sucesso!");

        

        return mv;
    }
}
