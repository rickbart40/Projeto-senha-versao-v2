package Projeto_senha_v2.demo.Service;

import Projeto_senha_v2.demo.Interface.ISenha;
import Projeto_senha_v2.demo.Model.SenhaNormal;
import Projeto_senha_v2.demo.Model.SenhaPrioritaria;

public class SenhaFactory {
    public abstract static class SenhaCreator {
        public abstract ISenha createSenha();
    }

    public static class SenhaNormalCreator extends SenhaCreator {
        @Override
        public ISenha createSenha() {
            return new SenhaNormal();
        }
    }

    public static class SenhaPrioritariaCreator extends SenhaCreator {
        @Override
        public ISenha createSenha() {
            return new SenhaPrioritaria();
        }
    }
}