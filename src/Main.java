import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.*;

public class Main extends JPanel implements ActionListener
{
    int recX=0;
    int recY=0;
    int flag = 0;
    Timer t = new Timer(5,this);

    public Main()
    {
        t.start();
    }

    public void paint(Graphics g)
    {
        Graphics2D g2 = (Graphics2D)g;
        RoundRectangle2D rec1 = new RoundRectangle2D.Double(10+recX,110+recY,100,100,20,20);
        g2.fill(rec1);
        g2.setColor(Color.white);
        RoundRectangle2D rec2 = new RoundRectangle2D.Double(110-recX,110-recY,100,100,20,20);
        g2.fill(rec2);

        /*Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gp = new GradientPaint(0, recY, Color.white, 0, recY + 60, Color.DARK_GRAY, true);
        g2.setPaint(gp);
        RoundRectangle2D rec = new RoundRectangle2D.Double(10, 10, 370, 250, 20, 20);
        g2.fill(rec);
        gp = new GradientPaint(0,260-rw,Color.white,380,260,Color.BLACK,true);
        Ellipse2D r = new Ellipse2D.Float(30,260-rw,330,rw);
        g2.setPaint(gp);
        g2.fill(r);
        g2.setFont(new Font("Serif",Font.BOLD,85));
        g2.setPaint(Color.lightGray);
        g2.drawString("笨笨",50+4,280-rw+4);
        g2.setPaint(Color.DARK_GRAY);
        g2.drawString("笨笨",50,280-rw);*/
    }

    public void actionPerformed(ActionEvent e)
    {
        if(recX==0&&recY==0){
            recY=50;
        }else if (recX==0&&recY==50){
            recY=0;
            recX=100;
        }else if (recX==100&&recY==0){
            recX=0;
            recY=-50;
        }else if (recX==0&&recY==-50){
            t.stop();
        }
        this.repaint();
    }

    public static void main(String[] args) {
        Main jp = new Main();
        JFrame jf = new JFrame();
        jf.add(jp);
        jf.setTitle("试试画布");
        jf.setBounds(100,100,220,340);
        jf.setResizable(false);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
