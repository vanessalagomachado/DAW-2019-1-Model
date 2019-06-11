/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes.junit;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Usuario;
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
public class TestePersistirIUsuario {
    
    EntityManager em;
    
    public TestePersistirIUsuario() {
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
            Usuario u = new Usuario();
            u.setNomeUsuario("vivente01");
            u.setSenha("123");
            
            u.setRg("1231231231");
            u.setCpf("20120925001");
            
            SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yy");
            Date dt = formatoData.parse("01/01/90");
            Calendar nasc = Calendar.getInstance();
            nasc.setTime(dt);
            u.setNascimento(nasc);
            //u.setNascimento(Calendar.getInstance());
            
            u.setNome("Vivente IFSUL");
            
            em.getTransaction().begin();
            em.persist(u);
            em.getTransaction().commit();
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("--\nERRO\n"+e);
        }
    }
}
