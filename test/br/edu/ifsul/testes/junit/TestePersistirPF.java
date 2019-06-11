/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes.junit;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Cidade;
import br.edu.ifsul.modelo.Endereco;
import br.edu.ifsul.modelo.PessoaFisica;
import br.edu.ifsul.modelo.Produto;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author vanes
 */
public class TestePersistirPF {
    
    EntityManager em;
    
    public TestePersistirPF() {
    }
    
    @Before
    public void setUp() {
        em = EntityManagerUtil.getEntityManager();
    }
    
    @After
    public void tearDown() {
        em.close();
    }
    
    @Test
    public void teste(){
        try {
            PessoaFisica p = new PessoaFisica();
            p.setNome("Vivente 01");
            p.setCpf("007.079.140-60");
            p.setRg("1231231231");
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dtN = sdf.parse("12/11/2000");
            Calendar dtC = Calendar.getInstance();
            dtC.setTime(dtN);
            
            p.setNascimento(dtC);
            
            //Endereço
            Endereco e = new Endereco();
            e.setNickname("apto");
            e.setEndereco("Av. Brasil");
            e.setNumero("1000");
            e.setComplemento("apto 100");
            e.setCidade(em.find(Cidade.class, 1));
            
            p.adicionarEndereco(e);
            
            Produto prod = new Produto();
            prod.setNome("Pendrive 1TB");
            prod.setPreco(120.00);
            prod.setQuantidadeEstoque(2.0);
            prod.setDescricao("Pendrive Kingston de 1TB");
            
            p.adicionarProdutoDesejo(prod);
            
            em.getTransaction().begin();
            em.persist(p);
            em.persist(e);
            em.persist(prod);
            em.getTransaction().commit();
                    
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro: "+e);
        }
    }
    
}
