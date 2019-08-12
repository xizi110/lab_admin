package xyz.yuelai.dao.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import xyz.yuelai.config.RootConfig;
import xyz.yuelai.config.ServletConfig;
import xyz.yuelai.dao.IEventDAO;
import xyz.yuelai.pojo.domain.EventDO;
import xyz.yuelai.pojo.dto.in.EventFormDTO;

import java.util.List;

/**
 * EventDAOImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>���� 12, 2019</pre>
 */

@Transactional
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( classes = {RootConfig.class, ServletConfig.class})
public class EventDAOImplTest {

    @Autowired
    private IEventDAO eventDAO;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: save(EventDO eventDO)
     */
    @Test
    public void testSave() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: deleteById(EventDO eventDO)
     */
    @Test
    public void testDeleteById() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: update(EventDO eventDO)
     */
    @Test
    public void testUpdate() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getById(Long id)
     */
    @Test
    public void testGetById() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: list(EventFormDTO formDTO)
     */
    @Test
    public void testList() throws Exception {
        EventFormDTO eventFormDTO = new EventFormDTO();
        eventFormDTO.setPage(0);
        List<EventDO> list = eventDAO.list(eventFormDTO);
        for (EventDO eventDO : list) {
            System.out.println(eventDO.getContent());
        }
        System.out.println(list);

//TODO: Test goes here...
    }


} 
