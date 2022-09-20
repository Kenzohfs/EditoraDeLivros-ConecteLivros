package Exceptions;

public class UsuarioSenhaIncorretoException extends RuntimeException{
    public UsuarioSenhaIncorretoException() {
        System.out.println("Usuário ou senha digitados estão incorretos!");
    }
}
