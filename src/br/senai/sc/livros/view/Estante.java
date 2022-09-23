package br.senai.sc.livros.view;

import br.senai.sc.livros.controller.LivrosController;
import br.senai.sc.livros.model.entities.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Estante extends JFrame {
    private JPanel estante;
    private JTable tabelaLivros;
    private JButton buttonVoltar;
    private JButton editarButton;

    private static int opcaoEstante;

    public static int getOpcaoEstante() {
        return opcaoEstante;
    }

    public Estante(int option) {
        opcaoEstante = option;
        criarComponentes();
        buttonVoltar.addActionListener(e -> {
            dispose();
            new Menu(Menu.getUsuario());
        });
        editarButton.addActionListener(e -> {
            LivrosController livrosController = new LivrosController();
            Pessoa usuario = Menu.getUsuario();

            int row = tabelaLivros.getSelectedRow();
            if (row != -1) {
                int isbn = (int) tabelaLivros.getValueAt(row, 0);
                Livro livro = livrosController.selecionarPorISBN(isbn);
                if (usuario instanceof Autor) {
                    livrosController.atualizarStatus(livro, Status.AGUARDANDO_REVISAO);
                    JOptionPane.showMessageDialog(null, "Livro editado com sucesso!");
                    dispose();
                    new Estante(option);
                } else {
                    //Revisor ou Diretor, irá separar dentro da função

                    new CadastroLivro(usuario, livro);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Selecione um livro!");
            }

        });
    }

    private void criarComponentes() {
        LivrosController livrosController = new LivrosController();
        System.out.println("Opção: " + opcaoEstante);
        if (opcaoEstante == 1) {
            tabelaLivros.setModel(new DefaultTableModelArrayList(livrosController.getAllLivros()));
        } else {
            tabelaLivros.setModel(new DefaultTableModelArrayList(livrosController.listarAtividades()));
        }
        tabelaLivros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setContentPane(estante);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }


}
