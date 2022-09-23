package br.senai.sc.livros.model.factory;

import br.senai.sc.livros.model.entities.*;

public class PessoaFactory {
    public Pessoa getPessoa(String cpf, String nome, String sobrenome, String email, String senha, Integer genero, Integer tipo) {
        switch (tipo) {
            case 1 -> {
                return new Autor(cpf, nome, sobrenome, email, new GeneroFactory().getGenero(genero), senha);
            }
            case 2 -> {
                return new Revisor(cpf, nome, sobrenome, email, new GeneroFactory().getGenero(genero), senha);
            }
            default -> {
                return new Diretor(cpf, nome, sobrenome, email, new GeneroFactory().getGenero(genero), senha);
            }
        }
    }
}
