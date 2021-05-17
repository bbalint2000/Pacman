package Test;

import game.model.Game;
import game.model.Maze;
import game.model.objects.PacDot;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;


public class MazeTest {
    private Maze maze;

    @Before
    public void setUp() throws Exception {
       maze = Game.game.getMaze();
    }

    @Test (expected = IOException.class)
    public void read() throws IOException{
        Path path1 = Path.of("Nemlétezik");
        Game.game.getMaze().read(path1);
    }

    @Test
    public void noMorePacdots(){
        boolean result = true;
        ArrayList<PacDot> pacdots = Game.game.getMaze().getPacDots();
        if(pacdots.size() != 0)
         result = maze.noMorePacdots();
        Assert.assertNotEquals(result, true);
    }


}