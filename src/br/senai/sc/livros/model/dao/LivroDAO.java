package br.senai.sc.livros.model.dao;

import br.senai.sc.livros.model.entities.*;
import br.senai.sc.livros.model.factory.ConexaoFactory;
import br.senai.sc.livros.model.factory.LivroFactory;
import br.senai.sc.livros.model.factory.PessoaFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class LivroDAO {
    private Connection conn;

    public LivroDAO() {
        this.conn = new ConexaoFactory().connectDB();
    }

    public boolean inserir(Livro livro) {
        String sqlCommand = "INSERT INTO LIVROS (isbn, titulo, status, qtdPaginas, autor_cpf) values (?, ?, ?, ?, ?);";
        try (PreparedStatement pstm = conn.prepareStatement(sqlCommand)) {
            pstm.setInt(1, livro.getISBN());
            pstm.setString(2, livro.getTitulo());
            pstm.setInt(3, livro.getStatus().ordinal());
            pstm.setInt(4, livro.getQntdPaginas());
            pstm.setString(5, livro.getAutor().getCPF());
            try {
                pstm.execute();
                return true;
            } catch (Exception e) {
                throw new RuntimeException("Erro na execução do comando SQL");
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro na preparação do comando SQL");
        }
    }

    public void remover(Livro livro) {
        String sqlCommand = "DELETE FROM LIVROS WHERE isbn = ?;";
        try (PreparedStatement pstm = conn.prepareStatement(sqlCommand)) {
            pstm.setInt(1, livro.getISBN());
            try {
                pstm.execute();
            } catch (Exception e) {
                throw new RuntimeException("Erro na execução do comando SQL");
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro na preparação do comando SQL");
        }
    }

    public Livro selecionar(int isbn) {
        String sqlCommand = "SELECT * FROM LIVROS WHERE isbn = ?;";
        try (PreparedStatement pstm = conn.prepareStatement(sqlCommand)) {
            pstm.setInt(1, isbn);
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
        return null;
    }

    public void atualizar(int isbn, Livro livroAtualizado) {
        String sqlCommand = "UPDATE LIVROS SET titulo = ?, status = ?, qtdPaginas = ?, autor_cpf = ? WHERE isbn = ?;";
        try (PreparedStatement pstm = conn.prepareStatement(sqlCommand)) {
            pstm.setString(1, livroAtualizado.getTitulo());
            pstm.setInt(2, livroAtualizado.getStatus().ordinal());
            pstm.setInt(3, livroAtualizado.getQntdPaginas());
            pstm.setString(4, livroAtualizado.getAutor().getCPF());
            pstm.setInt(5, isbn);
            try {
                pstm.execute();
            } catch (Exception e) {
                throw new RuntimeException("Erro na execução do comando SQL");
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro na preparação do comando SQL");
        }
    }

    public Collection<Livro> getAllLivros() {
        String sqlCommand = "SELECT * FROM LIVROS;";
        try (PreparedStatement pstm = conn.prepareStatement(sqlCommand)) {
            try (ResultSet resultSet = pstm.executeQuery()) {
                Collection<Livro> livros = new ArrayList<>();
                while (resultSet.next()) {
                    livros.add(extrairObjeto(resultSet));
                }
                return Collections.unmodifiableCollection(livros);
            } catch (Exception e) {
                throw new RuntimeException("Erro na execução do comando SQL");
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro na preparação do comando SQL");
        }
    }

    public Collection<Livro> selecionarPorAutor(Pessoa pessoa) {
        String sqlCommand = "SELECT * FROM LIVROS WHERE autor_cpf = ?;";
        try (PreparedStatement pstm = conn.prepareStatement(sqlCommand)) {
            pstm.setString(1, pessoa.getCPF());
            try (ResultSet resultSet = pstm.executeQuery()) {
                Collection<Livro> livros = new ArrayList<>();
                while (resultSet.next() && resultSet != null) {
                    livros.add(extrairObjeto(resultSet));
                }
                return Collections.unmodifiableCollection(livros);
            } catch (Exception e) {
                throw new RuntimeException("Erro na execução do comando SQL");
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro na preparação do comando SQL");
        }
    }

    public Collection<Livro> selecionarPorStatus(Status status, Pessoa usuario) {
        String sqlCommand;
        if (usuario instanceof Autor) {
            sqlCommand = "SELECT * FROM LIVROS WHERE status = ? AND autor_cpf = ?;";
        } else {
            sqlCommand = "SELECT * FROM LIVROS WHERE status = ?;";
        }
        try (PreparedStatement pstm = conn.prepareStatement(sqlCommand)) {
            pstm.setInt(1, status.ordinal());
            if (usuario instanceof Autor) {
                pstm.setString(2, usuario.getCPF());
            }
            try (ResultSet resultSet = pstm.executeQuery()) {
                Collection<Livro> livros = new ArrayList<>();
                while (resultSet.next()) {
                    livros.add(extrairObjeto(resultSet));
                }
                return Collections.unmodifiableCollection(livros);
            } catch (Exception e) {
                throw new RuntimeException("Erro na execução do comando SQL");
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro na preparação do comando SQL");
        }
    }

    public Collection<Livro> selecionarAtividadesAutor(Pessoa pessoa) {
        String sqlCommand = "SELECT * FROM LIVROS WHERE status = ? AND autor_cpf = ?;";
        try (PreparedStatement pstm = conn.prepareStatement(sqlCommand)) {
            pstm.setInt(1, Status.AGUARDANDO_EDICAO.ordinal());
            pstm.setString(2, pessoa.getCPF());
            try (ResultSet resultSet = pstm.executeQuery()) {
                Collection<Livro> livros = new ArrayList<>();
                while (resultSet.next()) {
                    livros.add(extrairObjeto(resultSet));
                }
                return Collections.unmodifiableCollection(livros);
            } catch (Exception e) {
                throw new RuntimeException("Erro na execução do comando SQL");
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro na preparação do comando SQL");
        }
    }

    public Collection<Livro> selecionarAtividadesRevisor(Pessoa pessoa) {
        String sqlCommand = "SELECT * FROM livros WHERE status = ? AND revisor_cpf = ?;";
        try (PreparedStatement pstm = conn.prepareStatement(sqlCommand)) {
            pstm.setInt(1, Status.EM_REVISAO.ordinal());
            pstm.setString(2, pessoa.getCPF());
            try (ResultSet resultSet = pstm.executeQuery()) {
                Collection<Livro> livros = new ArrayList<>();
                while (resultSet.next() && resultSet != null) {
                    livros.add(extrairObjeto(resultSet));
                }
                return Collections.unmodifiableCollection(livros);
            } catch (Exception e) {
                throw new RuntimeException("Erro na execução do comando SQL");
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro na preparação do comando SQL");
        }
    }

    public Livro extrairObjeto(ResultSet resultSet) {
        try {
            PessoaDAO pessoaDAO = new PessoaDAO();
            Autor autor = (Autor) pessoaDAO.selecionarPorCPF(resultSet.getString("autor_cpf"));
            Revisor revisor;

            try {
                revisor = (Revisor) pessoaDAO.selecionarPorCPF(resultSet.getString("revisor_cpf"));
            } catch (Exception e) {
                revisor = null;
            }

            EditoraDAO editoraDAO = new EditoraDAO();
            Editora editora;

            try {
                editora = editoraDAO.selecionarPorCNPJ(resultSet.getString("editora_cnpj"));
            } catch (Exception e) {
                editora = null;
            }

            return new LivroFactory().getLivro(
                    resultSet.getInt("isbn"),
                    resultSet.getString("titulo"),
                    Status.values()[resultSet.getInt("status")],
                    resultSet.getInt("qtdPaginas"),
                    autor,
                    editora,
                    revisor
            );
        } catch (Exception e) {
            throw new RuntimeException("Erro ao extrair o objeto");
        }
    }

}
