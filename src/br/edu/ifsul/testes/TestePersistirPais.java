package br.edu.ifsul.testes;

import br.edu.ifsul.modelo.Pais;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
public class TestePersistirPais {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManagerFactory emf = 
                Persistence.createEntityManagerFactory("DAW-2019-1-ModelPU");
        EntityManager em = emf.createEntityManager();
        Pais p = new Pais();
        p.setNome("Brasil");
        p.setIso("BRA");
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
    }

}
