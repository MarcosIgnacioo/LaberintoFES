import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class PintantdoLaberinto {

    int x = 0;
    int y = 0;
    int tecla;
    int width = 900;
    int heigth = 900;
    boolean colisionIzq = false;
    boolean colisionDer = false;
    boolean colisionArriba = false;
    boolean colisionAbajo = false;
    Rect p = new Rect(90,50,50,200, Color.BLUE);
    Rect r;
    public class MyGraphics extends JComponent {


        MyGraphics() {
            setPreferredSize(new Dimension(width, heigth));
        }

        @Override
        public void paintComponent(Graphics g) {
            r = new Rect(x,y,10,10, Color.BLACK);
            super.paintComponent(g);
            g.setColor(r.c);
            g.fillRect(r.x, r.y, r.w, r.h);
            g.setColor(p.c);
            g.fillRect(p.x, p.y, p.w, p.h);
            //87,65,83,68
        }
    }

    public void createGUI() {
        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        JPanel reiniciarP = new JPanel();
        frame.setLayout(new BorderLayout());
        panel.setBackground(Color.RED);
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                tecla = e.getKeyCode();
                int teclaAnterior;
                if ((e.getKeyCode() == 87 || e.getKeyCode() == 38) && y > 0){
                    if (!r.colisionArriba(p)){
                        y-=10;
                    }
                    System.out.println("ColArr"+colisionArriba);
                }
                if ((e.getKeyCode() == 65 || e.getKeyCode() == 37) && x > 0){
                    if (!r.colisionIzquierda(p)){
                        x-=10;
                    }
                    System.out.println("ColIzq"+colisionIzq);

                }
                if (e.getKeyCode() == 83 && y<430){
                    if (!r.colisionAbajo(p)){
                        y+=10;
                    }
                    System.out.println("ColAb"+colisionAbajo);

                }
                if (e.getKeyCode() == 68 && x < 450){
                    if (!r.colisionDerecha(p)){
                        x+=10;
                    }
                    System.out.println("ColDersdd"+colisionDer);

                }
                panel.repaint();
                panel.revalidate();
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        JButton reiniciarBtn = new JButton("Reiniciar");
        reiniciarBtn.setBackground(Color.pink);
        panel.add(new MyGraphics());
        panel.setPreferredSize(new Dimension(500,500));
        reiniciarP.setBackground(Color.orange);
        reiniciarP.setBackground(new Color(74, 225, 77));
        reiniciarP.setLayout(new BorderLayout());
        reiniciarP.add(reiniciarBtn, BorderLayout.CENTER);
        frame.setFocusable(true);
        frame.requestFocus();
        reiniciarP.setPreferredSize(new Dimension(500,100));
        frame.add(reiniciarP, BorderLayout.SOUTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setPreferredSize(new Dimension(900,900));
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
        public Boolean colisionIzquierda(Rect target){
            if (this.x < target.x + target.w  +10 && this.x + this.w> target.x
                    && this.y < target.y + target.h  && this.h +this.y > target.y){
                return true;
            }
            return false;
        }
        public Boolean colisionDerecha(Rect target){
            if (this.x < target.x + target.w   && this.x + this.w +10> target.x
                    && this.y < target.y + target.h  && this.h +this.y > target.y){
                return true;
            }
            return false;
        }

        public Boolean colisionArriba(Rect target){
            if (this.x < target.x + target.w   && this.x + this.w > target.x
                    && this.y < target.y + target.h +10 && this.h +this.y > target.y){
                return true;
            }
            return false;
        }
        public Boolean colisionAbajo(Rect target){
            if (this.x < target.x + target.w   && this.x + this.w > target.x
                    && this.y < target.y + target.h && this.h +this.y  +10 > target.y){
                return true;
            }
            return false;
        }

    }
    public void colisionesCuatroLados(int t){
        switch (t){
            case 87:
                break;
            case 65:
                break;
            case 83:
                break;
            case 68:
                break;
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