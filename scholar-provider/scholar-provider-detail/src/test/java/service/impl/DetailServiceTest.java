package service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import cn.scholar.service.DetailService;


@SpringBootTest
@RunWith(SpringRunner.class)
public class DetailServiceTest {

    @Autowired
    private DetailService detailService;

    @Test
    public void findAll(){
        detailService.findAll().forEach(System.out::println);
    }



}
