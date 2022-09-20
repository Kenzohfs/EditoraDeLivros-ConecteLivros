package br.senai.sc.livros.model.factory;

import br.senai.sc.livros.model.entities.Autor;
import br.senai.sc.livros.model.entities.Diretor;
import br.senai.sc.livros.model.entities.Pessoa;
import br.senai.sc.livros.model.entities.Revisor;

public class PessoaFactory {
    public Pessoa getPessoa(String cpf, String nome, String sobrenome, String email, String senha, String genero, Integer tipo) {
        switch (tipo) {
            case 1 -> {
                return new Autor(cpf, nome, sobrenome, email, genero, senha);
            }
            case 2 -> {
                return new Revisor(cpf, nome, sobrenome, email, genero, senha);
            }
            case 3 -> {
                return new Diretor(cpf, nome, sobrenome, email, genero, senha);
            }
            default -> {
                return null;
            }
        }
    }
}
