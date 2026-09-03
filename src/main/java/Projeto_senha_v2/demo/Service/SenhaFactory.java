package Projeto_senha_v2.demo.Service;

import Projeto_senha_v2.demo.Interface.ISenha;
import Projeto_senha_v2.demo.Model.SenhaIdoso;
import Projeto_senha_v2.demo.Model.SenhaVip;

public class SenhaFactory {
    public abstract static class SenhaCreator {
        public abstract ISenha createSenha();
    }

    public static class SenhaVipCreator extends SenhaCreator {
        @Override
        public ISenha createSenha() {
            return new SenhaVip();
        }
    }

    public static class SenhaIdosoCreator extends SenhaCreator {
        @Override
        public ISenha createSenha() {
            return new SenhaIdoso();
        }
    }

    public static class SenhaComunCreator extends SenhaCreator {
        @Override
        public ISenha createSenha() {
            return new Projeto_senha_v2.demo.Model.SenhaComun();
        }
    }
}