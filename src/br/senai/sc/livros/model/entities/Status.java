package br.senai.sc.livros.model.entities;

import br.senai.sc.livros.view.Menu;

import java.util.Arrays;

public enum Status {
    AGUARDANDO_REVISAO("Aguardando revisão", new int[]{1, 3}),
    EM_REVISAO("Em revisão", new int[]{2}),
    APROVADO("Aprovado", new int[]{2}),
    AGUARDANDO_EDICAO("Aguardando edição", new int[]{2}),
    REPROVADO("Reprovado", new int[]{2, 3}),
    PUBLICADO("Publicado", new int[]{3});

    private String nome;
    private int[] permissao;

    Status(String nome, int[] permissao) {
        this.nome = nome;
        this.permissao = permissao;
    }

    public static String[] getAllStatus() {
        if(Menu.getUsuario() instanceof Revisor){
            String[] stringStatus = new String[4];
            stringStatus[0] = Status.EM_REVISAO.getNome();
            stringStatus[1] = Status.AGUARDANDO_EDICAO.getNome();
            stringStatus[2] = Status.APROVADO.getNome();
            stringStatus[3] = Status.REPROVADO.getNome();
            return stringStatus;
        } else {
            String[] stringStatus = new String[3];
            stringStatus[0] = Status.PUBLICADO.getNome();
            stringStatus[1] = Status.AGUARDANDO_REVISAO.getNome();
            stringStatus[2] = Status.REPROVADO.getNome();
            return stringStatus;
        }
    }

    public static Status getStatusCorreto(String stringStatus) {
        for (Status status : Status.values()) {
            if (status.getNome().equals(stringStatus)) {
                return status;
            }
        }
        throw  new RuntimeException("Status não encontrado");
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
