package game.view.objects;

import game.model.objects.PacDot;
import game.view.Drawable;
import game.view.MazeView;

import java.awt.*;

public class PacDotView implements Drawable {
    private PacDot pacdot = null;

    public PacDotView usePacdot(PacDot pacdot) {
        this.pacdot = pacdot;
        return this;
    }

    @Override
    public void draw(Graphics g) {
        int size = MazeView.getCellWidth();
        if(pacdot == null)
            return;
        double sizecomp= 0;

        if(!pacdot.isSuper())
            sizecomp = 0.4;

        if(pacdot.isSuper())
            sizecomp = 0.6;

        int x = (int) ((pacdot.getX() + 0.8-sizecomp) * size);
        int y = (int) ((pacdot.getY() + 0.8-sizecomp) * size);

        g.setColor(Color.YELLOW);
        g.fillOval(x, y, (int) (sizecomp * size), (int) (sizecomp * size));

    }
}
