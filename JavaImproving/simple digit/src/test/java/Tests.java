import org.junit.Test;
import org.junit.Assert;
public class Tests {
    @Test
    public void correctWork(){
        FindNotSimple f=new FindNotSimple();
        int[] arr={1,2,3,5,7,9};
        int [] arr1={1,2,3,5,7,8,9};
        int []arr2={1,2,3,5};
        Assert.assertTrue(f.findNotSimple(arr));
        Assert.assertTrue(f.findNotSimple(arr1));
        Assert.assertFalse(f.findNotSimple(arr2));
    }


    @Test
    public void correctParallelWork(){
        FindNotSimple f=new FindNotSimple();
        int[] arr={1,2,3,5,7,9};
        int [] arr1={1,2,3,5,7,8,9};
        int []arr2={1,2,3,5};
        MyThreadPool p=new MyThreadPool();
        Assert.assertTrue(p.count(arr));
        Assert.assertTrue(p.count(arr1));
        Assert.assertFalse(p.count(arr2));
    }
}
