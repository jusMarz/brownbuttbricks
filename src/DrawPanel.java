import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.util.ArrayList;

public class DrawPanel extends JPanel implements MouseListener {

    private BrickLayout b = new BrickLayout("src/bricks", 39, false);
    private int[][] grid = b.getBrickLayout();
    private long timer = System.currentTimeMillis();

    public DrawPanel()
    {
        this.addMouseListener(this);
    }

    protected void paintComponent(Graphics g) {
        if(System.currentTimeMillis() - timer > 500)
        {
            b.BrickFall();
            timer = System.currentTimeMillis();
            for(int[] ints: grid)
            {
            for(int intz: ints)
            {
                System.out.print(intz);
            }
            System.out.println();
            }
            System.out.println("--------------------------------");
        }
        super.paintComponent(g);
        int x = 10;
        int y = 10;
        b.getBrickLayout();
//        for(int[] ints: grid)
//        {
//            for(int intz: ints)
//            {
//                System.out.print(intz);
//            }
//            System.out.println();
//        }
        Graphics2D g2 = (Graphics2D)g;


        for (int c = 0; c < grid[0].length; c++) {
            for (int r = 0; r < grid.length; r++) {
                g.drawRect(x, y, 20, 20);
                if (grid[r][c] == 1) {
                    g2.setColor(Color.RED);
                    g2.fillRect(x, y, 20, 20);
                    g2.setColor(Color.BLACK);
                }
                if (grid[r][c] == 2) {
                    g2.setColor(Color.GRAY);
                    g2.fillRect(x, y, 20, 20);
                    g2.setColor(Color.BLACK);
                }
                y += 25;
            }
            x += 25;
            y = 10;
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}