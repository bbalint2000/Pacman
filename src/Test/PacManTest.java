package Test;

import game.model.Game;
import game.model.objects.Mover;
import game.model.objects.PacMan;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



public class PacManTest {
   private PacMan pacman;

    @Before
    public void setUp() throws Exception {
        pacman = Game.game.getMaze().getPacman();

    }

    @Test //falnak megy neki
    public void minDistance(){
        double speed = 3;
        double distance = speed * Game.game.getStepTime();
        double err = distance/2;
        pacman = new PacMan(1,1);
        pacman.setOrientation(Mover.Orientation.LEFT);
        int min = pacman.minDistance(0.8, 1, err);
        Assert.assertEquals(min, 0);
    }

    @Test //vissza megy a kiindulási helyére
    public void moveBack() {
        int x = (int) pacman.getX();
        int y = (int) pacman.getY();
        int og_x = pacman.getOg_x();
        int og_y = pacman.getOg_y();;
        pacman.moveBack();
        Assert.assertEquals(x, og_x);
        Assert.assertEquals(y, og_y);
    }

    @Test //csökkenti az életet
    public void decreaseLife() {
        int life = pacman.getLife();
        pacman.decreaseLife();
        int result = pacman.getLife();
        Assert.assertEquals(life-1 , result);
    }

    @Test //Lejár a super golyo hatása
    public  void superTimerEnds(){
       boolean result = pacman.superTimerEnds();
       Assert.assertNotEquals(result, true);
    }

    @Test // kis golyónak megy neki
    public void collideWithPacdot_score(){
        pacman = new PacMan(4,1);
        pacman.setOrientation(Mover.Orientation.LEFT);
        int score = Game.game.getPlayerScore();
        pacman.collideWithPacdot();
        int result = Game.game.getPlayerScore();
        Assert.assertEquals(score + 20, result);
    }

}