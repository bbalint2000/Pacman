package game.model.objects;
import game.model.Game;
import game.model.Stepable;


public class RedGhost extends Ghost implements Stepable {
    public RedGhost(double x, double y) {
        super(x, y);
    }

    @Override
    public Orientation orientationChooser(double distance) {
        return pacmanFollow();
    }

    @Override
    public Color getColor() {
        return Color.RED;
    }
}





