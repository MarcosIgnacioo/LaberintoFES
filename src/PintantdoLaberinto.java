import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import javax.swing.*;

import static java.util.concurrent.TimeUnit.SECONDS;

public class PintantdoLaberinto {
    JPanel panel = new JPanel();
    JFrame frame = new JFrame();

    int x = 420;
    int y = 30;
    int tecla;
    int width = 900;
    int heigth = 900;
    int columnas = 50;
    int filas = 40;
    boolean colisionIzq = false;
    boolean colisionDer = false;
    boolean colisionArriba = false;
    boolean colisionAbajo = false;

    JLabel tiempoLbl = new JLabel();
    boolean victoriaMagistral = false;
    int tiempo = 0;


    Rect r;
    int mapa[][] = {{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,1,0,1,1,1,1,0,0,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,0,0,0,0,0,0,0,0,1,1,1,1,0,1,0,1,1,1,1,1,1,1,1,0,1,0,1,1,0,1,1,0,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1,1,1,1,1,0,1,0,1,1,0,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,0,0,0,0,0,0,0,0,0,1,1,1,0,1,1,1,1,1,1,1,1,1,1,0,1,0,1,1,0,1,1,0,1,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,0,1,1,1,1,0,0,0,0,0,0,1,0,1,1,0,0,0,1,0,0,0,1,0,0,0,1,1,0,1,1,0,1,0,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,0,1,1,1,1,0,1,1,1,1,0,1,0,1,1,0,1,0,1,0,1,0,1,1,1,0,1,1,0,1,1,0,1,0,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,0,1,1,1,1,0,1,0,0,0,0,1,0,1,1,0,1,0,1,0,1,0,1,1,1,0,1,1,0,1,1,0,1,0,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,0,1,1,0,1,0,1,0,1,1,1,1,0,1,1,0,1,0,1,0,1,0,1,1,1,0,1,1,0,1,1,0,1,0,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,0,1,1,0,1,0,1,0,0,0,0,0,0,0,0,0,1,0,0,0,1,0,1,0,0,0,1,1,0,0,0,0,0,0,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,0,1,1,0,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1,0,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,1,0,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,0,1,1,0,1,0,1,1,1,1,1,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,1,1,1,1,0,1,1,1,0,1,0,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,0,1,1,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,0,1,0,1,1,0,1,0,1,1,1,1,1,0,1,1,1,0,1,0,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,0,1,0,1,0,1,1,1,1,1,1,0,1,1,1,1,0,0,0,0,1,0,1,1,0,1,0,1,1,1,1,1,0,1,1,1,0,1,0,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,0,1,0,1,0,1,1,1,1,1,1,0,1,1,1,1,0,0,0,0,1,0,0,1,0,1,0,0,0,0,0,0,0,1,1,1,0,1,0,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,0,1,0,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,0,1,0,1,0,1,1,0,1,1,1,1,1,1,0,1,0,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,0,1,0,1,0,1,1,1,1,0,0,0,1,1,1,1,1,1,1,1,1,1,0,1,0,1,0,1,1,0,1,1,1,1,1,1,0,1,0,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,0,1,0,1,0,0,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,0,1,0,1,0,1,1,0,0,0,0,0,0,1,0,1,0,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,0,1,0,1,1,0,1,1,1,0,0,0,0,0,1,1,1,1,1,1,1,1,0,1,0,1,0,1,1,1,1,1,1,1,0,1,0,1,0,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,0,1,0,1,1,0,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,0,0,1,1,1,1,1,0,0,0,0,1,0,1,0,1,0,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,0,1,0,0,0,0,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,0,1,0,0,0,1,0,1,1,1,1,0,1,0,1,0,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,0,1,1,1,1,1,1,1,1,1,0,0,0,0,1,1,1,1,1,1,0,0,0,0,1,0,1,0,1,0,0,0,0,0,0,1,0,1,0,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,0,1,1,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,0,1,1,1,1,0,1,0,1,1,1,1,1,1,1,1,0,1,0,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,0,1,1,0,1,1,1,1,1,0,1,0,0,0,0,1,1,1,1,1,0,1,1,1,1,0,1,0,1,1,1,0,0,0,1,1,0,1,0,1,1,1,1,1,1,1,1,1,1},
            {1,0,0,0,1,1,0,0,0,1,1,1,0,1,1,1,1,0,1,1,1,1,1,0,1,1,1,1,0,1,0,1,1,1,0,1,0,0,1,0,1,0,1,1,1,1,1,1,1,1,1,1},
            {1,0,1,1,1,1,1,1,0,1,0,0,0,1,1,1,1,0,1,1,1,1,1,0,0,0,0,0,0,1,0,1,0,0,0,1,1,0,0,0,1,0,1,1,1,1,1,1,1,1,1,1},
            {1,0,1,0,0,0,1,1,0,1,0,1,1,1,1,1,1,0,0,0,1,1,1,0,1,1,1,1,1,1,0,0,0,1,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1},
            {1,0,1,0,1,1,1,1,0,1,0,1,0,0,0,0,1,1,1,0,1,0,0,0,1,1,1,1,1,1,0,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,1,0,0,0,1,1,0,0,0,1,0,1,1,0,1,1,1,0,1,1,1,1,1,1,1,1,1,0,0,0,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,1,1,1,0,1,1,1,1,1,1,0,1,1,0,1,1,1,0,0,1,1,1,1,1,1,1,1,0,1,0,1,1,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,1,1,1,1,1,1,0,0,0,1,0,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,1,1,0,1,1,0,1,1,1,1,1,1,1,0,1,1,0,0,0,1,1,1,1,1,1,0,1,1,1,0,1,1,0,1,1,1,1,1,1,0,0,0,1,1,1,1,1,1,1,1},
            {1,0,1,1,0,0,1,0,0,0,0,0,1,1,1,0,1,1,2,1,0,0,0,0,0,0,0,0,1,1,1,0,1,1,0,1,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1},
            {1,0,1,1,1,0,1,1,1,1,1,0,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,0,0,0,1,0,1,1,0,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,0,0,0,0,0,0,0,0,0,0,0,1,0,1,1,2,1,1,1,1,1,1,1,0,0,1,0,0,0,1,1,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},};

    Rect pLista[][] = new Rect[filas][columnas];
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
            g.setColor(Color.CYAN);
            for (int i = 0; i < mapa.length; i++){
                for (int j = 0; j< columnas; j++){
                    if (mapa[i][j] == 1){
                        g.setColor(Color.CYAN);
                        pLista[i][j] = new Rect(j*20, i*20, 20,20, Color.CYAN);
                        g.fillRect(j*20,i*20,20,20);
                    }
                    else if (mapa[i][j] == 2){
                        g.setColor(Color.yellow);
                        pLista[i][j] = new Rect(j*20, i*20, 20,20, Color.YELLOW);
                        g.fillRect(j*20,i*20,20,20);
                    }

                }
            }
            //87,65,83,68
        }
    }

    public void createGUI() {
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
                    if (!r.colisionLabArriba(pLista)){
                        y-=10;
                    }
                    System.out.println("ColArr"+colisionArriba);
                }
                if ((e.getKeyCode() == 65 || e.getKeyCode() == 37) && x > 0){
                    if (!r.colisionLabIzquierda(pLista)){
                        x-=10;
                    }
                    System.out.println("ColIzq"+colisionIzq);

                }
                if (e.getKeyCode() == 83 && y<heigth){
                    if (!r.colisionLabAbajo(pLista)){
                        y+=10;
                    }
                    System.out.println("ColAb"+colisionAbajo);

                }
                if (e.getKeyCode() == 68 && x < width){
                    System.out.println(r.colisionLabDerecha(pLista));
                    if (!r.colisionLabDerecha(pLista)){
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
        reiniciarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetearPosicion();
            }
        });
        tiempoLbl.setFont(new Font("Arial", Font.BOLD, 24));
        Cronometro.iniciar(tiempoLbl);
        panel.add(new MyGraphics());
        panel.setPreferredSize(new Dimension(900,900));
        reiniciarP.setBackground(Color.orange);
        reiniciarP.setBackground(new Color(74, 225, 77));
        reiniciarP.setLayout(new BorderLayout());
        reiniciarP.add(reiniciarBtn, BorderLayout.CENTER);
        reiniciarP.add(tiempoLbl, BorderLayout.WEST);
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
            if (target != null){
                if (this.x < target.x + target.w  +10 && this.x + this.w> target.x
                        && this.y < target.y + target.h  && this.h +this.y > target.y){
                    return true;
                }
                return false;
            }
            return false;
        }
        public Boolean colisionDerecha(Rect target){
            if (target != null){
            if (this.x < target.x + target.w   && this.x + this.w +10> target.x
                    && this.y < target.y + target.h  && this.h +this.y > target.y){
                return true;
            }
            return false;
        }
        return false;
        }

        public Boolean colisionArriba(Rect target){
            if (target != null){
                if (this.x < target.x + target.w   && this.x + this.w > target.x
                        && this.y < target.y + target.h +10 && this.h +this.y > target.y){
                    return true;
                }
                return false;
            }
            return false;
        }
        public Boolean colisionAbajo(Rect target){
            if (target != null){
            if (this.x < target.x + target.w   && this.x + this.w > target.x
                    && this.y < target.y + target.h && this.h +this.y  +10 > target.y){
                return true;
                }
            return false;
            }
        return false;
    }

        public Boolean colisionLabArriba(Rect target[][]){
            for (int i = 0; i < mapa.length; i++){
                for (int j = 0; j< columnas; j++){
                    if (this.colisionArriba(target[i][j]) == true && target[i][j] != null){
                        return true;
                    }
                }
            }
            return false;
        }

        public Boolean colisionLabAbajo(Rect target[][]){
            for (int i = 0; i < mapa.length; i++){
                for (int j = 0; j< columnas; j++){
                    if (this.colisionAbajo(target[i][j]) == true && target[i][j] != null){
                        if (target[i][j].c.equals(Color.yellow)){
                            victoriaRoyal();
                            return false;
                        }
                        return true;
                    }
                }
            }
            return false;
        }
        public Boolean colisionLabIzquierda(Rect target[][]){
            for (int i = 0; i < mapa.length; i++){
                for (int j = 0; j< columnas; j++){
                    if (this.colisionIzquierda(target[i][j]) == true&& target[i][j] != null){
                        return true;
                    }
                }
            }
            return false;
        }
        public Boolean colisionLabDerecha(Rect target[][]){
            for (int i = 0; i < mapa.length; i++){
                for (int j = 0; j< columnas; j++){
                    if (this.colisionDerecha(target[i][j]) == true && target[i][j] != null){
                        return true;
                    }
                }
            }
            return false;
        }
    }
    public void victoriaRoyal(){
        Cronometro.detener();
        JOptionPane.showMessageDialog(null, "Ganast en este tiempo " + tiempoLbl.getText(), "win", JOptionPane.INFORMATION_MESSAGE);
        resetearPosicion();
    }
    public void resetearPosicion(){
        Cronometro.reiniciar(tiempoLbl);
        Cronometro.iniciar(tiempoLbl);
        x = 420;
        y = 30;
        panel.repaint();
        panel.revalidate();
        frame.setFocusable(true);
        frame.requestFocus();
    }
    public void darkHour(boolean running){
        final ScheduledExecutorService[] scheduler = {null};
        if (scheduler[0] == null){
            scheduler[0] = Executors.newScheduledThreadPool(1);
        }
        final ScheduledExecutorService[] finalScheduler = {scheduler[0]};
        final Runnable runnable = new Runnable() {
            int countdownStarter = 20;

            public void run() {

                System.out.println(countdownStarter);
                countdownStarter--;

                if (countdownStarter < 0) {
                    System.out.println("Timer Over!");
                    finalScheduler[0] = null;
                    finalScheduler[0].shutdown();
                }
            }
        };
        scheduler[0].scheduleAtFixedRate(runnable, 0, 1, SECONDS);
    }
    public void theShowIsOver(ScheduledExecutorService scheduler){
        System.out.println("CAN YOU FEEL THE JAZZ!");
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