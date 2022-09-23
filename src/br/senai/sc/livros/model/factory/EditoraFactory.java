package br.senai.sc.livros.model.factory;

import br.senai.sc.livros.model.entities.Editora;

public class EditoraFactory {
    public Editora getEditora(String cnpj, String nome) {
        return new Editora(cnpj, nome);
    }
}
