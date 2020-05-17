import javax.swing.*;
import java.awt.*;

public class RenderPanel extends JPanel {
    public static int curColor = 0;
    public static Color green = new Color(1666073);
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        //Painting Background
        g.setColor(Color.BLACK);
        g.fillRect(0,0, 1000,1000);
        Snake snake = Snake.snake;

        //Painting body

        g.setColor(Color.GREEN);
        for (Point point: snake.snakePart){
            g.fillRect(point.x * snake.scale, point.y * snake.scale, snake.scale,snake.scale);
        }

        //Painting Head

        Point head = snake.head;
        g.setColor(Color.WHITE);
        g.fillRect(head.x * snake.scale, head.y * snake.scale, snake.scale,snake.scale);

        //Painting Apple

        g.setColor(Color.RED);
        g.fillRect(snake.apple.x * snake.scale, snake.apple.y * snake.scale, snake.scale, snake.scale);

        if (snake.running == false){
            g.drawString("Game Over", 50, 50);
        }
    }
}
