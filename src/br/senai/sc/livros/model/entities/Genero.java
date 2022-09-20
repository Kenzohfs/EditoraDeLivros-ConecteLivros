package br.senai.sc.livros.model.entities;

public enum Genero {
    MASCULINO("Masculino"),
    FEMININO("Feminino"),
    OUTRO("Outro");
    String nome;
    Genero(String nome){
        this.nome = nome;
    }

    public static Genero getGeneroCorreto(String stringGenero) {
        for (Genero genero : Genero.values()) {
            if (genero.getNome().equals(stringGenero)) {
                return genero;
            }
        }
        throw  new RuntimeException("Genero não encontrado");
    }

    private String getNome() {
        return this.nome;
    }
}
