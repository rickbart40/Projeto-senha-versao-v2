package Projeto_senha_v2.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import Projeto_senha_v2.demo.Service.SenhaService;

@Controller

public class SenhaController {
    private final SenhaService senhaService = SenhaService.getInstance();

    // view
    @GetMapping("/")
    public String index(Model model) {
        // Envia os dados para a view - ultima senha gerada
        model.addAttribute("ultimaSenha", senhaService.getLastSenha());
        // Envia o historico permanente para a view
        model.addAttribute("historico", senhaService.getTotalHistorico());
        return "index";
    }

    // display 
    @GetMapping("/display")
    public String display(Model model) {
        model.addAttribute("ultimaSenha", senhaService.getLastSenha());
        return "display";
    }

    // Ação de gerar uma nova senha
    @PostMapping("/gerarSenha")
    public String gerarSenha() {
        int novaSenha = senhaService.gerarSenha(); // Gera uma nova senha
        senhaService.chamarSenha(novaSenha); // Chama a nova senha
        return "redirect:/"; // Redireciona para a página inicial
    }

    // Ação de limpar o histórico de senhas chamadas
    @PostMapping("/limpar")
    public String limparSenhas() {
        senhaService.clear(); // Limpa o histórico de senhas chamadas
        return "redirect:/"; // Redireciona para a página inicial
    }

    // Ação de limpar historico
    @PostMapping("/limparHistorico")
    public String limparHistorico() {
        senhaService.clearTotalHistorico(); // Limpa o histórico total de senhas chamadas
        return "redirect:/"; // Redireciona para a página inicial
    }
}