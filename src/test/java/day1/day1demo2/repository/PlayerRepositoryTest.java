package day1.day1demo2.repository;

import day1.day1demo2.entity.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PlayerRepositoryTest {

    @Autowired
    PlayerRepository playerRepository;
    boolean isIntialized = false;

    @BeforeEach
    void setUp() {
        if(!isIntialized){
            playerRepository.deleteAll();
            playerRepository.save(new Player("Cristiano Ronaldo", "Portugal", "Forward"));
            playerRepository.save(new Player("Lionel Messi", "Argentina", "Forward"));
            isIntialized = true;
        }
    }

    @Test
    public void deleteAll(){
        playerRepository.deleteAll();
        assertEquals(0, playerRepository.count());
    }

    @Test
    public void testAll(){
        Long count = playerRepository.count();
        assertEquals(2, count);
    }

    @Test
    public void testFindByName(){
        Player p1 = playerRepository.findByName("Cristiano Ronaldo");
        assertEquals("Cristiano Ronaldo", p1.getName());
    }

    @Test
    public void testFindByNameLike(){
        Player p1 = playerRepository.findByNameLike("%Ronaldo%");
        assertEquals("Cristiano Ronaldo", p1.getName());
    }

}