package net.chrisparton.sparkled.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.function.Function;

public class PersistenceService {

    private static volatile PersistenceService instance;

    private final EntityManagerFactory entityManagerFactory;

    private PersistenceService() {
        entityManagerFactory = Persistence.createEntityManagerFactory("sparkled");
    }

    public static PersistenceService instance() {
        if (instance == null) {
            synchronized (PersistenceService.class) {
                instance = new PersistenceService();
            }
        }

        return instance;
    }

    public <R> R perform(Function<EntityManager, R> function) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            R result = function.apply(entityManager);
            entityManager.getTransaction().commit();
            return result;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            return null;
        } finally {
            entityManager.close();
        }
    }

    public void shutdown() {
        entityManagerFactory.close();
    }
}
