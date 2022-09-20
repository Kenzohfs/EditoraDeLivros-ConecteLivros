package br.senai.sc.livros.view;

import br.senai.sc.livros.controller.LivrosController;
import br.senai.sc.livros.model.entities.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroLivro extends JFrame implements ActionListener{
    private JButton voltarButton;
    private JButton cadastrarButton;
    private JTextField tituloInput;
    private JTextField isbnInput;
    private JTextField qtdPagInput;
    private JPanel cadastroLivro;
    private JComboBox opcoesStatus;
    private JLabel labelStatus;
    private JButton confirmarButton;

    private static Pessoa usuario;
    private static Livro livro;
    CadastroLivro(Pessoa pessoa, Livro livrozada) {
        usuario = pessoa;
        livro = livrozada;
        criarComponentes();
    }

    private void criarComponentes() {


        opcoesStatus.setModel(new DefaultComboBoxModel<>(Status.getAllStatus()));

        setContentPane(cadastroLivro);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();

        cadastrarButton.addActionListener((ActionListener) this);
        cadastrarButton.setActionCommand("cadastrarButton");
        confirmarButton.addActionListener((ActionListener) this);
        confirmarButton.setActionCommand("confirmarButton");
        voltarButton.addActionListener((ActionListener) this);
        voltarButton.setActionCommand("voltarButton");

        if(usuario instanceof Autor){
            confirmarButton.setVisible(false);
            opcoesStatus.setVisible(false);
            labelStatus.setVisible(false);
        }
        if(usuario instanceof Revisor || usuario instanceof Diretor){
            cadastrarButton.setVisible(false);
            tituloInput.setEnabled(false);
            isbnInput.setEnabled(false);
            qtdPagInput.setEnabled(false);
            tituloInput.setText(livro.getTitulo());
            isbnInput.setText(String.valueOf(livro.getISBN()));
            qtdPagInput.setText(String.valueOf(livro.getQntdPaginas()));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        LivrosController livrosController = new LivrosController();
        switch (e.getActionCommand()){
            case "cadastrarButton" -> {
                String titulo = tituloInput.getText();
                String isbn = isbnInput.getText();
                String qtdPag = qtdPagInput.getText();
                if(titulo.isEmpty() ||
                        isbn.isEmpty() ||
                        qtdPag.isEmpty() ){
                    JOptionPane.showMessageDialog(null, "Há campos vazios!");
                } else {
                    if(livrosController.cadastrar(titulo, isbn, qtdPag, usuario)){
                        JOptionPane.showMessageDialog(null, "Livro cadastrado com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "ISBN já cadastrado!");
                    };

                    dispose();
                    new Menu(Menu.getUsuario());
                }
            }
            case "confirmarButton" -> {
                livrosController.atualizarStatus(livro, Status.getStatusCorreto(String.valueOf(opcoesStatus.getSelectedItem())));
                if(usuario instanceof Revisor){
                    livrosController.adicionarRevisor(livro, usuario);
                    JOptionPane.showMessageDialog(null, "Livro revisado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Livro editado com sucesso!");

                }
                dispose();
                Menu.getEstanteAtual().dispose();
                Estante novaEstante = new Estante(Estante.getOpcaoEstante());
                Menu.setEstanteAtual(novaEstante);
            }
            case "voltarButton" -> {
                dispose();

            }
        }
    }



}
