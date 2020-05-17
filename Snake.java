import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class Snake implements ActionListener, KeyListener {
    public static Snake snake;
    public static RenderPanel renderPanel;
    public Random random = new Random();
    public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
    public int direction = DOWN;
    public int scale = 10;
    public int tailLength;
    public Timer timer = new Timer(100, this);
    public Point head = new Point(0,0);
    public Point apple;
    ArrayList<Point> snakePart = new ArrayList<>();
    public boolean running;


    public Snake(){
        JFrame frame = new JFrame("Snake");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.add(renderPanel = new RenderPanel());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.addKeyListener(this);
        tailLength = 1;
        timer.setDelay(100);
        timer.start();
        startGame();

    }
    public void startGame(){
        apple = new Point(random.nextInt(30), random.nextInt(30));
        running = true;
    }

    public static void main(String[] args){
        snake = new Snake();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println("[" + head.x + "," + head.y + "]");
        renderPanel.repaint();

        snakePart.add(new Point(head.x, head.y));
        if (direction == UP) {
            head = new Point(head.x, head.y - 1);
        } else if (direction == DOWN) {
            head = new Point(head.x, head.y + 1);
        } else if (direction == LEFT) {
            head = new Point(head.x - 1, head.y);
        } else if (direction == RIGHT) {
            head = new Point(head.x + 1, head.y);
        }
        if (snakePart.size() > tailLength){
            snakePart.remove(0);
        }
        if (head.equals(apple)){
            tailLength++;
            apple = new Point(random.nextInt(30), random.nextInt(30));
        }
        for(int i = 0; i < snakePart.size() - 1; i++){
            if (snakePart.get(i).equals(head)){
                running = false;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int i = keyEvent.getKeyCode();
        if ((i == KeyEvent.VK_A || i == KeyEvent.VK_LEFT)  && direction != RIGHT)
            direction = LEFT;
        if ((i == KeyEvent.VK_D || i == KeyEvent.VK_RIGHT) && direction != LEFT)
            direction = RIGHT;
        if ((i == KeyEvent.VK_W || i == KeyEvent.VK_UP)  && direction != DOWN)
            direction = UP;
        if ((i == KeyEvent.VK_S || i == KeyEvent.VK_DOWN) && direction != UP)
            direction = DOWN;
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
