package game.model.objects;

import game.model.Game;
import game.model.Stepable;
import java.util.ArrayList;

public class PacMan extends Mover implements Stepable {

    public PacMan(double x, double y) {
        super(x, y);
        og_x = (int) x;
        og_y = (int) y;
    }

    private boolean power = false;
    private int life = 3;

    int og_x, og_y;
    double powertime = 0;

    public void step() {
         dest_x = x;
         dest_y = y;
         dest_next_x = x;
         dest_next_y = y;
        double speed = 3;
        double distance = speed * Game.game.getStepTime();

        mover(distance );

        if (minDistance((dest_x), dest_y, distance / 2) >= 1 ) {
                x = dest_x;
                y = dest_y;
            }

        collideWithPacdot();
        collideWithGhost();

        if(power)
           if(superTimerEnds())
               power = false;
    }

    public int minDistance(double dest_x, double dest_y, double err){
        ArrayList<Wall> walls = Game.game.getMaze().getWalls();
        return walls.stream()
                .mapToInt(wall ->(int) (Math.abs(wall.getX() - dest_x) + err) + (int) (Math.abs(wall.getY() - dest_y) + err))
                .min().orElse(0);
    }

    public boolean superTimerEnds() {
        return (int) Game.game.getTime() == (int) powertime + 15;
    }

    public void  collideWithGhost() {
        ArrayList<Ghost> ghosts = Game.game.getMaze().getGhosts();
        for(int i=0; i<ghosts.size(); i++){
            Ghost ghost = ghosts.get(i);

            if(Math.abs(ghost.getX() - this.getX()) <0.5 && Math.abs(ghost.getY() - this.getY())<0.5){
                if(power) {
                    ghost.moveBack();
                    Game.game.incScore(50);
                } else {
                    decreaseLife();
                    moveBack();
                }
            }
        }
    }

    public void collideWithPacdot() {
        ArrayList<PacDot> pacdots = Game.game.getMaze().getPacDots();
        for(int i=0; i<pacdots.size(); i++){
            PacDot pacdot = pacdots.get(i);
            if (Math.abs(pacdot.getX() - this.getX()) < 0.4 && Math.abs(pacdot.getY() - this.getY()) < 0.4) {
                pacdot.collideWith(this);
                pacdots.remove(pacdot);
            }
        }
    }

    public void moveBack(){
        x = og_x;
        y = og_y;
    }

    public boolean isPower() {
        return power;
    }

    public int getLife(){
        return life;
    }

    public void decreaseLife() {
        life--;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.next_orientation = orientation; //De ez a next_orientation, és nem az orientation, szóval igazam volt
    }

    public double getMouthOpenness(){
        return Math.cos(Game.game.getTime()*10) * 0.5 + 0.5;
    }

    public void setPower(boolean power) {
        this.power = power;
        this.powertime = Game.game.getTime();
    }

    public int getOg_x() {
        return og_x;
    }

    public int getOg_y() {
        return og_y;
    }



}
