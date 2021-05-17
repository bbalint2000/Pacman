package game.model.objects;

import game.model.Game;

public class Score extends PacDot {

    public Score(double x, double y) {
        super(x, y);
    }

    public void collideWith(PacMan pacMan){
        Game.game.incScore(20);
    }

    @Override
    public boolean isSuper() {
        return false;
    }


}
