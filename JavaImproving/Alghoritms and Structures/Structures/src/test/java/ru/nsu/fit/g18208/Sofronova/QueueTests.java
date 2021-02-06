package ru.nsu.fit.g18208.Sofronova;

import org.junit.Assert;
import org.junit.Test;


import org.junit.Assert;
        import org.junit.Test;

public class QueueTests {
    @Test
    public void correctWork1(){
        Queue<Integer> q=new Queue<Integer>();
        q.push(5);
        Assert.assertEquals(1,q.getSize());
        Assert.assertEquals(5,(int)q.pop());
    }
    @Test
    public void correctWork2(){
        Queue<String> q=new Queue<String>();
        q.push("мама");
        q.push("мыла");
        q.push("раму");
        Assert.assertEquals(3,q.getSize());
        Assert.assertEquals("мама",q.pop());
        Assert.assertEquals("мыла",q.pop());
        Assert.assertEquals("раму",q.pop());
    }
}
