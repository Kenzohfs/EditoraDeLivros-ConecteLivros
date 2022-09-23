package br.senai.sc.livros.model.service;

import br.senai.sc.livros.model.dao.LivroDAO;
import br.senai.sc.livros.model.entities.*;
import br.senai.sc.livros.view.Menu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LivroService {
    LivroDAO bdLivro = new LivroDAO();

    public boolean inserir(Livro livro) {
        return bdLivro.inserir(livro);
    }

    public void remover(Livro livro) {
        bdLivro.remover(livro);
    }

    public Livro selecionar(int isbn) {
        return bdLivro.selecionar(isbn);
    }

    public void atualizar(int isbn, Livro livroAtualizado) {
        bdLivro.atualizar(isbn, livroAtualizado);
    }

    public Collection<Livro> getAllLivros(){
        Pessoa usuario = Menu.getUsuario();

        if(usuario instanceof Autor){
            return bdLivro.selecionarPorAutor(usuario);
        } else if(usuario instanceof Revisor){
            Collection<Livro> livros = bdLivro.selecionarPorStatus(Status.AGUARDANDO_REVISAO, usuario); //listar livros
//            livros.addAll(bdLivro.selecionarPorStatus(Status.EM_REVISAO)); //listar atividades/*/
            return livros;
        } else {
            return bdLivro.getAllLivros();
        }
    }

    public Collection<Livro> selecionarPorAutor(Pessoa pessoa) {
        return bdLivro.selecionarPorAutor(pessoa);
    }

    public Collection<Livro> selecionarPorStatus(Status status) {
        return bdLivro.selecionarPorStatus(status, Menu.getUsuario());
    }

    public Collection<Livro> selecionarAtividadesAutor(Pessoa pessoa) {
        return bdLivro.selecionarAtividadesAutor(pessoa);
    }

    public Collection<Livro> listarAtividades(Pessoa pessoa){
        if(pessoa instanceof Autor){
            return selecionarAtividadesAutor(pessoa);
        } else if(pessoa instanceof Revisor){
            System.out.println("Revisor");
            Collection<Livro> livros = bdLivro.selecionarAtividadesRevisor(pessoa);
            System.out.println(livros);
            return livros;
        } else {
            return selecionarPorStatus(Status.APROVADO);
        }
    }

    public Livro selecionarPorISBN(int isbn){
        for(Livro livro : listarAtividades(new Diretor())){
            if(livro.getISBN() == isbn){
                return livro;
            }
        }
        throw new RuntimeException("ISBN não encontrado!");
    }

    public void atualizarStatus(Livro livro, Status status){
        livro.setStatus(status);
        atualizar(livro.getISBN(), livro);
    }

    public void adicionarRevisor(Livro livro, Revisor revisor){
        livro.setRevisor(revisor);
        atualizar(livro.getISBN(), livro);
    }
}
