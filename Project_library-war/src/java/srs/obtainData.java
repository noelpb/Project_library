/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package srs;

import beans.GetData;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 *
 * @author Noel
 */
@WebService(serviceName = "GetData")
public class obtainData {

    @EJB
    private GetData ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

    @WebMethod(operationName = "getPI")
    public Double getPI() {
        return ejbRef.getPI();
    }

    @WebMethod(operationName = "Message")
    public String Message() {
        return ejbRef.Message();
    }

    @WebMethod(operationName = "timeOfDay")
    public String timeOfDay() {
        return ejbRef.timeOfDay();
    }
    
}
