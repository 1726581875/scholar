package cn.scholar.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class NoticeServiceTest {

    @Autowired
    private NoticeService noticeService;

    @Test
    public void findAll(){
        noticeService.findAll().forEach(System.out::println);
    }

    @Test
    public void findAllById(){
        noticeService.findAllByAcceptId(2,1).forEach(System.out::println);
    }


}