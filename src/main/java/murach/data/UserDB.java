package murach.data;

import jakarta.persistence.*;
import murach.business.User;

public class UserDB {
    private static final EntityManagerFactory emf = 
            Persistence.createEntityManagerFactory("emailListPU");

    public static void insert(User user) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(user);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }
}