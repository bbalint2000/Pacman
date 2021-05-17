package game.model.objects;

import game.model.*;

import java.util.ArrayList;
import java.util.Random;

public  abstract class  Ghost extends  Mover implements Stepable {
    public Ghost(double x, double y) {
        super(x,y);
        og_x = (int)x;
        og_y = (int)y;
    }

    int og_x, og_y;

    public enum Color{
        PINK, YELLOW, CYAN, RED
    }

    public int minDistance(double dest_x, double dest_y, double err){
        ArrayList<Wall> walls = Game.game.getMaze().getWalls();
        return walls.stream()
                .filter(wall->wall.isVisible())
                .mapToInt(wall ->(int) (Math.abs(wall.getX() - dest_x) + err) + (int) (Math.abs(wall.getY() - dest_y) + err))
                .min().orElse(0);
    }

    public abstract Orientation orientationChooser(double distance);

    public Orientation randomOrientation(){
        int pick = new Random().nextInt(Orientation.values().length);
        return Orientation.values()[pick];
    }

    public Orientation pacmanFollow(){
        int rand = (int) (Math.random()*2);
        PacMan pac =  Game.game.getMaze().getPacman();

        if(pac.getX() < x && pac.getY() < y) {
            if(rand == 0)
                return Orientation.UP;
            else return Orientation.LEFT;
        }

        if(pac.getX() > x && pac.getY() > y) {
            if(rand == 0)
                return Orientation.DOWN;
            else return Orientation.RIGHT;
        }

        if(pac.getX() < x && pac.getY() > y) {
            if(rand == 0)
                return Orientation.DOWN;
            else return Orientation.LEFT;
        }

        if(pac.getX() > x && pac.getY() < y) {
            if(rand == 0)
                return Orientation.UP;
            else return Orientation.RIGHT;
        }
        return randomOrientation();
    }

    @Override
    public void step() {

        dest_x = x;
        dest_y = y;
        dest_next_x = x;
        dest_next_y = y;
        double speed;

        Maze maze = Game.game.getMaze();
        PacMan pacman = maze.getPacman();

        if (pacman.isPower())
            speed = 2;
        else speed = 4;
        double distance = speed * Game.game.getStepTime();

        mover(distance);
        if ((minDistance((dest_x), dest_y, distance / 2) >= 1)) {
            if(crossRoad())
                next_orientation = orientationChooser(distance);
            x = dest_x;
            y = dest_y;

        } else next_orientation = orientationChooser(distance);
    }

    public boolean crossRoad(){
        ArrayList<Wall> walls = Game.game.getMaze().getWalls();
        int min = (int) walls.stream()
                .mapToInt(wall -> (int) (Math.abs(wall.getX() - x)) + (int) (Math.abs(wall.getY() - y)))
                .filter(d -> d == 1 ).count();
        return min == 0;
    }

    public abstract Color getColor();

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public void moveBack(){
        x = og_x;
        y = og_y;
    }
}
