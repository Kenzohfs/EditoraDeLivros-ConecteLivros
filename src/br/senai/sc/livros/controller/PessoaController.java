package br.senai.sc.livros.controller;

import br.senai.sc.livros.model.entities.Genero;
import br.senai.sc.livros.model.entities.Pessoa;
import br.senai.sc.livros.model.service.PessoaService;

import javax.swing.*;

public class PessoaController {
    Pessoa model;

    public Pessoa validaLogin(String email, String senha) {
        PessoaService service = new PessoaService();
        model = service.selecionarPorEmail(email);
        return model.validaLogin(senha);
    }

    public void cadastrar(String nome, String sobrenome, String email, Object genero, String senha, String cpf, String confSenha) {
        PessoaService service = new PessoaService();
        Pessoa pessoa = Pessoa.cadastrar(nome, sobrenome, email, (Genero)genero, senha, cpf, confSenha);
        service.inserir(pessoa);

    }
}
