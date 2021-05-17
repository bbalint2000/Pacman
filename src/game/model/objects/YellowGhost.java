package game.model.objects;

import game.model.Stepable;

public class YellowGhost extends Ghost implements Stepable {
    public YellowGhost(double x, double y) {
        super(x, y);
    }

    @Override
    public Orientation orientationChooser(double distance) {
      return randomOrientation();
    }

    @Override
    public Color getColor() {
        return Color.YELLOW;
    }


}
