package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controller extends KeyAdapter {

    Model model;
    View view;
    private static final int WINNING_TILE = 2048;

    public Controller(Model model) {
        this.model = model;
        this.view = new View(this);
    }

    public Tile[][] getGameTiles(){
        return model.getGameTiles();
    }

    public int getScore(){
        return model.score;
    }

    public View getView() {
        return view;
    }

    public void resetGame(){
        model.score = 0;
        view.isGameLost = false;
        view.isGameWon = false;
        model.resetGameTiles();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            resetGame();
        }
        if(!model.canMove()){view.isGameLost = true;}
        if(view.isGameLost == false && view.isGameWon == false){
            switch (e.getKeyCode()){
                case KeyEvent.VK_LEFT : model.left();break;
                case KeyEvent.VK_RIGHT : model.right();break;
                case KeyEvent.VK_UP : model.up();break;
                case KeyEvent.VK_DOWN : model.down();break;
            }
        }
        if(model.maxTile == WINNING_TILE){view.isGameWon = true;}
        view.repaint();
    }
}