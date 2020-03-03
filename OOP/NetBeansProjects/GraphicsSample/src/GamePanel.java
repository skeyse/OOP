
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sean-
 */

// WRITE A FOOD OBJECT THAT YOU TOUCH AND IT DISAPPEARS
public class GamePanel extends JPanel implements ActionListener, KeyListener, Runnable {
    
    private int foodX = 50;
    private int foodY = 50;
    private int dir;
    private boolean foodEaten = false;
    private boolean moving = false;
    
    List<Snake> snakes = new LinkedList();
    
    public GamePanel() {
        
        super();
        
        setFocusable(true);
        Thread thread = new Thread(this);
        thread.start();
        
    }
    
    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        
        //draw head
        
        graphics.setColor(Color.RED);
        graphics.drawOval(0, 0, 10, 10);
        graphics.fillOval(0, 0, 10, 10);
        
        
        
        //draw food
        if (foodEaten == false) {
            graphics.setColor(Color.GREEN);
            graphics.drawRect(foodX, foodY, 10, 10);
            graphics.fillRect(foodX, foodY, 10, 10);
        }
        
    }
    
   
    private void moveCircle() {
        
        switch(dir) {
            case KeyEvent.VK_UP:
                snake.setY(0, snake.getY(0) - 10);
                break;
            case KeyEvent.VK_DOWN:
                snake.setY(0, snake.getY(0) + 10);
                break;
            case KeyEvent.VK_RIGHT:
                snake.setX(0, snake.getX(0) + 10);
                break;
            case KeyEvent.VK_LEFT:
                snake.setX(0, snake.getX(0) - 10);
                break;
                       
        
        
    }
        }

    @Override
    public void run() {
        addKeyListener(this);
        Timer timer = new Timer(75, this);
        timer.setRepeats(true);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (snake.getX(0) == foodX && snake.getY(0) == foodY) {
            foodEaten = true;
            createFood();
        }
        repaint();
        moveCircle();
        }
    
    public void createFood() {
        foodX += 20;
        foodY += 20;
        foodEaten = false;
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        
        int keyCode = keyEvent.getKeyCode();
    
        switch(keyCode) {
            case KeyEvent.VK_UP:
            dir = Directions.UP.getDirection();
            snake.setMoving(true);
            break;
                
            case KeyEvent.VK_DOWN:
            dir = Directions.DOWN.getDirection();
            snake.setMoving(true);
            break;
            
            case KeyEvent.VK_RIGHT:
            dir = Directions.RIGHT.getDirection();
            snake.setMoving(true);
            break;
                
            case KeyEvent.VK_LEFT:
            dir = Directions.LEFT.getDirection();
            snake.setMoving(true);
            break;
        }
        
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
      
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}
