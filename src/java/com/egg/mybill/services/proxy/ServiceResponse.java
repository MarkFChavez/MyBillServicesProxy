/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.mybill.services.proxy;

/**
 *
 * @author Mark Chavez
 */
public class ServiceResponse implements java.io.Serializable {
    
    private String obj;
    private String description;
    
    public String getObj() {
        return obj;
    }
    
    public void setObj(String obj) {
        this.obj = obj;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
}
