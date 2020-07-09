package ontice.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class MessageFactoryTest {

    @Autowired
    private MessageFactory messageFactory;

    @Autowired
    private NoticeService noticeService;


    @Test
    public void testGetHomeWork() {
     messageFactory.getHomeWorkMessage(1,1,"1111111111").forEach(System.out::println);
    }

    @Test
    public void insertAllNotice(){
     //   noticeService.insertAll(messageFactory.getHomeWorkMessage(1,1));
    }

}