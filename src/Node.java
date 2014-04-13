import java.util.ArrayList;
import java.util.List;

/**
 * Created by xzjs on 14-4-13.
 */
public class Node {
    public List<Cubes> _lc=new ArrayList<Cubes>();
    public int _value = 0;

    public Node(List<Cubes> lc,int i){
        _lc=lc;
        _value = i;
    }

    //加入新的状态
    public Node add(Cubes c,int i){
        Node n=new Node(_lc,_value);
        n._lc.add(c);
        n._value+=i;
        return n;
    }

    //比较两个节点的状态，返回值为前者减去后者，若字符不同或耗散值相同则返回0
    public int nodeCompare(Node n){
        if (_lc.get(0).cubeEqual(n._lc.get(0))&&_lc.get(_lc.size()-1).cubeEqual(n._lc.get(n._lc.size()-1))){
            return _value-n._value;
        }
        return 0;
    }

    //计算链表首元素接下来的值并且将计算结果插入链表
    public ArrayList<Node> getNextNodes(ArrayList<Node> ln){
        int[] a={-3,-2,-1,1,2,3};
        for(int x : a){
            Cubes c = _lc.get(0).move(x);
            if(c!=null){
                int value = Math.abs(x)-1>0?Math.abs(x)-1:1;
                Node n =ln.get(0).add(c,value);
                for (int i = 0; i < ln.size() ; i++) {
                    if(n.nodeCompare(ln.get(i))<0){
                        ln.set(i,n);
                    }
                }
            }
        }
        ln.remove(0);
        ln.sort(new sortByValue());
        return ln;
    }
}
