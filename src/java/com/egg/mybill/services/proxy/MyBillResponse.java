/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.mybill.services.proxy;

import java.io.Serializable;

/**
 *
 * @author Mark Chavez
 */
public class MyBillResponse implements Serializable {
    
    private String message;
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
}
