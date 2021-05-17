package game.model.objects;

import game.model.Stepable;

public class CyanGhost extends Ghost implements Stepable {

    public CyanGhost(double x, double y) {
        super(x, y);
    }

    @Override
    public Orientation orientationChooser( double distance) {
        return randomOrientation();
    }

    @Override
    public Color getColor() {
        return Color.CYAN;
    }


}
