/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes.junit;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.PessoaJuridica;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author vanes
 */
public class TestePersistirPJ {
    
    EntityManager em;
    
    public TestePersistirPJ() {
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
            PessoaJuridica pj = new PessoaJuridica();
            pj.setNome("Casa da informática");
            pj.setCnpj("99.128.079/0001-06");
            pj.setIe("12312312311");
            
            em.getTransaction().begin();
            em.persist(pj);
            em.getTransaction().commit();
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro: "+e);
        }
    }
    
}
