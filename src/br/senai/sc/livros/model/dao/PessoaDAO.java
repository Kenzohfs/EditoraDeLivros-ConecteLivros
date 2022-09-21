package br.senai.sc.livros.model.dao;

import br.senai.sc.livros.model.factory.ConexaoFactory;
import br.senai.sc.livros.model.entities.*;
import br.senai.sc.livros.model.factory.PessoaFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class PessoaDAO {
    private Connection conn;

    private static final Set<Pessoa> listaPessoas = new HashSet<>();

    public PessoaDAO() {
        this.conn = new ConexaoFactory().connectDB();
    }

    static {
        listaPessoas.add(new Autor("12435678", "autor", "Rafaellizin",
                "autor@", Genero.MASCULINO, "123"));
        listaPessoas.add(new Revisor("12435678", "revisor", "Rafaellizin",
                "revisor@", Genero.MASCULINO, "123"));
        listaPessoas.add(new Revisor("12435678", "Revisor2", "Rafaellizin",
                "revisor2@", Genero.MASCULINO, "123"));
        listaPessoas.add(new Diretor("12435678", "diretor", "Rafaellizin",
                "diretor@", Genero.MASCULINO, "123"));
    }

    public void inserir(Pessoa pessoa) {
        String sqlCommand = "INSERT INTO PESSOAS (cpf, nome, sobrenome, email, genero, senha, tipo) values (?, ?, ?, ?, ?, ?, ?);";
        try (PreparedStatement pstm = conn.prepareStatement(sqlCommand)) {
            pstm.setString(1, pessoa.getCPF());
            pstm.setString(2, pessoa.getNome());
            pstm.setString(3, pessoa.getSobrenome());
            pstm.setString(4, pessoa.getEmail());
            pstm.setInt(5, pessoa.getGenero().ordinal());
            pstm.setString(6, pessoa.getSenha());
            pstm.setInt(7,
                    (pessoa instanceof Autor) ? 1 :
                            (pessoa instanceof Revisor) ? 2 : 3);
            try {
                pstm.execute();
            } catch (Exception e) {
                throw new RuntimeException("Erro na execução do comando SQL");
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro na preparação do comando SQL");
        }
        System.out.println("Cadastro chegou ao fim");
    }

    public void remover(Pessoa pessoa) {
        listaPessoas.remove(pessoa);
    }

    public Pessoa selecionarPorCPF(String CPF) {
        String sql = "SELECT * FROM PESSOAS WHERE cpf = ?";
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, CPF);
            try (ResultSet resultSet = pstm.executeQuery()) {
                if (resultSet.next()) {
                    return extrairObjeto(resultSet);
                }
            } catch (Exception e) {
                throw new RuntimeException("Erro na execução do comando SQL");
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro na preparação do comando SQL");
        }
        throw new RuntimeException("Algo deu ruim");
    }

    public Pessoa selecionarPorEmail(String email) {
        String sql = "SELECT * FROM PESSOAS WHERE email = ?";
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, email);
            try (ResultSet resultSet = pstm.executeQuery()) {
                if (resultSet.next()) {
                    return extrairObjeto(resultSet);
                }
            } catch (Exception e) {
                throw new RuntimeException("Erro na execução do comando SQL");
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro na preparação do comando SQL");
        }
        throw new RuntimeException("Algo deu ruim");
    }

    private Pessoa extrairObjeto(ResultSet resultSet) {
        try {
            return new PessoaFactory().getPessoa(
                    resultSet.getString("cpf"),
                    resultSet.getString("nome"),
                    resultSet.getString("sobrenome"),
                    resultSet.getString("email"),
                    resultSet.getString("senha"),
                    resultSet.getInt("genero"),
                    resultSet.getInt("tipo")
            );
        } catch (Exception e) {
            throw new RuntimeException("Erro ao extrair o objeto");
        }
    }
}
