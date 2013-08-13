/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.mybill.services.proxy;

/**
 *
 * @author Mark Chavez
 */
public class RBSResponse implements java.io.Serializable {
    
    private String errorMsg;
    private int operationStatus;
    private int reprintResponse;
    
    public String getErrorMsg() {
        return errorMsg;
    }
    
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
    
    public int getOperationStatus() {
        return operationStatus;
    }
    
    public void setOperationStatus(int operationStatus) {
        this.operationStatus = operationStatus;
    }
    
    public int getReprintResponse() {
        return reprintResponse;
    }
    
    public void setReprintResponse(int reprintResponse) {
        this.reprintResponse = reprintResponse;
    }
    
}
