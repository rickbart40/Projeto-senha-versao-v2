package Projeto_senha_v2.demo.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import Projeto_senha_v2.demo.Interface.ISenha;

@Service
public class SenhaService {
    // Singleton
    private static SenhaService uniqueInstance;
    private String currentTicket;
    private List<String> calledTickets;
    private List<String> totalHistorico;

    // Factory
    private SenhaFactory.SenhaCreator senhaCreator;

    // Construtor privado
    private SenhaService() {
        this.currentTicket = null;
        this.calledTickets = new ArrayList<>();
        this.totalHistorico = new ArrayList<>();
    }

    // Obtém a única instância
    public static synchronized SenhaService getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new SenhaService();
        }
        return uniqueInstance;
    }

    // Gera uma senha
    public synchronized String gerarSenha(String tipo) {
        // Escolhe qual Creator utilizar (Comun - Idoso - Vip)
        if (tipo.equalsIgnoreCase("Vip")) {
            senhaCreator = new SenhaFactory.SenhaVipCreator();
        } else if (tipo.equalsIgnoreCase("Idoso")) {
            senhaCreator = new SenhaFactory.SenhaIdosoCreator();
        } else if (tipo.equalsIgnoreCase("comum")) {
            senhaCreator = new SenhaFactory.SenhaComunCreator();
        } else {
            throw new IllegalArgumentException(
                "Tipo de senha inválido: " + tipo
            );
        }

        // Factory cria a senha
        ISenha senha = senhaCreator.createSenha();
        // Obtém o ticket
        currentTicket = senha.gerarTicket();

        return currentTicket;
    }

    // Retorna a última senha
    public synchronized String getLastSenha() {
        return currentTicket;
    }

    // Chama uma senha
    public synchronized void chamarSenha(String senha) {
        this.calledTickets.add(senha);
        this.totalHistorico.add(senha);
    }

    // Retorna senhas chamadas
    public synchronized List<String> getHistorico() {
        return this.calledTickets;
    }

    // Retorna histórico permanente
    public synchronized List<String> getTotalHistorico() {
        return this.totalHistorico;
    }

    // Limpa senhas chamadas e reseta a senha atual
    public synchronized void clear() {
        this.currentTicket = null;
        this.calledTickets.clear();
    }

    // Limpa histórico completo
    public synchronized void clearTotalHistorico() {
        this.totalHistorico.clear();
    }
}