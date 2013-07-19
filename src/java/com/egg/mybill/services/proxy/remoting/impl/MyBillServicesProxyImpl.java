/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.mybill.services.proxy.remoting.impl;

import com.egg.mybill.services.proxy.ServiceResponse;
import com.egg.mybill.services.proxy.remoting.MyBillServicesProxy;
import com.egg.mybill.services.proxy.wsdl.DocInfo;
import com.google.gson.Gson;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author Mark Chavez
 */
public class MyBillServicesProxyImpl implements MyBillServicesProxy {

    public ServiceResponse sayHello(String message) {
        ServiceResponse response = new ServiceResponse();
        response.setDescription(message);
        
        return response;
    }

    public ServiceResponse getBillingList(String msisdn, String fromDate, String toDate) {
//        MyBillResponse response = new MyBillResponse();
//        
//        try {
//            
//            com.egg.mybill.services.proxy.wsdl.BillingService service = new com.egg.mybill.services.proxy.wsdl.BillingService();
//            com.egg.mybill.services.proxy.wsdl.BillingProxyService port = service.getBillingServicePort();
//        
//            BindingProvider bp = (BindingProvider) port;
//            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, PropertiesUtil.getProperty("url.endpoint"));
//            
//            com.egg.mybill.services.proxy.wsdl.GetBillingList parameters = new com.egg.mybill.services.proxy.wsdl.GetBillingList();
//            javax.xml.ws.Holder<com.egg.mybill.services.proxy.wsdl.GetBillingListResponse> result = new javax.xml.ws.Holder<com.egg.mybill.services.proxy.wsdl.GetBillingListResponse>();
//            javax.xml.ws.Holder<com.egg.mybill.services.proxy.wsdl.WarcraftHeader> warcraftHeader = new javax.xml.ws.Holder<com.egg.mybill.services.proxy.wsdl.WarcraftHeader>();
//            
//            //set parameters here...
//            parameters.setMSISDN(msisdn);
//            parameters.setFromDate(fromDate);
//            parameters.setToDate(toDate);
//            
//            port.getBillingList(parameters, result, warcraftHeader);
//            
//            //response
//            com.egg.mybill.services.proxy.wsdl.GetBillingListResponse callResponse = result.value;
//            com.egg.mybill.services.proxy.wsdl.GetBillingListResult callResult = callResponse.getGetBillingListResult();
//            
//            if(callResult != null) {
//                if(callResult.getDocListInfo() != null) {
//                    if(callResult.getDocListInfo().getDocList() != null) {
//                        response.setMessage("DOC ID >>> " + callResult.getDocListInfo().getDocList().getDocId());
//                    } else {
//                        response.setMessage("callResult[DocListInfo][DocList] >>> " + callResult.getDocListInfo().getDocList());
//                    }
//                } else {
//                    response.setMessage("callResult[DocListInfo] >>> " + callResult.getDocListInfo());
//                }
//            } else {
//                response.setMessage("callResult is " + callResult);
//            }
//            
//        } catch(Exception e) {
//            e.printStackTrace();
//            response.setMessage("STACK TRACE >>> " + e.getMessage());
//        }
//        
//        return response;
        
        ServiceResponse resp = new ServiceResponse();
        DocInfo[] docInfos = new DocInfo[2];
        
        DocInfo infoOne = new DocInfo();
        infoOne.setAccountNo(1111111);
        infoOne.setDocId(11);
        
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(new Date());
        
        try {
           XMLGregorianCalendar gregCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
           infoOne.setPeriodCoverageStartDate(gregCalendar);
           infoOne.setPeriodCoverageEndDate(gregCalendar);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        DocInfo infoTwo = new DocInfo();
        infoTwo.setAccountNo(2222222);
        infoTwo.setDocId(22);
        
        try {
           XMLGregorianCalendar gregCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
           infoTwo.setPeriodCoverageStartDate(gregCalendar);
           infoTwo.setPeriodCoverageEndDate(gregCalendar);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        docInfos[0] = infoOne;
        docInfos[1] = infoTwo;
        
        String jsonFormat = convertToJSON(docInfos);
        resp.setObj(jsonFormat);
        resp.setDescription("GetBillingList");
        
        return resp;
    }
    
    private String convertToJSON(Object obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

}
