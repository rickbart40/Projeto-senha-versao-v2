package Projeto_senha_v2.demo.Model;

import Projeto_senha_v2.demo.Interface.ISenha;

public class SenhaComun implements ISenha {
    @Override
    public String gerarTicket() {
        return String.format(
            "C-%05d",
            System.currentTimeMillis() % 100000
        );
    }
}
