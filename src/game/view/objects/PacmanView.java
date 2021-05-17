package game.view.objects;

import game.model.objects.PacMan;
import game.view.Drawable;
import game.view.MazeView;

import java.awt.*;

public class PacmanView implements Drawable {
    private PacMan pacman = null;

    public PacmanView usePacman(PacMan pacman) {
        this.pacman = pacman;
        return this;
    }

    @Override
    public void draw(Graphics g) {
        if(pacman == null)
            return;

        g.setColor(Color.YELLOW);

        int mounthMaxDeg = 60;
        int deg    = (int) (mounthMaxDeg * pacman.getMouthOpenness());
        int center = pacman.getOrientation().ordinal()*270/3;

        int size = MazeView.getCellWidth();

        int x = (int)((pacman.getX() + 0.1) * size);
        int y = (int)((pacman.getY() + 0.1) * size);

        g.fillArc(x, y, (int)(size*0.9), (int)(size*0.9), center + deg/2, 360 - deg);

        g.setColor(Color.black);
        g.drawArc(x, y, (int)(size*0.9), (int)(size*0.9), center + deg/2, 360 - deg);

    }
}


