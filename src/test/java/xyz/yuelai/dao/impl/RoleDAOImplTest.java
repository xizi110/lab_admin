package xyz.yuelai.dao.impl;

import org.hibernate.Session;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import xyz.yuelai.config.RootConfig;
import xyz.yuelai.config.ServletConfig;
import xyz.yuelai.config.WebInit;
import xyz.yuelai.dao.IRoleDAO;
import xyz.yuelai.pojo.domain.RoleDO;

import java.util.List;

/**
 * RoleDAOImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>七月 15, 2019</pre>
 */
@Transactional
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( classes = {RootConfig.class, ServletConfig.class})
public class RoleDAOImplTest {

    @Autowired
    private IRoleDAO roleDAO;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: save(RoleDO roleDO)
     */
    @Test
    public void testSave() throws Exception {
    }

    /**
     * Method: deleteById(RoleDO roleDO)
     */
    @Test
    public void testDeleteById() throws Exception {
    }

    /**
     * Method: update(RoleDO roleDO)
     */
    @Test
    public void testUpdate() throws Exception {
    }

    /**
     * Method: getById(Long id)
     */
    @Test
    public void testGetById() throws Exception {
    }

    /**
     * Method: listByName(String username)
     */
    @Test
    public void testListByName() throws Exception {
        List<RoleDO> roleDOList = roleDAO.listByUserId(1L);
        System.out.println("roleDOList = "+roleDOList);
    }

    /**
     * Method: listBySQL(String sql)
     */
    @Test
    public void testListBySQL() throws Exception {
    }


} 
