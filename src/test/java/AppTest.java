import com.kharizma.tennisscoreboard.models.Match;
import com.kharizma.tennisscoreboard.models.Player;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AppTest {
    static EntityManager entityManager;

    @BeforeEach
    public void initializeEMF() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TennisScoreboard_PU");
        entityManager = emf.createEntityManager();
    }

    @AfterAll
    public static void closeEMF() {
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
    
    @Test
    public void addMatch() {
        entityManager.getTransaction().begin();

        Player s1 = new Player();
        s1.setName("Dima");

        Player s2 = new Player();
        s2.setName("Paulina");

        entityManager.persist(s1);
        entityManager.persist(s2);
        
        Match match1= new Match();
        match1.setPlayerOne(s1);
        match1.setPlayerTwo(s2);
        match1.setWinner(s1);
        
        entityManager.getTransaction().commit();

        List<Match> matches = entityManager.createQuery("from Match ").getResultList();
        matches.forEach(match ->
                System.out.println(
                        match.getId() + ", " +
                        match.getPlayerOne() + ", " +
                        match.getPlayerTwo() + ", " +
                        match.getWinner()));
    }
}
