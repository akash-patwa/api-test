package io.apitest.example.integrationtest;

import cucumber.api.java.Before;
import io.apitest.example.CustomerApp;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@ContextConfiguration(classes = CustomerApp.class)
@WebAppConfiguration
public class CustomerAppBaseIntegrationTest {

    protected MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext wac;

}
