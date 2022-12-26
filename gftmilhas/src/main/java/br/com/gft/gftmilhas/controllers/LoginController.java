package br.com.gft.gftmilhas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.gft.gftmilhas.entities.Usuario;
import br.com.gft.gftmilhas.services.TokenService;
import br.com.gft.gftmilhas.services.UsuarioService;

@Controller
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TokenService tokenService;

    
    @GetMapping("/login")
    public String login() {
        return "login/login";
    }

    @GetMapping("/cadastrar")
    public String getCadastro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "login/cadastroLogin.html";
    }

    @PostMapping("/cadastrar")
    public ModelAndView cadastrar(Usuario usuario, RedirectAttributes redirAtt) {
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
    public String getCadastroAdm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "login/cadastroLoginAdm.html";
    }

    @PostMapping("/cadastrarAdm")
    public ModelAndView cadastrarAdm(Usuario usuario, RedirectAttributes redirAtt) {
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

    @GetMapping("/recuperar")
    public ModelAndView getRecuperar() {
        return new ModelAndView("login/form-recupera");
    }

    @PostMapping("/recuperar")
    public ModelAndView recuperar(String username, RedirectAttributes redirAtt) {
        ModelAndView mv = new ModelAndView("redirect:/login");
        try {
            usuarioService.recuperarSenha(username);

            redirAtt.addFlashAttribute("mensagem", "Email enviado com sucesso.");
        } catch (Exception e) {
            return new ModelAndView("login/form-recupera").addObject("mensagemErro", e.getMessage());

        }
        return mv;
    }

    @GetMapping("/recuperar/{token}")
    public ModelAndView getFormNovaSenha(@PathVariable String token) {
        ModelAndView mv = new ModelAndView("login/form-novasenha");
        mv.addObject("token", token);
        return mv;
    }

    @PostMapping("/novasenha")
    public ModelAndView novaSenha(String token, String novaSenha, RedirectAttributes redirAtt) {
        ModelAndView mv = new ModelAndView("redirect:/login");
        try {
            boolean tokenValido = tokenService.verificaToken(token);
            if (!tokenValido) {
                redirAtt.addFlashAttribute("mensagemErro", "Token de acesso expirado");
                return mv;
            }
            Long id = tokenService.retornarIdUsuario(token);
            usuarioService.alterarSenha(id, novaSenha);

        } catch (Exception e) {
            redirAtt.addFlashAttribute("mensagemErro",e.getMessage());
        }

        return mv;
    }


}
