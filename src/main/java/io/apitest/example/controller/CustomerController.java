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
}
