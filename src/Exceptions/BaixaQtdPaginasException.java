package Exceptions;

public class BaixaQtdPaginasException extends RuntimeException{
    public BaixaQtdPaginasException(){
        System.out.println("O seu livro deve conter no mínimo 50 páginas.");
    }
}
