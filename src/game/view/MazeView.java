package game.view;

import game.model.Maze;
import game.view.objects.GhostView;
import game.view.objects.PacmanView;
import game.view.objects.PacDotView;
import game.view.objects.WallView;
import java.awt.*;
import java.util.ConcurrentModificationException;

public class MazeView implements Drawable {
    private static final int cellWidth = 27;
    private Maze maze = null;

    private final PacmanView pacmanView = new PacmanView();
    private final WallView wallView = new WallView();
    private final PacDotView pacDotView = new PacDotView();
    private final GhostView ghostView = new GhostView();

    public MazeView useMaze(Maze maze) {
        this.maze = maze;
        return this;
    }

    public static int getCellWidth() {
        return cellWidth;
    }

    @Override
    public void draw(Graphics g) throws ConcurrentModificationException {
        g.setColor(Color.BLACK);
        g.fillRect(0,0, maze.getWidth()*cellWidth, maze.getHeight()*cellWidth);

        //pacman kirajzolása
        pacmanView
                .usePacman(maze.getPacman())
                .draw(g);

        //falak kirajzolása
       maze
                .getWalls()
                .forEach(wall -> wallView.useWall(wall).draw(g));

        //pontok kirajzolása
        maze
                .getPacDots()
                .forEach(pacDot -> pacDotView.usePacdot(pacDot).draw(g));

        //szellemek kirajzolása
        maze
                .getGhosts()
                .forEach(ghost -> ghostView.useGhost(ghost).draw(g));
    }
}
