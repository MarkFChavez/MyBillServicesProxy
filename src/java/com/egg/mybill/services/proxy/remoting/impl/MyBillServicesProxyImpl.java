/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.mybill.services.proxy.remoting.impl;

import com.egg.mybill.services.proxy.ServiceResponse;
import com.egg.mybill.services.proxy.remoting.MyBillServicesProxy;
import com.egg.mybill.services.proxy.util.PropertiesUtil;
import com.egg.mybill.services.proxy.soap.DocInfo;
import com.egg.mybill.services.proxy.soap.DocListInfo;
import com.egg.mybill.services.proxy.soap.ObjectFactory;
import com.google.gson.Gson;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.BindingProvider;
import org.apache.log4j.Logger;
import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.ws.Dispatch;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.Service;
import java.io.StringReader;
import java.util.Calendar;

/**
 *
 * @author Mark Chavez
 */
public class MyBillServicesProxyImpl implements MyBillServicesProxy {

    private static Logger logger = Logger.getLogger(MyBillServicesProxy.class);
    
    public ServiceResponse testCall(String msisdn) {
        ServiceResponse response = new ServiceResponse();
        Gson gson = new Gson();
        
        response.setObj("Responded with MSISDN: " + msisdn);
        response.setDescription("SUCCESS");
        
        return response;
    }

    public ServiceResponse dummyGetBillingList() {
        ServiceResponse resp = new ServiceResponse();
        DocInfo[] docInfos = new DocInfo[4];
        
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
        
        DocInfo infoThree = new DocInfo();
        infoThree.setAccountNo(3333333);
        infoThree.setDocId(33);
        
        try {
           XMLGregorianCalendar gregCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
           infoThree.setPeriodCoverageStartDate(gregCalendar);
           infoThree.setPeriodCoverageEndDate(gregCalendar);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        DocInfo infoFour = new DocInfo();
        infoFour.setAccountNo(4444444);
        infoFour.setDocId(44);
        
        try {
           XMLGregorianCalendar gregCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
           infoFour.setPeriodCoverageStartDate(gregCalendar);
           infoFour.setPeriodCoverageEndDate(gregCalendar);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        docInfos[0] = infoOne;
        docInfos[1] = infoTwo;
        docInfos[2] = infoThree;
        docInfos[3] = infoFour;
        
        String jsonFormat = convertToJSON(docInfos);
        resp.setObj(jsonFormat);
        resp.setDescription("GetBillingList");
        
        return resp;
    }

    public ServiceResponse getBillingList(String msisdn, int pageSize, int pageNumber, Integer numRows) {
        ServiceResponse response = new ServiceResponse();
        Gson gson = new Gson();
        
        try {
            
            com.egg.mybill.services.proxy.soap.BillingService service = new com.egg.mybill.services.proxy.soap.BillingService();
            com.egg.mybill.services.proxy.soap.BillingProxyService port = service.getBillingServicePort();
            
            BindingProvider bp = (BindingProvider) port;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, PropertiesUtil.getProperty("url.endpoint"));
           
            com.egg.mybill.services.proxy.soap.GetBillingList parameters = new com.egg.mybill.services.proxy.soap.GetBillingList();
            javax.xml.ws.Holder<com.egg.mybill.services.proxy.soap.GetBillingListResponse> result = new javax.xml.ws.Holder<com.egg.mybill.services.proxy.soap.GetBillingListResponse>();
            javax.xml.ws.Holder<com.egg.mybill.services.proxy.soap.WarcraftHeader> warcraftHeader = new javax.xml.ws.Holder<com.egg.mybill.services.proxy.soap.WarcraftHeader>();
            
            logger.debug("WSDL LOCATION >>> " + service.getWSDLDocumentLocation());
            
//            ObjectFactory objectFactory = new ObjectFactory();
            
            GregorianCalendar c1 = new GregorianCalendar();
            Calendar cal1 = Calendar.getInstance();
            cal1.set(2013, 0, 1, 14, 16, 46);
            c1.setTime(cal1.getTime());

            GregorianCalendar c2 = new GregorianCalendar();
            Calendar cal2 = Calendar.getInstance();
            cal2.set(2014, 0, 1, 14, 16, 46);
            c2.setTime(cal2.getTime());

            XMLGregorianCalendar gregCalendar1 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c1);
            XMLGregorianCalendar gregCalendar2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c2);
            
            //set parameters here...
            parameters.setMSISDN(msisdn);
            parameters.setPageSize(pageSize);
            parameters.setPageNumber(pageNumber);
            parameters.setFromDate(gregCalendar1);
            parameters.setToDate(gregCalendar2);
            parameters.setNumRows(numRows);
            
            logger.debug("MSISDN >>> " + parameters.getMSISDN());
            logger.debug("PAGE SIZE >>> " + parameters.getPageSize());
            logger.debug("PAGE NUMBER >>> " + parameters.getPageNumber());
            logger.debug("FROM DATE >>> " + parameters.getFromDate());
            logger.debug("TO DATE >>> " + parameters.getToDate());
            logger.debug("NUM ROWS >>> " + parameters.getNumRows());
            
            port.getBillingList(parameters, result, warcraftHeader);
            
            logger.debug("WarcraftHeader >>> " + warcraftHeader.value);
           
            //response
            com.egg.mybill.services.proxy.soap.GetBillingListResponse callResponse = result.value;
            com.egg.mybill.services.proxy.soap.GetBillingListResult callResult = callResponse.getGetBillingListResult();
            
            logger.debug("Call Result >>> " + gson.toJson(callResult));
            
            if(callResult != null) {
                DocListInfo docInfo = callResult.getDocListInfo();
  
                response.setObj(gson.toJson(callResult) + " - " + ((docInfo != null) ? "DOCINFO IS NOT NULL" : "DOCINFO IS NULL"));
                response.setDescription("SUCCESS");
            } else {
                response.setDescription("ERROR");
            }
            
        } catch(Exception e) {
            e.printStackTrace();
            response.setDescription("STACK TRACE >>> " + e.getMessage());
        }
        
        return response;
    }
    
    public ServiceResponse retrieveBillingStatement(String msisdn, String billId, boolean isConvertible, 
            boolean isFullBill, boolean isImmediate, Integer pageSize, Integer pageNumber, Integer numberOfRows, 
            String absolutePath, String fileName) {
        
        ServiceResponse response = new ServiceResponse();
        Gson gson = new Gson();
        
        try {
          
            com.egg.mybill.services.proxy.soap.BillingService service = new com.egg.mybill.services.proxy.soap.BillingService();
            com.egg.mybill.services.proxy.soap.BillingProxyService port = service.getBillingServicePort();
    
            BindingProvider bp = (BindingProvider) port;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, PropertiesUtil.getProperty("url.endpoint"));
            
            com.egg.mybill.services.proxy.soap.RetrieveBillingStatement parameters = new com.egg.mybill.services.proxy.soap.RetrieveBillingStatement();
            javax.xml.ws.Holder<com.egg.mybill.services.proxy.soap.RetrieveBillingStatementResponse> result = new javax.xml.ws.Holder<com.egg.mybill.services.proxy.soap.RetrieveBillingStatementResponse>();
            javax.xml.ws.Holder<com.egg.mybill.services.proxy.soap.WarcraftHeader> warcraftHeader = new javax.xml.ws.Holder<com.egg.mybill.services.proxy.soap.WarcraftHeader>();
            
            ObjectFactory objectFactory = new ObjectFactory();
            
            //set parameters here...
            parameters.setMSISDN(msisdn);
            if(billId != null)
                parameters.setBillId(objectFactory.createRetrieveBillingStatementBillId(billId));
            parameters.setConvertible(isConvertible);
            parameters.setFullBill(isFullBill);
            parameters.setImmediate(isImmediate);
            parameters.setPageSize(pageSize);
            parameters.setPageNumber(pageNumber);
            parameters.setNumberOfRows(numberOfRows);
            parameters.setAbsolutePath(objectFactory.createRetrieveBillingStatementAbsolutePath(absolutePath));
            parameters.setFilename(objectFactory.createRetrieveBillingStatementFilename(fileName));
           
            logger.debug("MSISDN >>> " + parameters.getMSISDN());
            logger.debug("Convertible >>> " + parameters.isConvertible());
            logger.debug("Full Bill >>> " + parameters.isFullBill());
            logger.debug("Immediate >>> " + parameters.isImmediate());
            logger.debug("Page Size >>> " + parameters.getPageSize());
            logger.debug("Page Number >>> " + parameters.getPageNumber());
            logger.debug("Number Of Rows >>> " + parameters.getNumberOfRows());
            logger.debug("Absolute Path >>> " + parameters.getAbsolutePath().getValue());
            logger.debug("Filename >>> " + parameters.getFilename().getValue());
            
            port.retrieveBillingStatement(parameters, result, warcraftHeader);
            
            //response
            com.egg.mybill.services.proxy.soap.RetrieveBillingStatementResponse callResponse = result.value;
            com.egg.mybill.services.proxy.soap.RetrieveBillingStatementResult callResult = callResponse.getRetrieveBillingStatementResult();
            
            logger.debug("CALL RESULT >>> " + callResult);
            
            if(callResult != null) {
                response.setObj(gson.toJson(callResult));
                response.setDescription("SUCCESS");
            } else {
                response.setDescription("ERROR");
            }
            
        } catch(Exception e) {
            e.printStackTrace();
            response.setDescription("STACK TRACE >>> " + e.getMessage());
        }
        
        return response;
        
//        ServiceResponse response = new ServiceResponse();
//        
//        com.egg.mybill.services.proxy.wsdl.RetrieveBillingStatementResult result = new com.egg.mybill.services.proxy.wsdl.RetrieveBillingStatementResult();
//        
//        result.setOperationStatus(0);
//        result.setReprintResponse(999);
//        
//        String jsonFormat = convertToJSON(result);
//        
//        response.setObj(jsonFormat);
//        response.setDescription("RetrieveBillingStatement");
//        
//        return response;
    }
    
    private String convertToJSON(Object obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

}
