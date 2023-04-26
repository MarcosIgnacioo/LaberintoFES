import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class PintantdoLaberinto {

    int x = 0;
    int y = 0;
    int tecla;
    int width = 500;
    int heigth = 500;
    public class MyGraphics extends JComponent {

        private static final long serialVersionUID = 1L;

        MyGraphics() {
            setPreferredSize(new Dimension(width, heigth));
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Rect r = new Rect(x,y,50,50, Color.BLACK);
            Rect p = new Rect(90,50,50,200, Color.BLUE);
            g.setColor(r.c);
            g.fillRect(r.x, r.y, r.w, r.h);
            g.setColor(p.c);
            g.fillRect(p.x, p.y, p.w, p.h);
            System.out.println(r.collision(p));
        }
    }

    public void createGUI() {
        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        panel.setBackground(Color.blue);
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println(e.getKeyCode());
                tecla = e.getKeyCode();
                int teclaAnterior;
                if ((e.getKeyCode() == 87 || e.getKeyCode() == 38) && y > 0){
                    y-=10;
                }
                if ((e.getKeyCode() == 65 || e.getKeyCode() == 37) && x > 0){
                    x-=10;
                }
                if (e.getKeyCode() == 83 && y<430){
                    System.out.println(y+"y");
                    y+=10;
                }
                if (e.getKeyCode() == 68 && x < 450){
                    System.out.println(x+"x");
                    x+=10;
                }
                panel.repaint();
                panel.revalidate();
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        panel.add(new MyGraphics());
        JPanel reiniciarP = new JPanel();
        JButton popo = new JButton("asd");
        popo.setVisible(true);
        reiniciarP.add(new MyGraphics());
        reiniciarP.add(popo);
        reiniciarP.setSize(500,500);
        reiniciarP.setVisible(true);
        reiniciarP.setBackground(new Color(74, 225, 77));
        reiniciarP.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.repaint();
        frame.revalidate();

    }
    public class Rect{
        int x =0;
        int y = 0;
        int w = 0;
        int h = 0;
        Color c = Color.BLACK;
        Rect(int x, int y, int w, int h, Color c){
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
            this.c = c;
        }
        public Boolean collision(Rect target){
            if (this.x< target.x + target.w && this.x + this.w > target.x
                    && this.y < target.y + target.h && this.h +this.y > target.y){
                return true;
            }
            return false;
        }

    }


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                PintantdoLaberinto GUI = new PintantdoLaberinto();
                GUI.createGUI();
            }
        });
    }
}