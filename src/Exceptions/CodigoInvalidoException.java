package Exceptions;

public class CodigoInvalidoException extends RuntimeException{
    public CodigoInvalidoException() {
        System.out.println("ISBN inválido (não encontrado)!!");
    }
}
