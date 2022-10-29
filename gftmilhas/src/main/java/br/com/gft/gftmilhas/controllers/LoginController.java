package br.com.gft.gftmilhas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.gft.gftmilhas.entities.Usuario;
import br.com.gft.gftmilhas.services.UsuarioService;

@Controller
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;
    
    @GetMapping("/login")
    public String login(){
        return "login/login";
    }

    @GetMapping("/cadastrar")
    public String getCadastro(Model model){
        model.addAttribute("usuario", new Usuario());
        return "login/cadastroLogin.html";
    }

    @PostMapping("/cadastrar")
    public ModelAndView cadastrar(Usuario usuario, RedirectAttributes redirAtt){
        ModelAndView mav = new ModelAndView("redirect:/login");
        try {
            usuarioService.cadastrar(usuario);
        } catch (Exception e) {
            ModelAndView mv = new ModelAndView("login/cadastroLogin.html");
            mv.addObject("usuario", usuario);
            mv.addObject("erroCadastro", e.getMessage());
            return mv;
        }

        redirAtt.addFlashAttribute("cadastroSucesso", "Usuário cadastrado com sucesso!");
        return mav;
    }

    @GetMapping("/cadastrarAdm")
    public String getCadastroAdm(Model model){
        model.addAttribute("usuario", new Usuario());
        return "login/cadastroLoginAdm.html";
    }

    @PostMapping("/cadastrarAdm")
    public ModelAndView cadastrarAdm(Usuario usuario, RedirectAttributes redirAtt){
        ModelAndView mav = new ModelAndView("redirect:/login");
        try {
            usuarioService.cadastrarAdm(usuario);
        } catch (Exception e) {
            ModelAndView mv = new ModelAndView("login/cadastroLogin.html");
            mv.addObject("usuario", usuario);
            mv.addObject("erroCadastro", e.getMessage());
            return mv;
        }

        redirAtt.addFlashAttribute("cadastroSucesso", "Usuário cadastrado com sucesso!");
        return mav;
    }
}
