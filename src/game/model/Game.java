package game.model;

import game.model.objects.Mover;

import java.io.*;
import java.nio.file.Path;

public class Game implements Stepable, Serializable {

    public enum State {MENU, LOAD, PLAY, PAUSED, GAME_OVER, WIN}
    private State state = State.MENU;

    private int score = 0;
    private double t = 0;
    private double stepTime = 0;

    private final Maze maze = new Maze();

    //Singleton
    public static Game game;

    static {
        try {
            game = new Game();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Game() throws IOException {
        restart();
    }

    @Override // Léptet a pályán mindent, növeli az eltelt időt, ha elfogyott a pacman élete befejezi a játékot
    public void step(){
        if(state == State.PLAY) {
            maze.step();
            t += stepTime;
        }
        if(noMoreLife())
            gameOver();
    }

    public boolean noMoreLife() {
        return getPlayerLife() == 0;
    }

    //betölti a pályát, beállítja az időt és pontot 0-ra
    public void restart() throws IOException {
        t = 0;
        score = 0;
        setState(State.MENU);
        maze.read(Path.of("maze.txt"));
    }

    //Az előző játékállás vissza olvasása
    public void reloadGame() throws IOException, ClassNotFoundException {
        FileInputStream fin = new FileInputStream("previousgame");
        ObjectInputStream oin = new ObjectInputStream(fin);
        game = (Game) oin.readObject();
        Game.game.setState(State.PLAY);
    }

    //A jelenlegi játékállás elmentése
    public void saveGame() throws IOException {
        FileOutputStream fout = new FileOutputStream("previousgame");
        ObjectOutputStream oout = new ObjectOutputStream(fout);
        oout.writeObject(this);
    }

    //Átállítja a pacman mozgási irányát
    public void movePacman(Mover.Orientation orientation){
        if(state == State.PLAY) {
            getMaze().getPacman().setOrientation(orientation);
        }
    }

    public void incScore(int score) {
        this.score += score;
    }

    public int getPlayerScore() {
        return score;
    }

    public int getPlayerLife(){
        return maze.getPacman().getLife();
    }

    public double getTime(){
        return t;
    }

    public void setStepTime(double stepTime) {
        this.stepTime = stepTime;
    }

    public double getStepTime() {
        return stepTime;
    }

    public State getState() {
        return state;
    }

    public Maze getMaze() {
        return maze;
    }

    public void gameOver(){
        state = State.GAME_OVER;
    }

    public void victory(){
        state = State.WIN;
    }

    public void setState(State state) {
        this.state = state;
    }
}
