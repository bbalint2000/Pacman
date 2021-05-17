package game.view.objects;

import game.model.objects.Wall;
import game.view.Drawable;
import game.view.MazeView;

import java.awt.*;

public class WallView implements Drawable {
    Wall wall;

    public WallView useWall(Wall wall) {
        this.wall = wall;
        return this;
    }

    @Override
    public void draw(Graphics g) {
        if(wall == null)
            return;
        if(wall.isVisible()) {
            g.setColor(Color.BLUE);

            int x = (int) (wall.getX() * MazeView.getCellWidth());
            int y = (int) (wall.getY() * MazeView.getCellWidth());

            g.fillRect(x, y, MazeView.getCellWidth(), MazeView.getCellWidth());

            g.setColor(Color.BLACK);
            g.drawRect(x, y, MazeView.getCellWidth(), MazeView.getCellWidth());
        }
    }
}
