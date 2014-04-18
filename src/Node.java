import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by xzjs on 14-4-13.
 */
public class Node {
    public ArrayList<Cubes> _lc=new ArrayList<Cubes>();
    public int _value = 0;

    public Node(ArrayList<Cubes> lc,int i){
        _lc=(ArrayList<Cubes>)lc.clone();
        _value = i;
    }

    //加入新的状态
    public Node nodeAdd(Cubes c,int i){
        ArrayList<Cubes> alc = (ArrayList<Cubes>)_lc.clone();
        alc.add(c);
        int value = _value+i;
        return new Node(alc,value);
    }

    //比较两个节点的最后一个字符串
    public boolean nodeCompare(Node n){
        if (_lc.get(_lc.size()-1).cubeEqual(n._lc.get(n._lc.size()-1))){
            return true;
        }
        return false;
    }

    //计算链表首元素接下来的值并且将计算结果插入链表
    public ArrayList<Node> getNextNodes(ArrayList<Node> ln,ArrayList<Node> lc){
        Comparator<Node> comparator = new Comparator<Node>() {
            @Override
            public int compare(Node node, Node node2) {
                return node._value-node2._value;
            }
        };
        int[] a={-3,-2,-1,1,2,3};
        for(int x : a){
            Cubes c = _lc.get(_lc.size()-1).move(x);
            if(c!=null){
                int value = Math.abs(x)-1>0?Math.abs(x)-1:1;
                Node n =ln.get(0).nodeAdd(c, value);
                boolean num=true;
                for (int i =0;i<lc.size();i++){
                    if(n.nodeCompare(lc.get(i))){
                        num=false;
                    }
                }
                if(num==false){
                    continue;
                }
                for (int i = 0; i < ln.size() ; i++) {
                    if(n.nodeCompare(ln.get(i))){
                        num=false;
                    }
                }
                if(num==true){
                    char[] c1=n._lc.get(0)._c;
                    char[] c2=n._lc.get(n._lc.size()-1)._c;
                    String s1=String.valueOf(c1);
                    String s2=String.valueOf(c2);
                    if(!(s1.equals(s2))) {
                        ln.add(n);
                    }
                }else {
                    continue;
                }
            }
        }
        ln.remove(0);
        Collections.sort(ln,comparator);
        return ln;
    }


}
