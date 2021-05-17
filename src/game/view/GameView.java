package game.view;

import game.model.Game;
import game.model.Maze;
import java.awt.*;
import java.util.ConcurrentModificationException;

import static game.model.Game.State.PLAY;

public class GameView implements Drawable{
    private final MazeView mazeView = new MazeView();

    @Override
    public void draw(Graphics g) throws ConcurrentModificationException {
        Maze maze = Game.game.getMaze();
        Game.State state = Game.game.getState();

        int w = maze.getWidth()*MazeView.getCellWidth();
        int h = maze.getHeight()*MazeView.getCellWidth();

        g.setColor(Color.BLACK);
        g.fillRect(0,0,w,h);

        mazeView.useMaze(maze).draw(g);

        if(state != PLAY){
            switch (state){
                case MENU -> drawMenu(g);
                case GAME_OVER -> drawState(g, "Game over, you lost :(", "Press 'enter' to go to the Menu!", "Your score: "+ Game.game.getPlayerScore());
                case WIN       -> drawState(g,"Congratulations, you won :)", "Press 'enter' to go to the Menu!", "Your score: "+ Game.game.getPlayerScore());
                case PAUSED    ->drawState(g, "Game pauesed, press 'space' to continue!" ,"Press's' to save game!", "Press 'esc' to exit game!");
                case LOAD -> drawState(g, "Press 'enter' to start", "", "");
            }
        }
    }

    private void drawState(Graphics g, String str, String str2, String str3){
        Maze maze = Game.game.getMaze();
        int w = maze.getWidth()*MazeView.getCellWidth();
        int h = maze.getHeight()*MazeView.getCellWidth();

        Font font = new Font("Arial", Font.BOLD, 20);
        FontMetrics metrics = g.getFontMetrics(font);
        g.setFont(font);

        int x = w / 2 - metrics.stringWidth(str) / 2;
        int y = h / 3 + 40;

        g.setColor(new Color(0, 0, 0, (float) 0.8));
        g.fillRect(0, 0, w, h);
        g.setColor(Color.WHITE);
        g.drawString(str, x, y);

         x = w / 2 - metrics.stringWidth(str2) / 2;
         y = h / 2 ;
         g.drawString(str2, x, y);

        x = w / 2 - metrics.stringWidth(str3) / 2;
        y = h / 2 +80;
        g.drawString(str3, x, y);

    }

    public void drawMenu(Graphics g){
        Maze maze = Game.game.getMaze();
        int w = maze.getWidth()*MazeView.getCellWidth();
        int h = maze.getHeight()*MazeView.getCellWidth();

        Font font = new Font("TimesRoman", Font.PLAIN, 45);
        Font font2 = new Font("Helvetica", Font.PLAIN, 30);
        FontMetrics metrics = g.getFontMetrics(font);
        FontMetrics metrics2 = g.getFontMetrics(font2);

        Toolkit t=Toolkit.getDefaultToolkit();
        Image i = t.getImage("images/menu1.jpg");
        g.drawImage(i, 0,0,w, h, null);

        String message = "Menu";
        int x = w / 2 - metrics.stringWidth(message) / 2;
        int y = (h / 2);
        g.setFont(font);
        g.setColor(Color.white);
        g.drawString(message, x, y);

        g.setFont(font2);
        g.setColor(Color.YELLOW);

        message = "Press 'enter' to start a game!";
        x = w / 2- metrics2.stringWidth(message) / 2;
        y = (int)(h / 1.7);
        g.drawString(message,x,y );

        message = "Press 'r' to reload a previous game!";
        x = (w / 2 - metrics2.stringWidth(message)/2);
        y = y+80;
        g.drawString(message, x, y );

        message = "Press 'esc' to exit the game!";
        x = (w / 2 - metrics2.stringWidth(message)/2);
        y = y+80;
        g.drawString(message, x, y );
    }
}
