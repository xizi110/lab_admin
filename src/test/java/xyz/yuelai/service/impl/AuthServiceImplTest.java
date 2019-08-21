package xyz.yuelai.service.impl; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After; 
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import xyz.yuelai.config.RootConfig;
import xyz.yuelai.config.ServletConfig;
import xyz.yuelai.pojo.dto.ResponseDTO;
import xyz.yuelai.service.IAuthService;

/** 
 * AuthServiceImpl Tester. 
 * 
 * @author lizezhong 
 * @since <pre>08/20/2019</pre> 
 */ 
 
@Transactional
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( classes = {RootConfig.class, ServletConfig.class})
public class AuthServiceImplTest {

    @Autowired
    private IAuthService authService;

    @Before
    public void before() throws Exception { 
    } 
    
    @After
    public void after() throws Exception { 
    } 

    /** 
     * 
     * Method: login(String username, String password) 
     * 
     */ 
    @Test
    public void testLogin() throws Exception { 
        //TODO: Test goes here... 
    } 
    
    /** 
     * 
     * Method: register(String username, String password, String vcode) 
     * 
     */ 
    @Test
    public void testRegister() throws Exception {
        ResponseDTO register = authService.register("test", "test", "123");
        System.out.println(register);
    } 
    

} 
