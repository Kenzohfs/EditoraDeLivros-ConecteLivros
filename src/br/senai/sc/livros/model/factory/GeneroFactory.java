package br.senai.sc.livros.model.factory;

import br.senai.sc.livros.model.entities.Genero;

public class GeneroFactory {
    public Genero getGenero(Integer ordinal) {
        switch (ordinal) {
            case 1 -> {
                return Genero.MASCULINO;
            }
            case 2 -> {
                return Genero.FEMININO;
            }
            default -> {
                return Genero.OUTRO;
            }
        }
    }
}
