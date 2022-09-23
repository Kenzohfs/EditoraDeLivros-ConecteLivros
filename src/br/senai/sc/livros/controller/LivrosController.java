package br.senai.sc.livros.controller;

import br.senai.sc.livros.model.entities.*;
import br.senai.sc.livros.model.service.LivroService;
import br.senai.sc.livros.view.Menu;

import java.util.ArrayList;
import java.util.Collection;
//Objeto que vai intermediar a view com a classe livros;


public class LivrosController {

    Livro model = new Livro();

    public Autor getAutor() {
        return model.getAutor();
    }

    public void setAutor(Autor autor) {
        model.setAutor(autor);
    }

    public boolean cadastrar(String titulo, String isbn, String qtdPag, Pessoa autor){
       return new LivroService().inserir(Livro.cadastrar(titulo, Integer.parseInt(isbn), Integer.parseInt(qtdPag), (Autor)autor));
    }

    public Collection<Livro> getAllLivros(){
        return new LivroService().getAllLivros();
    }

    public Collection<Livro> selecionarPorAutor(){
        return new LivroService().selecionarPorAutor(Menu.getUsuario());
    }

    public Collection<Livro> listarAtividades(){
        return new LivroService().listarAtividades(Menu.getUsuario());
    }

    public Livro selecionarPorISBN(int isbn){
        return new LivroService().selecionarPorISBN(isbn);
    }

    public void atualizarStatus(Livro livro, Status status){
        new LivroService().atualizarStatus(livro, status);
    }

    public void adicionarRevisor(Livro livro, Pessoa usuario){
        new LivroService().adicionarRevisor(livro, (Revisor) usuario);
    }


}
