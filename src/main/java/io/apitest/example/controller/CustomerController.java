package io.apitest.example.controller;

import io.apitest.example.exception.CustomerException;
import io.apitest.example.interfaces.CustomerService;
import io.apitest.example.model.Customer;
import io.apitest.example.model.Response;
import io.apitest.example.util.PayloadValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by prasantabiswas on 27/06/18.
 */

@RestController
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    CustomerService customerService;

    @Autowired
    PayloadValidator payloadValidator;

    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    public ResponseEntity<Response> saveCustomer(@RequestBody Customer payload) throws CustomerException {
        logger.info("Payload to save " + payload);
        int result = payloadValidator.validateCreateCustomerPayload(payload);
        if (result == -1){
            throw new CustomerException("Payload malformed, id must not be defined");
        }
        else if(result == -2){
            throw new CustomerException("Specified view does not exists");
        }
        else if(result == -3){
            throw new CustomerException("Specified workflow does not exists");
        }

        customerService.saveCustomer(payload);
        return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(),"Customer data saved"), HttpStatus.OK);
    }

}
