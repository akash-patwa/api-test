package io.apitest.example.util;

import io.apitest.example.model.Customer;
import org.springframework.stereotype.Service;

/**
 * Created by prasantabiswas on 29/06/18.
 */

@Service
public class PayloadValidator {

    public int validateCreateCustomerPayload(Customer customer) {
        if (customer.getId() > 0 || customer.getId() < 0){
            return -1;
        }
        return 0;
    }
}
