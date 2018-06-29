package io.apitest.example.util;

import io.apitest.example.interfaces.ViewService;
import io.apitest.example.interfaces.WorkflowService;
import io.apitest.example.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by prasantabiswas on 29/06/18.
 */

@Service
public class PayloadValidator {

    @Autowired
    private ViewService viewService;

    @Autowired
    private WorkflowService workflowService;

    public int validateCreateCustomerPayload(Customer customer) {
        if (customer.getId() > 0 || customer.getId() < 0){
            return -1;
        }
        else if(viewService.getViewById(customer.getViewId()) == null) {
            System.out.println("All views: "+viewService.getAllView());
            return -2;
        }
        else if(workflowService.getWorkflowById(customer.getWorkflowId()) == null) {
            return -3;
        }
        return 0;
    }
}
