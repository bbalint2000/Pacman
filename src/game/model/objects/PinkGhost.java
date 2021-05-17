package game.model.objects;
import game.model.Game;
import game.model.Stepable;


public class PinkGhost extends Ghost implements Stepable {
    public PinkGhost(double x, double y) {
        super(x, y);
    }

    @Override
    public Orientation orientationChooser(double distance) {
        return pacmanFollow();
    }

    @Override
    public Color getColor() {
        return Color.PINK;
    }


}

