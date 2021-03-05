package com.softeem.dao;

import com.softeem.entity.Position;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.Date;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class PositionDaoTest {
    @Autowired
    private PositionDao positionDao;
    @Test
    public void insert() {
        for(int i = 1 ; i<=50; i ++){
            Position position = new Position();
            position.setPositionName("普通员工");
            position.setCreateTime(new Date());
            position.setStatus(1);
            int insert = positionDao.insert(position);
            System.out.println("----------");
            System.out.println(insert);
        }

    }

    @Test
    public void selectPosition() {
        Position position = positionDao.SelectPosition();
        System.out.println(position);
    }
}