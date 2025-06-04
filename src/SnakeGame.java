import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.Random;
import java.util.ArrayList;

public class SnakeGame extends JPanel implements KeyListener {
    int boardWidth = 590;
    int boardHeight = 590;
 
    JFrame frame = new JFrame("Snake Game");
    
    int tileSize = 25;

    Tile snakeHead;
    ArrayList<Tile> snakeBody;
    Tile food;
    Random random;
    Timer timer;
    int movementX;
    int movementY;
    boolean gameOver = false;

    SnakeGame() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(boardWidth, boardHeight);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        frame.add(this);
        this.setFocusable(true);
        this.setSize(boardWidth, boardHeight);
        this.setBackground(Color.BLACK);

        snakeHead = new Tile(5, 5);
        snakeBody = new ArrayList<Tile>();

        food = new Tile(20, 5);

        random = new Random();
        placeFood();

        movementX = 0; 
        movementY = 0;

        addKeyListener(this);
        setFocusable(true);
        requestFocus();

        timer = new Timer(100, (ActionEvent e) -> {
            move();
            repaint();
        });

        timer.start();

        frame.setVisible(true);
    }   

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {

        for (int i = 0; i < boardWidth / tileSize; i++) {
            g.drawLine(i*tileSize, 0, i*tileSize, boardHeight);
            g.drawLine(0, i*tileSize, boardWidth, i*tileSize);
        }

        g.setColor(Color.RED);
        g.fillRect(food.x * tileSize, food.y * tileSize, tileSize, tileSize);

        g.setColor(Color.GREEN);
        g.fillRect(snakeHead.x * tileSize, snakeHead.y * tileSize, tileSize, tileSize); 

        g.setColor(Color.YELLOW);
        for (int i = 0; i < snakeBody.size(); i++) { 
            Tile snakePart = snakeBody.get(i);
            g.fill3DRect(snakePart.x * tileSize, snakePart.y * tileSize, tileSize, tileSize, true);
        }

        g.setFont(new Font("Arial", Font.BOLD, 20));
        if(gameOver == true) {
            g.setColor(Color.RED);
            g.drawString("Game Over!", boardWidth / 2 - 50, boardHeight / 2);
            g.drawString("Press R to Restart", boardWidth / 2 - 100, boardHeight / 2 + 30);
        } else {
            g.setColor(Color.WHITE);
            g.drawString("Score: " + snakeBody.size(), 10, 20);
        }
    }

    public class Tile {
        int x;
        int y;

        Tile(int x, int y) {
        this.x = x;
        this.y = y;
        }
    }

    public boolean collision(Tile tile1, Tile tile2){
        return tile1.x == tile2.x && tile1.y == tile2.y;
    }

    public void move(){

        if(collision(snakeHead, food)){
            placeFood();
            snakeBody.add(new Tile(snakeHead.x, snakeHead.y));
        }

        for(int i = snakeBody.size() - 1; i >= 0; i--) {
            Tile snakePart = snakeBody.get(i);
            if(i == 0) {
                snakePart.x = snakeHead.x;
                snakePart.y = snakeHead.y;
            } else { 
                Tile prevNextPart = snakeBody.get(i - 1);
                snakePart.x = prevNextPart.x;
                snakePart.y = prevNextPart.y;
            }  
        }

        snakeHead.x += movementX;
        snakeHead.y += movementY;
        
        for(int i = 0; i < snakeBody.size(); i++) {
            Tile snakePart = snakeBody.get(i);
            if(collision(snakeHead, snakePart)) {
                gameOver = true;
                timer.stop();
            }
        }

        if(snakeHead.x < 0 || snakeHead.x >= boardWidth / tileSize || 
           snakeHead.y < 0 || snakeHead.y >= boardHeight / tileSize) {
            gameOver = true;
            timer.stop();
        }

    }

    public void placeFood(){
        food.x = random.nextInt(boardWidth / tileSize);
        food.y = random.nextInt(boardHeight / tileSize);
    }

    
    @Override
    public void keyPressed(KeyEvent e) {
       if(e.getKeyCode() == KeyEvent.VK_UP && movementY != 1) {
            movementX = 0;
            movementY = -1;
        } else if(e.getKeyCode() == KeyEvent.VK_DOWN && movementY != -1) {
            movementX = 0;
            movementY = 1;
        } else if(e.getKeyCode() == KeyEvent.VK_LEFT && movementX != 1) {
            movementX = -1;
            movementY = 0;
        } else if(e.getKeyCode() == KeyEvent.VK_RIGHT && movementX != -1) {
            movementX = 1;
            movementY = 0;
        }
    }

    
    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
    }
}

