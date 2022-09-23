package br.senai.sc.livros.model.dao;

import br.senai.sc.livros.model.entities.Editora;
import br.senai.sc.livros.model.entities.Pessoa;
import br.senai.sc.livros.model.factory.ConexaoFactory;
import br.senai.sc.livros.model.factory.EditoraFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EditoraDAO {
    private Connection conn;

    public EditoraDAO() {
        this.conn = new ConexaoFactory().connectDB();
    }

    public Editora selecionarPorCNPJ(String CNPJ) {
        String sql = "SELECT * FROM EDITORAS WHERE cpf = ?";
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, CNPJ);
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

    private Editora extrairObjeto(ResultSet resultSet) {
        try {
            return new EditoraFactory().getEditora(resultSet.getString("cnpj"), resultSet.getString("nome"));

        } catch(Exception e) {
            throw new RuntimeException("Erro ao extrair o objeto");
        }
    }
}
