import javax.swing.*;
import java.awt.event.*;

public class Main extends JFrame implements ActionListener
{
    private JPanel jp=new JPanel();
    private JLabel jl = new JLabel("请输入积木块对应的字符串");
    private JButton jb = new JButton("开始");
    private JTextField jtxtstr =new JTextField();

    public Main()
    {
        jp.setLayout(null);
        jl.setBounds(30, 20, 180, 30);
        jb.setBounds(50, 130, 83, 26);
        jp.add(jl);
        jp.add(jb);
        jb.addActionListener(this);
        jtxtstr.setBounds(30,70,180,30);
        jp.add(jtxtstr);
        jtxtstr.addActionListener(this);
        this.add(jp);
        this.setTitle("滑动积木块");
        this.setResizable(false);
        this.setBounds(100,100,300,250);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource()==jtxtstr){
            jb.requestFocus();
        }else if(e.getSource()==jb){
            Calculate c=new Calculate(jtxtstr.getText());
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
