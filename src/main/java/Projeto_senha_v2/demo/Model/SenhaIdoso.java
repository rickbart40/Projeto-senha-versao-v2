package Projeto_senha_v2.demo.Model;

import Projeto_senha_v2.demo.Interface.ISenha;

public class SenhaIdoso implements ISenha {
    @Override
    public String gerarTicket() {
        return String.format(
            "I-%05d",
            System.currentTimeMillis() % 100000
        );
    }
}