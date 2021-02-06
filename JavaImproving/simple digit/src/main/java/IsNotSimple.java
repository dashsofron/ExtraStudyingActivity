import java.util.concurrent.Callable;

public class IsNotSimple implements Callable<Boolean> {
    int i;
    IsNotSimple(int i){
        this.i=i;
    }
    public   Boolean call (){

        for (int k = 2; k <= Math.sqrt(i); k++) {
            if (i % k == 0) return  true;
        }

        return false;
    }
}
