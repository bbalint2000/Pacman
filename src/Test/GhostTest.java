package Test;

import game.model.Game;
import game.model.objects.Mover;
import game.model.objects.Ghost;
import org.junit.Assert;
import org.junit.Test;



public class GhostTest {
    private Ghost ghost;

    @Test
    public void randomOrientation() {
         ghost = Game.game.getMaze().getGhosts().get(1);
        Mover.Orientation orientation = ghost.randomOrientation();
        Assert.assertNotNull(orientation);
    }

    @Test
    public void orientationChooser(){
        ghost = Game.game.getMaze().getGhosts().get(1);
        Mover.Orientation or = ghost.randomOrientation();
        Assert.assertNotNull(or);
    }

    @Test //szellem vissza megy a kiindulási helyére
    public void moveBack(){
        ghost = Game.game.getMaze().getGhosts().get(1);
        int og_x = (int) ghost.getX();
        int og_y = (int) ghost.getY();
        ghost.step();
        ghost.step();
        ghost.step();
        ghost.step();
        ghost.step();
        double x1 =  ghost.getX();
        double y1 =  ghost.getY();
        ghost.moveBack();
        double x2 = ghost.getX();
        double y2 = ghost.getY();
        Assert.assertNotEquals(x1, og_x);
        Assert.assertNotEquals(y1, og_y);
        Assert.assertEquals(x2, og_x,0);
        Assert.assertEquals(y2, og_y,0);

    }
}
