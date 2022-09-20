import br.senai.sc.livros.model.dao.LivroDAO;
import br.senai.sc.livros.model.dao.PessoaDAO;
import br.senai.sc.livros.model.entities.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

public class Teste {
    static String[] lista = {"Resp1", "Resp2", "Resp3"};

    private static Collection<Livro> listaLivros = new HashSet<>();

    public static void main(String[] args) {
//        String CPF = "123456";
//        int hash = 0;
//        for(char l : CPF.toCharArray()){
//            hash += l;
//        }
//        System.out.println(hash);
        LivroDAO livroDao = new LivroDAO();
        livroDao.getAllLivros().add(new Livro());
    }
}
