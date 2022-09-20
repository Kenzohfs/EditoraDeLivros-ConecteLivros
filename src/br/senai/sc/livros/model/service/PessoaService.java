package br.senai.sc.livros.model.service;

import br.senai.sc.livros.model.dao.PessoaDAO;
import br.senai.sc.livros.model.entities.Pessoa;

import javax.swing.*;
import java.sql.SQLException;

public class PessoaService {
    PessoaDAO bdPessoa = new PessoaDAO();

    public void inserir(Pessoa pessoa) {
        try{
            bdPessoa.inserir(pessoa);
        } catch (SQLException err){
            JOptionPane.showMessageDialog(null, "Erro: " + err.getMessage());
            System.out.println(err.getMessage());
        }
    }

    public void remover(Pessoa pessoa) {
        bdPessoa.remover(pessoa);
    }

    public Pessoa selecionarPorCPF(String CPF) {
        return bdPessoa.selecionarPorCPF(CPF);
    }

    public Pessoa selecionarPorEmail(String email){
        return bdPessoa.selecionarPorEmail(email);
    }
}
