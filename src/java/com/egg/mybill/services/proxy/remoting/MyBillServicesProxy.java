/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.mybill.services.proxy.remoting;

import com.egg.mybill.services.proxy.MyBillResponse;

/**
 *
 * @author Mark Chavez
 */
public interface MyBillServicesProxy {
    
    public MyBillResponse sayHello(String message);
    public MyBillResponse getBillingList(String msisdn, String fromDate, String toDate);
    public MyBillResponse retrieveBillingStatement(String msisdn, String fromDate, String toDate);
    
}
