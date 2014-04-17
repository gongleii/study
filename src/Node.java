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

    //比较两个节点的状态，返回值为前者减去后者，若字符不同或耗散值相同则返回0
    public int nodeCompare(Node n){
        if (_lc.get(0).cubeEqual(n._lc.get(0))
                &&_lc.get(_lc.size()-1).cubeEqual(n._lc.get(n._lc.size()-1))){
            return _value-n._value;
        }
        return 9999;
    }

    //计算链表首元素接下来的值并且将计算结果插入链表
    public ArrayList<Node> getNextNodes(ArrayList<Node> ln){
        Comparator<Node> comparator = new Comparator<Node>() {
            @Override
            public int compare(Node node, Node node2) {
                return node._value-node2._value;
            }
        };
        int[] a={-3,-2,-1,1,2,3};
        for(int x : a){
            Cubes c = _lc.get(0).move(x);
            if(c!=null){
                int value = Math.abs(x)-1>0?Math.abs(x)-1:1;
                Node n =ln.get(0).nodeAdd(c, value);
                int num=0;
                for (int i = 0; i < ln.size() ; i++) {
                    int cha = n.nodeCompare(ln.get(i));
                    if(cha<0){
                        ln.set(i,n);
                        break;
                    }else if (cha==9999){
                        num++;
                    }
                }
                if(num==ln.size()){
                    ln.add(n);
                }
            }
        }
        ln.remove(0);
        //ln.sort(new sortByValue());
        Collections.sort(ln,comparator);
        return ln;
    }


}
