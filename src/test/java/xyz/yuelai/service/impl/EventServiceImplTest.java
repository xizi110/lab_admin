package xyz.yuelai.service.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xyz.yuelai.config.RootConfig;
import xyz.yuelai.config.ServletConfig;
import xyz.yuelai.config.ShiroConfig;
import xyz.yuelai.pojo.domain.EventDO;
import xyz.yuelai.pojo.dto.ResponseDTO;
import xyz.yuelai.pojo.dto.in.EventFormDTO;
import xyz.yuelai.service.IEventService;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * EventServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>七月 29, 2019</pre>
 */

@Transactional(propagation = Propagation.REQUIRED)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( classes = {RootConfig.class, ServletConfig.class, ShiroConfig.class})
public class EventServiceImplTest {

    @Autowired
    private IEventService eventService;



    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void testSave() throws Exception{
        EventDO eventDO = new EventDO();
        eventDO.setTitle("biaoti");
        eventDO.setAuthor("xizi");
        eventDO.setPublishDate(Timestamp.valueOf(LocalDateTime.now()));
        eventDO.setBrief("简介");
        eventDO.setContent("内容");
        eventDO.setCarousel((byte)0);

        ResponseDTO save = eventService.save(eventDO);
        System.out.println(save);

    }

    /**
     * Method: list(int currentPage)
     */
    @Test
    public void testList() throws Exception {
        EventFormDTO eventFormDTO = new EventFormDTO();
//        eventFormDTO.setAuthor("xizi");
        eventFormDTO.setPage(0);
        ResponseDTO responseDTO = eventService.list(eventFormDTO);
        System.out.println(responseDTO);
    }

} 
