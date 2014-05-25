import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xzjs on 14-4-13.
 */
public class Calculate {
    public char shapes[];
    public ArrayList<Node> open = new ArrayList<Node>();
    public ArrayList<Node> closed = new ArrayList<Node>();
    public int[] moveStep;

    public Calculate(String s){
        shapes=s.toCharArray();
        Cubes c=new Cubes(shapes);
        ArrayList<Cubes> lc =new ArrayList<Cubes>();
        lc.add(c);
        Node n = new Node(lc,0);
        open.add(n);
        if(open.get(0)._lc.get(open.get(0)._lc.size()-1).isFinished()!=0) {
            tryOneTry();
        }else {
            Demo d=new Demo(open.get(0));
            d.demoShow();
        }
    }

    private void tryOneTry(){
        closed.add(open.get(0));
        open=open.get(0).getNextNodes(open,closed);
        if(open.get(0)._lc.get(open.get(0)._lc.size()-1).isFinished()!=0) {
            tryOneTry();
        }else {
           // Demo d=new Demo(open.get(0));
            //d.demoShow();
            String fileName="get.txt";
            try {
                FileWriter writer = new FileWriter(fileName);
                String s="";
                for(int i=0;i<open.get(0)._lc.size();i++){
                    //s+=open.get(0)._lc.get(i)._c.toString()+"\n";
                    String ss = new String(open.get(0)._lc.get(i)._c);
                    s+=ss+"\r\n";
                }
                writer.write(s);
                writer.close();
            }catch (IOException e){
                e.printStackTrace();
            }


        }
    }


}
