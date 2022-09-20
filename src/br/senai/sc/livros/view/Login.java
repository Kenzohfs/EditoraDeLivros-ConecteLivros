package br.senai.sc.livros.view;

import br.senai.sc.livros.controller.PessoaController;
import br.senai.sc.livros.model.entities.Pessoa;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame implements Runnable{

    PessoaController pessoaController = new PessoaController();

    private JTextField emailInput;
    private JPasswordField passwordInput;
    private JButton CADASTRARSEButton;
    private JButton logarButton;
    private JPanel login;

    public Login(){
        criarComponentes();
        logarButton.addActionListener(e -> {
            if(emailInput.getText().isEmpty() || passwordInput.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"Há campos vázios!");
            } else {
                try{

                    Pessoa pessoa = pessoaController.validaLogin(emailInput.getText(), passwordInput.getText());
                    dispose();

                    new Menu(pessoa);

                }catch (RuntimeException err){
                    JOptionPane.showMessageDialog(null, err.getMessage());
                }
            }
        });
        CADASTRARSEButton.addActionListener(e -> {
            new CadastroPessoa();
            dispose();
        });
    }

    private void criarComponentes(){
        setContentPane(login);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        run();
        pack();
    }

    @Override
    public void run() {
        if(!isVisible()){
            setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "A janela ja está aberta!");
        }
    }

    public static void main(String[] args) {
        Login programa = new Login();
    }

}
