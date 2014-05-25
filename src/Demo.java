/**
 * Created by xzjs on 14-4-13.
 */

import sun.plugin2.os.windows.Windows;

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
            } else if ((x != -step * 150) && y == -100) {
                x -= step * 15 / Math.abs(step);
            } else if ((x == -step * 150) && y != 0) {
                y += 10;
            } else if ((x == -step * 150) && y == 0) {
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
            try {
                Thread.currentThread().sleep(2000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            dispose();
        }
    }

    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        Image image = this.getToolkit().getImage(".//image//background.jpg");

        g.drawImage(image, 0, 0, this);
        //g2.setColor(getContentPane().getBackground());
        //g2.fillRect(0,0,getContentPane().getWidth(),getContentPane().getHeight());
        for (int j = 0; j < _cube._c.length; j++) {
            g2.setColor(Color.gray);
            switch (_cube._c[j]){
                case 'w':
                    //g2.setColor(Color.WHITE);
                    image=this.getToolkit().getImage(".//image//white.png");
                    break;
                case 'b':
                    //g2.setColor(Color.BLACK);
                    image=this.getToolkit().getImage(".//image//black.png");
                    break;
                default:
                    continue;
            }
            RoundRectangle2D rec2;
            if (j==_cube.indexOfE()+step) {
                g.drawImage(image, 10 + 150 * j + x, 600 + y, this);
                //rec2 = new RoundRectangle2D.Float(10 + 100 * j + x, 110 + y, 100, 100, 20, 20);
            }else {
                g.drawImage(image, 10 + 150 * j, 600 , this);
                //rec2 = new RoundRectangle2D.Float(10 + 100 * j , 440 , 100, 100, 20, 20);
            }
            //g2.fill(rec2);
        }
    }

    public void demoShow(){
        this.setTitle("试试画布");
        _cube=_n._lc.get(i);
        if(howToMove().size()!=0) {
            step = howToMove().get(i);
        }

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        //通过调用GraphicsEnvironment的getDefaultScreenDevice方法获得当前的屏幕设备了
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        // 全屏设置
        gd.setFullScreenWindow(this);


        ImageIcon img = new ImageIcon("//background.jpg");//这是背景图片
        JLabel imgLabel = new JLabel(img);//将背景图放在标签里。

        this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));//注意这里是关键，将背景标签添加到jfram的LayeredPane面板里。
        imgLabel.setBounds(0,0,img.getIconWidth(), img.getIconHeight());//设置背景标签的位置
        Container cp=this.getContentPane();
        cp.setLayout(new BorderLayout());
        //JButton but=new JButton("anniu");//创建按钮
        //cp.add(but,"North");//将按钮添加入窗口的内容面板

        ((JPanel)cp).setOpaque(false); //注意这里，将内容面板设为透明。这样LayeredPane面板中的背景才能显示出来。

        //this.setBounds(10,10,100+100*(_cube._c.length+1),300);
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
