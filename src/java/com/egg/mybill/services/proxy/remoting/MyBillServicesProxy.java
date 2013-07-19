/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.mybill.services.proxy.remoting;

import com.egg.mybill.services.proxy.ServiceResponse;

/**
 *
 * @author Mark Chavez
 */
public interface MyBillServicesProxy {
    
    public ServiceResponse sayHello(String message);
    public ServiceResponse getBillingList(String msisdn, String fromDate, String toDate);
    
}
