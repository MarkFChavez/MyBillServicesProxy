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
    
    public ServiceResponse testCall(String msisdn);
    public ServiceResponse dummyGetBillingList();
    public ServiceResponse getBillingList(String msisdn, int pageSize, int pageNumber, Integer numRows);
    public ServiceResponse retrieveBillingStatement(String msisdn, String billId, boolean isConvertible, 
            boolean isFullBill, boolean isImmediate, Integer pageSize, Integer pageNumber, Integer numberOfRows, 
            String absolutePath, String fileName);
    
}
