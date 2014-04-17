/**
 * Created by xzjs on 14-4-13.
 */
public class Cubes {
    public char[] _c;

    public Cubes(char[] c){
        _c=c.clone();
    }

    //判断所处状态是否是目的状态
    public boolean isFinished(){
        int sum =0;
        for (int i = 0; i < _c.length; i++) {
            if(_c[i]=='b'){
                if(sum<i||i==0){
                    return false;
                }else{
                    return true;
                }
            }else{
                sum++;
            }
        }
        return true;
    }

    public Cubes move(int i){
        Cubes c = new Cubes(_c);
        if(c.m(i)){
            return c;
        }
        return null;
    }

    public boolean m(int i){
        int index =indexOfE();
        if(index+i<0||index+i>=_c.length){
            return false;
        }
        char temp = _c[index];
        _c[index] = _c[index+i];
        _c[index+i]=temp;
        return true;
    }

    //找到e所对应的索引
    public int indexOfE(){
        for (int i = 0; i < _c.length; i++) {
            if(_c[i]=='e'){
                return i;
            }
        }
        return 0;
    }

    //判断两个积木块是否相等
    public boolean cubeEqual(Cubes c){
        if(_c.equals(c._c)){
            return true;
        }else{
            return false;
        }
    }
}
