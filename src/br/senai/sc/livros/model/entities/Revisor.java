package br.senai.sc.livros.model.entities;

import Exceptions.CodigoInvalidoException;

import java.util.ArrayList;

public class Revisor extends Pessoa {

    public Revisor(String CPF, String nome, String sobrenome, String email, Genero genero, String senha) {
        super(CPF, nome, sobrenome, email, genero, senha);
    }

}
