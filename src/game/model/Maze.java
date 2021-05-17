package game.model;

import game.model.objects.*;


import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Maze implements Stepable, Serializable {

    private PacMan pacman;
    private final ArrayList<Ghost> ghosts = new ArrayList();
    private final ArrayList<Wall> walls = new ArrayList();
    private final ArrayList<PacDot> pacdots = new ArrayList();

    private int w, h;

    //Beolvassa a pályát txt filebol
    public void read(Path path) throws IOException {
        var lines = Files.readAllLines(path);
        ghosts.clear();
        pacdots.clear();
        walls.clear();

        h = lines.size();
        w = lines.stream().mapToInt(String -> String.length()).max().getAsInt();

        for(int y=0; y<h; y++)
            for(int x=0; x<lines.get(y).length(); x++) {
                switch (lines.get(y).charAt(x)) {
                    case '#' -> walls.add(new Wall(x, y, true));
                    case '-' -> walls.add(new Wall(x, y, false));
                    case '.' -> pacdots.add(new Score(x, y));
                    case 'o' -> pacdots.add(new Super(x, y));
                    case 'G' -> ghosts.add(new PinkGhost(x, y));
                    case 'R' -> ghosts.add(new RedGhost(x, y));
                    case 'C' -> ghosts.add(new CyanGhost(x, y));
                    case 'Y' -> ghosts.add(new YellowGhost(x, y));
                    case 'P' -> pacman = new PacMan(x, y);
                }
            }
    }

    @Override // Lépteti a Pacmant és a szellemeket, nézi hogy van e még pacdot, ha nincs akkor nyert a jatekos
    public void step() {
        pacman.step();
        ghosts.forEach(ghost->ghost.step());

        if(noMorePacdots())
            Game.game.victory();
    }

    public boolean noMorePacdots() {
        return  pacdots.size() == 0;
    }

    public int getWidth() {
        return w;
    }

    public int getHeight() {
        return h;
    }

    public PacMan getPacman() {
        return pacman;
    }

    public ArrayList<Ghost> getGhosts() {
        return ghosts;
    }

    public ArrayList<Wall> getWalls() {
        return walls;
    }

    public ArrayList<PacDot> getPacDots() {
        return pacdots;
    }
}
