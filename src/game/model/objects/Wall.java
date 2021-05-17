package game.model.objects;

import game.model.GameObject;

public class Wall extends GameObject {
    boolean visible;
    public Wall(int x, int y, boolean visible) {
        super(x, y);
         this.visible = visible;
    }

    public boolean isVisible(){
        return this.visible;
    }

}
