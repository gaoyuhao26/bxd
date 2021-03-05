package com.softeem.service;

import com.github.pagehelper.PageInfo;
import com.softeem.dto.requestDTO.PositionListRequestDTO;
import com.softeem.entity.Position;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.w3c.dom.ls.LSOutput;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:spring-mvc.xml"})
public class PositionServiceTest {
    @Autowired
    private PositionService positionService;
    @Test
    public void queryPageList() {
        PositionListRequestDTO positionListRequestDTO = new PositionListRequestDTO();
        PageInfo<Position> positionPageInfo = positionService.queryPageList(positionListRequestDTO);
        List<Position> list = positionPageInfo.getList();
        System.out.println("-----------------------------------");
        list.stream().forEach(System.out::println);
    }
    @Test
    public void toggleStatus() {
        Position position = new Position();
        position.setPositionId(1);
        position.setStatus(1);
        boolean b = positionService.toggleStatus(position);
        System.out.println(b);
    }


//    @Test
//    public void insert() {
//        Position position = new Position();
//        position.setPositionName("老板");
//        position.setStatus(1);
//        Position insert = positionService.insert(position);
//        System.out.println(insert);
//    }
}