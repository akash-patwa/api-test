package io.apitest.example.controller;

import io.apitest.example.enums.CustomerStatus;
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
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer payload) throws CustomerException {
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
        if(payload.getStatus() == null)
            payload.setStatus(CustomerStatus.INACTIVE);
        return new ResponseEntity<Customer>(customerService.saveCustomer(payload), HttpStatus.OK);
    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") long id) throws CustomerException{
        logger.info("Customer id to return " + id);
        Customer customer = customerService.getCustomerById(id);
        if (customer == null || customer.getId() <= 0){
            throw new CustomerException("Customer doesn't exist");
        }
        return new ResponseEntity<Customer>(customerService.getCustomerById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Response> removeCustomerById(@PathVariable("id") long id) throws CustomerException{
        logger.info("Customer id to remove " + id);
        Customer customer = customerService.getCustomerById(id);
        if (customer == null || customer.getId() <= 0){
            throw new CustomerException("Customer to delete doesn't exist");
        }
        customerService.removeCustomer(customer);
        return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), "Customer has been deleted"), HttpStatus.OK);
    }

    @RequestMapping(value = "/customer", method = RequestMethod.PATCH)
    public ResponseEntity<Customer>  updateCustomer(@RequestBody Customer payload) throws CustomerException{
        logger.info("Payload to update " + payload);
        Customer customer = customerService.getCustomerById(payload.getId());
        if (customer == null || customer.getId() <= 0){
            throw new CustomerException("Customer to update doesn't exist");
        }
        return new ResponseEntity<Customer>(customerService.saveCustomer(payload), HttpStatus.OK);
    }

    @RequestMapping(value="/customer", method=RequestMethod.GET)
    public ResponseEntity<List<Customer>> getAllCustomers(){
        logger.info("Returning all the Customers");
        return new ResponseEntity<List<Customer>>(customerService.getAllCustomer(), HttpStatus.OK);
    }

}
