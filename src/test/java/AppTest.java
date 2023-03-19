import com.kharizma.tennisscoreboard.models.Player;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AppTest {
    @Test
    public void JpaExample() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Player_PU");

        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        Player s1 = new Player();
        s1.setName("Dima");

        Player s2 = new Player();
        s2.setName("Paulina");

        entityManager.persist(s1);
        entityManager.persist(s2);

        entityManager.getTransaction().commit();

        entityManager = emf.createEntityManager();


        List<Player> list = entityManager.createQuery("from Player ").getResultList();
        list.forEach(p -> System.out.println(p.getName()));

        entityManager.close();
    }
}
