package ar.unrn.tp.jpa.servicios;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.function.Consumer;

public abstract class GenericServiceImpl {
    protected EntityManagerFactory emf;

    public GenericServiceImpl(){
        this.emf = Persistence.createEntityManagerFactory("objectdb:myDbTestFile.tmp;drop");
    }
    private void setUp(){
        //this.emf = Persistence.createEntityManagerFactory("objectdb:myDbTestFile.tmp");
    }

    public void inTransactionExecute(Consumer<EntityManager> bloqueDeCodigo) {
        this.setUp();
        EntityManager em = this.emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            bloqueDeCodigo.accept(em);
            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            throw e;
        } finally {
            if (em != null && em.isOpen())
                em.close();
        }
    }
    public void tearDown() {
        this.emf.close();
    }
}
