package br.senai.sc.livros.model.entities;

import br.senai.sc.livros.model.factory.PessoaFactory;
import br.senai.sc.livros.view.Menu;

import java.util.ArrayList;
import java.util.Objects;

public abstract class Pessoa {
    private String CPF, nome, sobrenome, email, senha;
    private Genero genero;

    static ArrayList<Pessoa> listaPessoas = new ArrayList<>();

    public Pessoa(String CPF, String nome, String sobrenome, String email, Genero genero, String senha) {
        this.CPF = CPF;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.genero = genero;
        this.senha = senha;
    }

    @Override
    public boolean equals(Object o) {
        Pessoa outraPessoa = (Pessoa) o;
        return CPF.equals(outraPessoa.CPF);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for (char l : CPF.toCharArray()) {
            hash += l;
        }
        return hash;
    }

    public String getCPF() {
        return CPF;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public String getSenha() {
        return senha;
    }

    public Pessoa validaLogin(String senha) {
        if (this.getSenha().equals(senha)) {
            return this;
        }
        ;
        throw new RuntimeException("Senha incorreta!");
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    @Override
    public String toString() {
        return nome + " " + sobrenome;
    }

    public static Pessoa cadastrar(String nome, String sobrenome, String email, Genero genero, String senha, String cpf, String confSenha) {
        if (senha.equals(confSenha)) {
            if (email.contains("@")) {
                int tipo = 1;
                if (Menu.getUsuario() instanceof Diretor) {
                    tipo = 2;
                }
                return new PessoaFactory().getPessoa(cpf, nome, sobrenome, email, senha, genero.ordinal(), tipo);
            } else {
                throw new RuntimeException("Email inválido!");
            }
        } else {
            throw new RuntimeException("Senhas não conferem!");
        }
    }
}
