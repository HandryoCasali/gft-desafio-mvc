package br.com.gft.gftmilhas.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.gft.gftmilhas.dto.FormEntregaAtividadeDTO;
import br.com.gft.gftmilhas.entities.Atividade;
import br.com.gft.gftmilhas.entities.Evento;
import br.com.gft.gftmilhas.enums.StatusConclusao;
import br.com.gft.gftmilhas.services.AtividadeService;
import br.com.gft.gftmilhas.services.EventoService;

@Controller
@RequestMapping("atividade")
public class AtividadeController {

    @Autowired
    private EventoService eventoService;

    @Autowired
    private AtividadeService atividadeService;

    @GetMapping("listar")
    public ModelAndView listar(@RequestParam("id") Long idEvento) {
        ModelAndView mv = new ModelAndView("atividade/listarAtividades.html");

        List<Atividade> atividades;
        try {
            atividades = eventoService.buscarPorId(idEvento).getAtividades();

        } catch (Exception e) {
            atividades = new ArrayList<>();
            mv.addObject("erroBuscar", e.getMessage());
        }
        mv.addObject("idEvento", idEvento);
        mv.addObject("atividades", atividades);
        return mv;
    }

    @GetMapping("buscar")
    public ModelAndView buscarPorId(@RequestParam Long id) {
        ModelAndView mv = new ModelAndView("atividade/detalhes.html");

        Atividade atividade;
        try {
            atividade = atividadeService.buscarPorId(id);
        } catch (Exception e) {
            atividade = new Atividade();
            mv.addObject("erroBuscar", e.getMessage());
        }

        FormEntregaAtividadeDTO entrega = new FormEntregaAtividadeDTO(atividade.getParticipantes());

        mv.addObject("atividade", atividade);
        mv.addObject("formEntregaAtividadeDTO", entrega);
        mv.addObject("statusValores", StatusConclusao.values());

        return mv;
    }

    @GetMapping("cadastrar")
    public ModelAndView getForm(@RequestParam("id") Long idEvento) {
        ModelAndView mv = new ModelAndView("atividade/form.html");

        boolean existeEvento = eventoService.existe(idEvento);
        if (!existeEvento) {
            mv.addObject("erroBuscar", "Não foi encontrado evento com id: " + idEvento);
            return mv;
        }

        mv.addObject("idEvento", idEvento);
        mv.addObject("atividade", new Atividade());
        return mv;
    }

    @PostMapping("cadastrar")
    public ModelAndView cadastrar(Long idEvento, @Valid Atividade atividade, BindingResult br,
            RedirectAttributes redirAtt) {
        ModelAndView mv = new ModelAndView("redirect:/atividade/listar?id=" + idEvento);

        if (br.hasErrors()) {
            return new ModelAndView("redirect:/atividade/cadastrar?id=" + idEvento);
        }

        try {
            Evento evento = eventoService.buscarPorId(idEvento);
            atividade.setEvento(evento);
            atividadeService.cadastrar(atividade);

            redirAtt.addFlashAttribute("mensagem", "Atividade cadastrada com sucesso!");
        } catch (Exception e) {
            redirAtt.addFlashAttribute("erroBuscar", e.getMessage());
        }

        return mv;
    }

    @GetMapping("editar")
    public ModelAndView getFormEditar(@RequestParam("id") Long id) {
        ModelAndView mv = new ModelAndView("atividade/form.html");
        Atividade atividade;
        try {
            atividade = atividadeService.buscarPorId(id);
            mv.addObject("idEvento", atividade.getEvento().getId());
        } catch (Exception e) {
            mv.addObject("erroBuscar", e.getMessage());
            atividade = new Atividade();
        }

        mv.addObject("atividade", atividade);
        return mv;
    }

    @GetMapping("deletar")
    public String excluir(@RequestParam("id") Long id,
            RedirectAttributes redirAtt) {

        long idEvento = 0;
        try {
            idEvento = atividadeService.buscarPorId(id).getEvento().getId();
            atividadeService.deletar(id);
            redirAtt.addFlashAttribute("mensagem", "Atividade excluída com sucesso!");
        } catch (Exception e) {
            redirAtt.addFlashAttribute("erroDeletar", e.getMessage());
        }

        return "redirect:/atividade/listar?id=" + idEvento;
    }

}
