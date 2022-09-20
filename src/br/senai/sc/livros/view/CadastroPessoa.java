package br.senai.sc.livros.view;

import br.senai.sc.livros.controller.PessoaController;
import br.senai.sc.livros.model.entities.Genero;

import javax.swing.*;

public class CadastroPessoa extends JFrame {
    private JButton voltarButton;
    private JButton cadastrarButton;
    private JPanel cadastroPessoa;
    private JTextField sobrenomeInput;
    private JTextField emailInput;
    private JPasswordField senhaInput;
    private JPasswordField confirmaSenhaInput;
    private JTextField nomeInput;
    private JTextField cpfInput;
    private JComboBox<Genero> generoInput;

    public CadastroPessoa() {
        criarComponentes();
        voltarButton.addActionListener(e -> {
            new Login();
            dispose();
        });
        cadastrarButton.addActionListener(e -> {
            PessoaController controller = new PessoaController();

            if (nomeInput.getText().isEmpty() ||
                    sobrenomeInput.getText().isEmpty() ||
                    emailInput.getText().isEmpty() ||
                    cpfInput.getText().isEmpty() ||
                    senhaInput.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Há campos não preenchidos!");
            } else {
                try {
                    controller.cadastrar(nomeInput.getText(), sobrenomeInput.getText(), emailInput.getText(), generoInput.getSelectedItem(), senhaInput.getText(), cpfInput.getText(), confirmaSenhaInput.getText());
                    JOptionPane.showMessageDialog(null, "Cadastro efetuado com sucesso!");
                    if (Menu.getUsuario() == null) {
                        new Login();
                        dispose();
                    } else {
                        dispose();
                        voltar();
                    }
                } catch (RuntimeException err) {
                    JOptionPane.showMessageDialog(null, err.getMessage());
                }
            }

        });
    }

    private void voltar() {
        Menu menu = new Menu(Menu.getUsuario());
    }

    public void criarComponentes() {
        generoInput.setModel(new DefaultComboBoxModel<>(Genero.values()));
        setContentPane(cadastroPessoa);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }
}
