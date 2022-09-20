package Exceptions;

public class LivroExistenteException extends RuntimeException{
    public LivroExistenteException(){
        System.out.println("ISBN inserido ja está cadastrado!!");
    }
}
