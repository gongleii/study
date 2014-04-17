/**
 * Created by xzjs on 14-4-13.
 */

import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;

public class Demo extends JPanel /*implements ActionListener */{

    Node _n;
    //Timer t = new Timer(500,this);
    int i=10;

    public Demo(Node n){
        _n=n;
    }

    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        for(char x :shapes){
            switch (x){
                case 'B':
                case 'b':
                    g2.setColor(Color.white);
                    break;
                case 'W':
                case 'w':
                    g2.setColor(Color.black);
                    break;
                case 'E':
                case 'e':
                    g2.setColor(Color.gray);
                    break;
                default:
                    break;
            }
            RoundRectangle2D rec = new RoundRectangle2D.Float(i,110,100,100,20,20);
            i+=100;
            g2.fill(rec);
        }
    }

    public void demoShow(){
        JFrame jf = new JFrame();
        jf.add(this);
        jf.setTitle("试试画布");
        jf.setBounds(10,10,20+100*shapes.length,340);
        jf.setResizable(false);
        jf.setVisible(true);
    }

    private int[] distance() {
        for (int j = 0; j <_n._lc.size()-1 ; j++) {
            char[] a1=_n._lc.get(j)._c;
            char[] a2=_n._lc.get(j+1)._c;
            for (int k = 0; k <a1.length ; k++) {
                if(a1[k]!=a2[k]){
                    if(a1[k]=='e'){

                    }
                }
            }
        }
    }
}
