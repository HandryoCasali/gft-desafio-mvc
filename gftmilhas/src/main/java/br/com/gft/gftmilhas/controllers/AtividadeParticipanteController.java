package br.com.gft.gftmilhas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.gft.gftmilhas.dto.FormEntregaAtividadeDTO;
import br.com.gft.gftmilhas.entities.AtividadeParticipante;
import br.com.gft.gftmilhas.services.AtividadeParticipanteService;

@Controller
@RequestMapping("atividadeParticipante")
public class AtividadeParticipanteController {
    
    @Autowired
    private AtividadeParticipanteService atividadeParticipanteService;

    @PostMapping("editar")
    public ModelAndView editar(FormEntregaAtividadeDTO form, long idAtividade, RedirectAttributes redirAtt){
        List<AtividadeParticipante> entregas = form.getAtividades();
        atividadeParticipanteService.cadastrarTodos(entregas);
        redirAtt.addFlashAttribute("mensagem", "Atualizado com sucesso!");
        return new ModelAndView("redirect:/atividade/buscar?id="+ idAtividade);
    }
}
