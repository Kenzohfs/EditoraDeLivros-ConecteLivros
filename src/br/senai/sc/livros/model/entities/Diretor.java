package br.senai.sc.livros.model.entities;

import Exceptions.CodigoInvalidoException;

public class Diretor extends Pessoa {

    public Diretor(String CPF, String nome, String sobrenome, String email, Genero genero, String senha) {
        super(CPF, nome, sobrenome, email, genero, senha);
    }
}
