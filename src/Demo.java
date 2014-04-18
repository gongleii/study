/**
 * Created by xzjs on 14-4-13.
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

public class Demo extends JFrame implements ActionListener {

    Node _n;
    Timer t = new Timer(50,this);
    int i=0;
    private Cubes _cube;
    private int x=0,y=0;
    private int step;

    public Demo(Node n){
        _n=n;
    }

    public void actionPerformed(ActionEvent e){
        if(step!=0) {
            if (x == 0 && y > -100) {
                y -= 10;
            } else if ((x != -step * 100) && y == -100) {
                x -= step * 10 / Math.abs(step);
            } else if ((x == -step * 100) && y != 0) {
                y += 10;
            } else if ((x == -step * 100) && y == 0) {
                if (i == _n._lc.size() - 1) {
                    t.stop();
                    return;
                } else {
                    _cube = _n._lc.get(++i);
                    step = howToMove().get(i);
                    x=0;
                }
            }
            this.repaint();
        }else {
            t.stop();
        }
    }

    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getContentPane().getBackground());
        g2.fillRect(0,0,getContentPane().getWidth(),getContentPane().getHeight());
        for (int j = 0; j < _cube._c.length; j++) {
            g2.setColor(Color.gray);
            //RoundRectangle2D rec=new RoundRectangle2D.Float(10+100*j,110,100,100,20,20);
            //g2.fill(rec);
            switch (_cube._c[j]){
                case 'w':
                    g2.setColor(Color.WHITE);
                    break;
                case 'b':
                    g2.setColor(Color.BLACK);
                    break;
                default:
                    continue;
            }
            RoundRectangle2D rec2;
            if (j==_cube.indexOfE()+step) {
                rec2 = new RoundRectangle2D.Float(10 + 100 * j + x, 110 + y, 100, 100, 20, 20);
            }else {
                rec2 = new RoundRectangle2D.Float(10 + 100 * j , 110 , 100, 100, 20, 20);
            }
            g2.fill(rec2);
        }
    }

    public void demoShow(){
        this.setTitle("试试画布");
        _cube=_n._lc.get(i);
        if(howToMove().size()!=0) {
            step = howToMove().get(i);
        }

        this.setBounds(10,10,20+100*_cube._c.length,340);
        this.setResizable(false);
        this.setVisible(true);
        t.start();
    }

    private ArrayList<Integer> howToMove() {
        ArrayList<Integer> steps=new ArrayList<Integer>();
        for (int j = 0; j <_n._lc.size()-1 ; j++) {
            char[] a1=_n._lc.get(j)._c;
            char[] a2=_n._lc.get(j+1)._c;
            for (int k = 0; k <a1.length ; k++) {
                if (a1[k]=='e'){
                    for(int l=0;l<a2.length;l++){
                        if(a2[l]=='e'){
                            steps.add(l-k);
                        }
                    }
                }
            }
        }
        steps.add(0);
        return steps;
    }
}
