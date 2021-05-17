package game.view.objects;

import game.model.Game;

import game.model.Maze;
import game.model.objects.*;
import game.view.Drawable;
import game.view.MazeView;

import java.awt.*;

public class GhostView implements Drawable {
    private Ghost ghost;

    public GhostView useGhost(Ghost ghost) {
        this.ghost = ghost;
        return this;
    }

    @Override
    public void draw(Graphics g) {
        Toolkit t=Toolkit.getDefaultToolkit();
        Image i;

        Maze maze = Game.game.getMaze();
        PacMan pacman = maze.getPacman();

        if(!pacman.isPower()) {
           i = t.getImage( "images/" + ghost.getColor().toString().toLowerCase()+".jpg");

        } else{
                i = t.getImage("images/blue.jpg");
        }

        int size = MazeView.getCellWidth();
        int x = (int)((ghost.getX() ) * size);
        int y = (int)((ghost.getY() ) * size);
        g.setColor(Color.CYAN);
        g.drawImage(i, x, y, size, size, null);

    }
}
