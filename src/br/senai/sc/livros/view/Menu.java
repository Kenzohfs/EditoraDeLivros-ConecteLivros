package br.senai.sc.livros.view;

import br.senai.sc.livros.model.entities.Autor;
import br.senai.sc.livros.model.entities.Diretor;
import br.senai.sc.livros.model.entities.Pessoa;
import br.senai.sc.livros.model.entities.Revisor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame implements ActionListener {
    private JButton cadastrarLivrosButton;
    private JButton SAIRButton;
    private JPanel menu;
    private JButton listarLivrosButton;
    private JButton listarAtividadesButton;
    private JButton cadastrarRevisorButton;

    private static Pessoa usuario;

    public Menu(Pessoa pessoa) {
        usuario = pessoa;
        criarComponentes();
    }

    public static Pessoa getUsuario() {
        return usuario;
    }

    public void criarComponentes() {
        setContentPane(menu);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);

        cadastrarLivrosButton.addActionListener(this);
        cadastrarLivrosButton.setActionCommand("cadastrarLivro");
        listarLivrosButton.addActionListener(this);
        listarLivrosButton.setActionCommand("listarLivros");
        listarAtividadesButton.addActionListener(this);
        listarAtividadesButton.setActionCommand("listarAtividades");
        cadastrarRevisorButton.addActionListener(this);
        cadastrarRevisorButton.setActionCommand("cadastrarRevisor");
        SAIRButton.addActionListener(this);
        SAIRButton.setActionCommand("sair");
        if (usuario instanceof Autor || usuario instanceof Revisor) {
            cadastrarRevisorButton.setVisible(false);
        }

        if (usuario instanceof Revisor || usuario instanceof Diretor) {
            cadastrarLivrosButton.setVisible(false);
        }
    }

    private static Estante estanteAtual;

    public static Estante getEstanteAtual() {
        return estanteAtual;
    }

    public static void setEstanteAtual(Estante novaEstante) {
        estanteAtual = novaEstante;
    }

    ;

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "cadastrarLivro" -> {
                new CadastroLivro(usuario, null);
            }
            case "listarLivros" -> {
                new Estante(1);
            }
            case "listarAtividades" -> {
                estanteAtual = new Estante(2);
            }
            case "cadastrarRevisor" -> {
                new CadastroPessoa();
            }
            case "sair" -> {
                usuario = null;
                Login login = new Login();
                login.setVisible(true);
            }
        }
        dispose();
    }
}
