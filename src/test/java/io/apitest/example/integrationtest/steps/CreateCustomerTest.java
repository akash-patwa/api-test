package io.apitest.example.integrationtest.steps;

import com.google.gson.Gson;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.apitest.example.enums.CustomerStatus;
import io.apitest.example.integrationtest.CustomerAppBaseIntegrationTest;
import io.apitest.example.model.Customer;
import jdk.nashorn.internal.objects.Global;
import jdk.nashorn.internal.parser.JSONParser;
import jdk.nashorn.internal.runtime.Context;
import jdk.nashorn.internal.runtime.ScriptObject;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by prasantabiswas on 29/06/18.
 */
public class CreateCustomerTest extends CustomerAppBaseIntegrationTest {

    private static int dbState = 0;
    private static String requestObject;
    private static final Logger logger = LoggerFactory.getLogger(CreateCustomerTest.class);
    private static final Gson gson = new Gson();

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Given("^The app database has dummy customer data$")
    public void verifyAppData() {
        logger.info("Verifying dummy data presence");
        dbState = customerService.getAllCustomer().size();
        Assert.assertTrue("No dummy data present",dbState > 0);
    }

    @And("^The client has customer data (.+),(.+),(.+),(.+),(.+),(.+)$")
    public void createRequestObject(String name, String address, boolean onboarded, CustomerStatus status, long viewId, long workflowId) {
        requestObject = "{" +
                    "\"name\":"+"\""+name+"\""+"," +
                    "\"address\":"+"\""+address+"\""+"," +
                    "\"onboarded\":"+onboarded+"," +
                    "\"status\":"+status.ordinal()+"," +
                    "\"viewId\":"+viewId+", " +
                    "\"workflowId\":"+workflowId+" " +
                "}";
        Customer customer = gson.fromJson(requestObject,Customer.class);
        logger.info("Request object created:\n"+ requestObject);
        Assert.assertTrue("Invalid request parameter",customer != null);
    }

    @When("^The client send POST using API (.+)$")
    public void sendPOSTRequest(String URL) throws Exception {
        logger.info("Execute POST request");
        resultActions = mockMvc.perform(MockMvcRequestBuilders.post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestObject)
                .accept(MediaType.APPLICATION_JSON));
        Assert.assertTrue(true);
    }

    @Then("^It should receive (.+) as HTTP status code$")
    public void verifyHTTPStatusCode(int status) throws Exception {
        resultActions.andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.address").exists())
                .andExpect(jsonPath("$.onboarded").exists())
                .andExpect(jsonPath("$.status").exists())
                .andExpect(jsonPath("$.viewId").exists())
                .andExpect(jsonPath("$.workflowId").exists())
                .andExpect(status().is(status))
                .andDo(print());
    }

    @And("^customer database should be updated$")
    public void verifyCustomerDatabase() {
        logger.info("Check customer database update");
        int newDbState = customerService.getAllCustomer().size();
        Assert.assertTrue("Creation customer verification failed", newDbState > dbState);
    }

    @And("^The client has customer data (.+),(.+),(.+),(.+),(.+)$")
    public void createRequestObject(String name, String address, CustomerStatus status, long viewId, long workflowId) {
        requestObject = "{" +
                "\"name\":"+"\""+name+"\""+"," +
                "\"address\":"+"\""+address+"\""+"," +
                "\"status\":"+status.ordinal()+"," +
                "\"viewId\":"+viewId+", " +
                "\"workflowId\":"+workflowId+" " +
                "}";
        Customer customer = gson.fromJson(requestObject,Customer.class);
        logger.info("Request object created:\n"+ requestObject);
        Assert.assertTrue("Invalid request parameter",customer != null);
    }
}
