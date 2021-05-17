package game.model.objects;


public class Super extends PacDot{
    public Super(double x, double y) {
        super(x, y);
    }

    @Override
    public void collideWith(PacMan pacMan) {
        pacMan.setPower(true);
    }

    @Override
    public boolean isSuper() {
        return true;
    }


}
