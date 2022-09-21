package br.senai.sc.livros.model.service;

import br.senai.sc.livros.model.dao.PessoaDAO;
import br.senai.sc.livros.model.entities.Pessoa;

import javax.swing.*;
import java.sql.SQLException;

public class PessoaService {

    public void inserir(Pessoa pessoa) {
        new PessoaDAO().inserir(pessoa);
    }

    public void remover(Pessoa pessoa) {
        new PessoaDAO().remover(pessoa);
    }

    public Pessoa selecionarPorCPF(String CPF) {
        return new PessoaDAO().selecionarPorCPF(CPF);
    }

    public Pessoa selecionarPorEmail(String email) {
        return new PessoaDAO().selecionarPorEmail(email);
    }
}
