package Exceptions;

public class OpcaoInvalidaException extends RuntimeException{
    public OpcaoInvalidaException(){
        System.out.println("Opção inválida!!");
    }
}
