package game.model;


import java.io.Serializable;

public abstract class GameObject implements Serializable {
    protected double x, y;

    public GameObject(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

}
