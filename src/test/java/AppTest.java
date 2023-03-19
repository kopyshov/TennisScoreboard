import com.kharizma.tennisscoreboard.models.Player;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AppTest {
    EntityManagerFactory emf;
    EntityManager entityManager;

    @BeforeEach
    public void initializeEMF() {
        emf = Persistence.createEntityManagerFactory("Player_PU");
        entityManager = emf.createEntityManager();
    }

    @AfterEach
    public void closeEMF() {
        entityManager.close();
    }

    @Test
    public void addSomePlayersAndShow() {

        entityManager.getTransaction().begin();

        Player s1 = new Player();
        s1.setName("Dima");

        Player s2 = new Player();
        s2.setName("Paulina");

        entityManager.persist(s1);
        entityManager.persist(s2);

        entityManager.getTransaction().commit();

        List<Player> list = entityManager.createQuery("from Player ").getResultList();
        list.forEach(p -> System.out.println(p.getName()));


    }
}
