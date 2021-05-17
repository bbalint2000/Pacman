package game;

import game.model.Game;
import game.model.Maze;
import game.view.GameView;
import game.view.MazeView;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ConcurrentModificationException;

public class GamePanel extends JPanel {
   private BufferedImage frame = null;
   private GameView gameView = new GameView();

    public GamePanel(){
        Maze maze = Game.game.getMaze();

        this.setPreferredSize(new Dimension(
                maze.getWidth() * MazeView.getCellWidth(),
                maze.getHeight() *  MazeView.getCellWidth()
        ));
    }

    @Override
    public void paintComponent(Graphics g) throws ConcurrentModificationException {
        super.paintComponent(g);

        if(Game.game == null)
            return;

        if(frame == null)
            frame = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);

        Graphics2D frame_g = (Graphics2D) frame.getGraphics();
        frame_g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        gameView.draw(frame_g);

        g.drawImage(frame, 0, 0, this);
    }
}

