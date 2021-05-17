package game.model.objects;

import game.model.GameObject;

public abstract class Mover extends GameObject {

    public Mover(double x, double y) {
        super(x, y);
    }

    public enum Orientation {
        RIGHT, UP, LEFT, DOWN
    }
    protected Orientation orientation = Orientation.LEFT;
    protected Orientation next_orientation = Orientation.LEFT;
    protected double dest_x = x;
    protected double dest_y = y;
    protected double dest_next_x = x;
    protected double dest_next_y = y;

    public void mover(double distance){
        switch(orientation){
            case UP    -> dest_y -= distance;
            case DOWN  -> dest_y += distance;
            case LEFT  -> dest_x -= distance;
            case RIGHT -> dest_x += distance;
        }

        switch(next_orientation){
            case UP    -> dest_next_y -= distance;
            case LEFT  -> dest_next_x -= distance;
            case DOWN  -> dest_next_y += distance;
            case RIGHT -> dest_next_x += distance;
        }

        if(minDistance(dest_next_x, dest_next_y, distance/2) >= 1){
            dest_x = dest_next_x;
            dest_y = dest_next_y;
            orientation = next_orientation;
        }

        switch (orientation){
            case UP, DOWN -> dest_x = (int)Math.round(dest_x);
            case LEFT, RIGHT -> dest_y = (int)Math.round(dest_y);
        }
    }

    public abstract int minDistance(double dest_x, double dest_y, double err);
}
