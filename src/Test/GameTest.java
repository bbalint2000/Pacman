package Test;

import game.model.Game;
import game.model.objects.PacMan;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.nio.file.Path;

public class GameTest {
    private PacMan pacman;

    @Before
    public void setUp() throws Exception {
        pacman = Game.game.getMaze().getPacman();
    }

    @Test
    public void incScore() {
        int score1 = Game.game.getPlayerScore();
         Game.game.incScore(20);
         int score2 = Game.game.getPlayerScore();
         Assert.assertEquals(score1 + 20, score2 );
    }

    @Test
    public void noMoreLife(){
        boolean result = true;
        int lifes = pacman.getLife();
        if(lifes != 0)
            result = Game.game.noMoreLife();
        Assert.assertNotEquals(result, true);
    }
}