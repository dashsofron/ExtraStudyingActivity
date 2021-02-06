package ru.nsu.fit.g18208.Sofronova;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class QuickTests {
    @Test
    public void work1(){
        int[] array={1};
        int[] array1={1};
        new QuickSort().sort(array,0,array.length-1);
        Assert.assertArrayEquals(array,array1);
    }
    @Test
    public void work2(){
        int[] array={1,2};
        int[] array1={1,2};
        new QuickSort().sort(array,0,array.length-1);
        Assert.assertArrayEquals(array,array1);
    }
    @Test
    public void work3(){
        int[] array={2,1};
        int[] array1={1,2};
        new QuickSort().sort(array,0,array.length-1);
        Assert.assertArrayEquals(array,array1);
    }
    @Test
    public void work4(){
        int[] array={7,6,5,4,3,2,1};
        int[] array1={1,2,3,4,5,6,7};
        new QuickSort().sort(array,0,array.length-1);
        Assert.assertArrayEquals(array,array1);
    }
    @Test
    public void work5(){
        int[] array={7,6,6,4,3,2,3};
        int[] array1={2,3,3,4,6,6,7};
        new QuickSort().sort(array,0,array.length-1);
        Assert.assertArrayEquals(array,array1);
    }
    @Test
    public void work6(){
        int[] array={1,2,3,4,3,2,1};
        int[] array1={1,1,2,2,3,3,4};
        new QuickSort().sort(array,0,array.length-1);
        Assert.assertArrayEquals(array,array1);
    }
    @Test
    public void work7(){
        int[] array={1,2,3,4,5,2,1};
        int[] array1={1,1,2,2,3,4,5};
        new QuickSort().sort(array,0,array.length-1);
        Assert.assertArrayEquals(array,array1);
    }
    @Test
    public void work8(){
        int[] array={5,6,7,4,3,2,1};
        int[] array1={1,2,3,4,5,6,7};
        new QuickSort().sort(array,0,array.length-1);
        Assert.assertArrayEquals(array,array1);
    }
    @Test
    public void empty(){
        int[] array={};
        int[] array1={};
        new QuickSort().sort(array,0,array.length-1);
        Assert.assertArrayEquals(array,array1);
    }
    @Test
    public void nul(){
        int[] array=null;
        int[] array1=null;
        new QuickSort().sort(array,0,9);
        Assert.assertArrayEquals(array,array1);
    }
}
