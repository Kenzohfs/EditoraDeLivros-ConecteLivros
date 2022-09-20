package br.senai.sc.livros.model.entities;

import java.util.ArrayList;
import java.util.Objects;

public class Livro {
    private Autor autor;
    private Editora editora;
    private String titulo;
    private Status status;
    //    1 - Aprovado
//    2 - Em revisão
//    3 - Aguardando revisão
//    4 - Aguardando edição
//    5 - Reprovado
//    6 - Publicado
    private Integer qntdPaginas;
    private Double paginasRevisadas = 0.0;
    private Integer ISBN;
    private Pessoa revisor;

    public Livro() {
    }
    ;

    public Livro(Autor autor, String titulo, Status status, int qntdPaginas, int ISBN) {
        this.autor = autor;
        this.titulo = titulo;
        this.status = status;
        this.qntdPaginas = qntdPaginas;
        this.ISBN = ISBN;
    }

    public static Livro cadastrar(String titulo, int isbn, int qtdPag, Autor autor) {
        return new Livro(autor, titulo, Status.AGUARDANDO_REVISAO, qtdPag, isbn);
    }


    @Override
    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
        Livro livro = (Livro) o;
        return this.ISBN == livro.ISBN;
    }

    @Override
    public int hashCode() {
        return ISBN;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getQntdPaginas() {
        return qntdPaginas;
    }


    public Integer getISBN() {
        return ISBN;
    }

    public Pessoa getRevisor() {
        return revisor;
    }

    public void setRevisor(Revisor revisor) {
        this.revisor = revisor;
    }

    @Override
    public String toString() {
        String editoraNome;
        String revisorNome;

        if (getEditora() == null) {
            editoraNome = "Livro não publicado";
        } else {
            editoraNome = editora.getNome();
        }
        if (getRevisor() == null) {
            revisorNome = "Sem revisor responsável";
        } else {
            revisorNome = getRevisor().getNome();
        }

        return "Livro{" +
                "autor=" + autor.getNome() +
                ", editora=" + editoraNome +
                ", titulo='" + titulo + '\'' +
                ", status=" + status.getNome() +
                ", qntdPaginas=" + qntdPaginas +
                ", ISBN=" + ISBN +
                ", RevisorResponsável: " + revisorNome +
                ", % Páginas revisadas: " + paginasRevisadas +
                '}';
    }
}
