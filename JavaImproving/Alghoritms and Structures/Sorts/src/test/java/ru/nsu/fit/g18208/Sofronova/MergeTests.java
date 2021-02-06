package ru.nsu.fit.g18208.Sofronova;

import org.junit.Assert;
import org.junit.Test;

public class MergeTests {
    @Test
    public void work(){
        int[] array={7,6,5,4,3,2,1};
        int[] array1={1,2,3,4,5,6,7};
        new MergeSort().sort(array,0,array.length-1);
        Assert.assertArrayEquals(array,array1);
    }
    @Test
    public void work1(){
        int[] array={1};
        int[] array1={1};
        new MergeSort().sort(array,0,array.length-1);
        Assert.assertArrayEquals(array,array1);
    }
    @Test
    public void work2(){
        int[] array={1,2};
        int[] array1={1,2};
        new MergeSort().sort(array,0,array.length-1);
        Assert.assertArrayEquals(array,array1);
    }
    @Test
    public void work3(){
        int[] array={1,2,3,4,5};
        int[] array1={1,2,3,4,5};
        new MergeSort().sort(array,0,array.length-1);
        Assert.assertArrayEquals(array,array1);
    }
    @Test
    public void empty(){
        int[] array={};
        int[] array1={};
        new MergeSort().sort(array,0,array.length-1);
        Assert.assertArrayEquals(array,array1);
    }
    @Test
    public void nul(){
        int[] array=null;
        int[] array1=null;
        new MergeSort().sort(array,0,0);
        Assert.assertArrayEquals(array,array1);
    }
}
