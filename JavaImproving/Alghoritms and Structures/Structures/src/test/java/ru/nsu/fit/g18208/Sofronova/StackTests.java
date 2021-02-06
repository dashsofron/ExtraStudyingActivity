package ru.nsu.fit.g18208.Sofronova;

import org.junit.Assert;
import org.junit.Test;

public class StackTests {
    @Test
    public void correctWork1(){
        Stack<Integer> stack=new Stack<Integer>();
        stack.push(5);
        Assert.assertEquals(1,stack.count());
        Assert.assertEquals(5,(int)stack.pop());
    }
    @Test
    public void correctWork2(){
        Stack<String> stack=new Stack<String>();
        stack.push("мама");
        stack.push("мыла");
        stack.push("раму");
        Assert.assertEquals(3,stack.count());
        Assert.assertEquals("раму",stack.pop());
        Assert.assertEquals("мыла",stack.pop());
        Assert.assertEquals("мама",stack.pop());
    }
}
