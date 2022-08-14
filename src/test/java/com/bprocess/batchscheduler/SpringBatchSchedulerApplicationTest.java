package com.bprocess.batchscheduler;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBatchSchedulerApplicationTest {

    @Test
    public void name() {

    }

    @Test
    public void contextLoads() {
        assertEquals(4, 4);
    }

}