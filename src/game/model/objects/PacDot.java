package game.model.objects;

import game.model.GameObject;

public abstract class PacDot extends GameObject {
    public PacDot(double x, double y) {
        super(x, y);
    }

    public abstract void collideWith(PacMan pacMan);

    public abstract boolean isSuper();


}

