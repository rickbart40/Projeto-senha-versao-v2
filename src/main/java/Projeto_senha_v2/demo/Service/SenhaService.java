package Projeto_senha_v2.demo.Service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class SenhaService {
    // Implementação do padrão Singleton - guarda uma única instância da classe SenhaService
    private static SenhaService uniqueInstance;

    private int currentTicket;
    private List<Integer> calledTickets; // Lista de senhas chamadas
    private List<Integer> totalHistorico; // Historico acumulado de senhas chamadas

    // Construtor privado para evitar a criação de instâncias externas
    private SenhaService() {
        this.currentTicket = 0;
        this.calledTickets = new java.util.ArrayList<>();
        this.totalHistorico = new java.util.ArrayList<>();
    }

    // Método para obter a instância única da classe SenhaService
    public static synchronized SenhaService getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new SenhaService();
        }
        return uniqueInstance;
    }

    // Método para gerar a próxima senha
    public synchronized int gerarSenha() {
        this.currentTicket = this.currentTicket + 1; // Incrementa a senha atual
        return currentTicket;
    }

    public synchronized int getLastSenha() {
        return this.currentTicket;
    }

    // Método para chamar uma senha
    public synchronized void chamarSenha(int senha) {
        this.calledTickets.add(senha); // Adiciona a senha chamada à lista de senhas chamadas
        this.totalHistorico.add(senha); // Adiciona a senha chamada ao histórico total
    }

    public synchronized List<Integer> getHistorico() {
        return this.calledTickets; // Retorna o histórico total de senhas chamadas
    }

    // metodo retorna o permantente mantido mesmo apos a limpeza
    public synchronized List<Integer> getTotalHistorico() {
        return this.totalHistorico; // Retorna o histórico total de senhas chamadas
    }

    // Reseta o contador atual e a fila de ciclo, mas preserva o historico
    public synchronized void clear() {
        this.currentTicket = 0; // Reseta a senha atual
        this.calledTickets.clear(); // Limpa a lista de senhas chamadas
    }

    // metodo para limpar o historico acumulado
    public synchronized void clearTotalHistorico() {
        this.totalHistorico.clear(); // Limpa o histórico total de senhas chamadas
    }

}
