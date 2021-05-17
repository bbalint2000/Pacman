package game;

import game.model.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import static game.model.Game.State.*;
import static game.model.objects.Mover.Orientation.*;
import static java.awt.event.KeyEvent.*;


public class GameFrame extends JFrame implements KeyListener{
    private static final int fps = 60;

    public static void main(String[] args) throws IOException {
        GameFrame gameFrame = new GameFrame();
        startGame(gameFrame);
        gameFrame.setIconImage(ImageIO.read(new File("images/icon.png")));
    }


    private GameFrame() {
        GamePanel gamePanel = new GamePanel();

        add(gamePanel);
        addKeyListener(this);
        setResizable(false);
        setVisible(true);
        pack();
    }

    private static void startGame(GameFrame gameFrame){
        int frame_time = 1000/fps;
        Game.game.setStepTime(frame_time/1000.0);

        java.util.Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
            if(Game.game == null)
                return;

            int life = Game.game.getPlayerLife();
            int score = Game.game.getPlayerScore();

            gameFrame.setTitle("Score: " + score + ", life: "+life + "        Press 'space' for pause!");

            gameFrame.repaint();
                Game.game.step();
            }
        }, 0, frame_time);
    }

    private static void endGame(GameFrame gameFrame){
        gameFrame.dispatchEvent(new WindowEvent(gameFrame, WindowEvent.WINDOW_CLOSING));
        System.exit(0);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Game.State state = Game.game.getState();
        switch (state) {
            case MENU -> inMenu(e);
            case PLAY -> inPlay(e);
            case WIN, GAME_OVER -> inEnd(e);
            case PAUSED -> inPause(e);
            case LOAD -> inLoad();
        }
    }

    private void inMenu( KeyEvent e){
        switch (e.getKeyCode()) {
            case VK_ESCAPE -> endGame(this);
            case VK_ENTER -> Game.game.setState(PLAY);
            case VK_R -> Game.game.setState(LOAD);
        }
    }

    private void inPlay(KeyEvent e){
        switch (e.getKeyCode()) {
            case VK_RIGHT -> Game.game.movePacman(RIGHT);
            case VK_UP -> Game.game.movePacman(UP);
            case VK_LEFT -> Game.game.movePacman(LEFT);
            case VK_DOWN -> Game.game.movePacman(DOWN);
            case VK_SPACE -> Game.game.setState(PAUSED);
        }
    }

    private void inEnd(KeyEvent e){
        if (e.getKeyCode() == VK_ENTER) {
            try {
                Game.game.restart();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    private void inPause(KeyEvent e){
        switch (e.getKeyCode()) {
            case VK_SPACE -> Game.game.setState(PLAY);
            case VK_ESCAPE -> endGame(this);
            case VK_S -> {
                try {
                    Game.game.saveGame();

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
    }

    private   void inLoad() {
        try {
            Game.game.reloadGame();
        } catch (IOException | ClassNotFoundException ioException) {
            ioException.printStackTrace();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

}
