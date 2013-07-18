/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.mybill.services.proxy.remoting.impl;

import com.egg.mybill.services.proxy.MyBillResponse;
import com.egg.mybill.services.proxy.remoting.MyBillServicesProxy;
import com.egg.mybill.services.proxy.util.PropertiesUtil;
import javax.xml.ws.BindingProvider;

/**
 *
 * @author Mark Chavez
 */
public class MyBillServicesProxyImpl implements MyBillServicesProxy {

    public MyBillResponse sayHello(String message) {
        MyBillResponse response = new MyBillResponse();
        response.setMessage(message);
        
        return response;
    }

    public MyBillResponse getBillingList(String msisdn, String fromDate, String toDate) {
        MyBillResponse response = new MyBillResponse();
        
        try {
            
            com.egg.mybill.services.proxy.wsdl.BillingService service = new com.egg.mybill.services.proxy.wsdl.BillingService();
            com.egg.mybill.services.proxy.wsdl.BillingProxyService port = service.getBillingServicePort();
        
            ((BindingProvider) port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, PropertiesUtil.getProperty("url.endpoint"));
            
            com.egg.mybill.services.proxy.wsdl.GetBillingList parameters = new com.egg.mybill.services.proxy.wsdl.GetBillingList();
            javax.xml.ws.Holder<com.egg.mybill.services.proxy.wsdl.GetBillingListResponse> result = new javax.xml.ws.Holder<com.egg.mybill.services.proxy.wsdl.GetBillingListResponse>();
            javax.xml.ws.Holder<com.egg.mybill.services.proxy.wsdl.WarcraftHeader> warcraftHeader = new javax.xml.ws.Holder<com.egg.mybill.services.proxy.wsdl.WarcraftHeader>();
            
            //set parameters here...
            parameters.setMSISDN(msisdn);
            parameters.setFromDate(fromDate);
            parameters.setToDate(toDate);
            
            port.getBillingList(parameters, result, warcraftHeader);
            
            //response
            com.egg.mybill.services.proxy.wsdl.GetBillingListResponse callResponse = result.value;
            com.egg.mybill.services.proxy.wsdl.GetBillingListResult callResult = callResponse.getGetBillingListResult();
            
            if(callResult != null) {
                if(callResult.getDocListInfo() != null) {
                    if(callResult.getDocListInfo().getDocList() != null) {
                        response.setMessage("DOC ID >>> " + callResult.getDocListInfo().getDocList().getDocId());
                    } else {
                        response.setMessage("callResult[DocListInfo][DocList] >>> " + callResult.getDocListInfo().getDocList());
                    }
                } else {
                    response.setMessage("callResult[DocListInfo] >>> " + callResult.getDocListInfo());
                }
            } else {
                response.setMessage("callResult is " + callResult);
            }
            
        } catch(Exception e) {
            e.printStackTrace();
            response.setMessage("STACK TRACE >>> " + e.getMessage());
        }
        
        return response;
    }

    public MyBillResponse retrieveBillingStatement(String msisdn, String fromDate, String toDate) {
        
        MyBillResponse response = new MyBillResponse();
        
        try {
            com.egg.mybill.services.proxy.wsdl.BillingService service = new com.egg.mybill.services.proxy.wsdl.BillingService();
            com.egg.mybill.services.proxy.wsdl.BillingProxyService port = service.getBillingServicePort();
            
            ((BindingProvider) port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, PropertiesUtil.getProperty("url.endpoint"));
            
            com.egg.mybill.services.proxy.wsdl.RetrieveBillingStatement parameters = new com.egg.mybill.services.proxy.wsdl.RetrieveBillingStatement();
            javax.xml.ws.Holder<com.egg.mybill.services.proxy.wsdl.RetrieveBillingStatementResponse> result = new javax.xml.ws.Holder<com.egg.mybill.services.proxy.wsdl.RetrieveBillingStatementResponse>();
            javax.xml.ws.Holder<com.egg.mybill.services.proxy.wsdl.WarcraftHeader> warcraftHeader = new javax.xml.ws.Holder<com.egg.mybill.services.proxy.wsdl.WarcraftHeader>();
            
            parameters.setMSISDN(msisdn);
            parameters.setFromDate(null);
            parameters.setToDate(null);
            
            port.retrieveBillingStatement(parameters, result, warcraftHeader);
            
            //response
            com.egg.mybill.services.proxy.wsdl.RetrieveBillingStatementResponse callResponse = result.value;
            com.egg.mybill.services.proxy.wsdl.RetrieveBillingStatementResult callResult = callResponse.getRetrieveBillingStatementResult();
            
            if(callResult != null) {
                response.setMessage("OPERATION STATUS >> " + callResult.getOperationStatus());
            } else {
                response.setMessage("CALL RESULT >> " + callResult);
            }
            
        } catch(Exception e) {
            e.printStackTrace();
            response.setMessage("STACK TRACE >>> " + e.getMessage());
        }
        
        return response;
    }

}
