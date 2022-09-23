package br.senai.sc.livros.model.factory;

import br.senai.sc.livros.model.entities.*;

public class LivroFactory {
    public Livro getLivro(int isbn, String titulo, Status status, int qtdPaginas, Autor autor, Editora editora, Revisor revisor) {
        Livro livro = new Livro(autor, titulo, status, qtdPaginas, isbn);
        livro.setEditora(editora);
        livro.setRevisor(revisor);

        return livro;
    }

}
