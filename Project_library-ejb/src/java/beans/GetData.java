/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;

/**
 *
 * @author Noel
 */
@Singleton
@LocalBean
public class GetData {

    public Double getPI() {
        return Math.PI;
    }

    public String Message() {
        return "Any message";
    }

    public String timeOfDay() {
        String dataTime = "yyyy-MM-DD HH:mm:ss";
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(dataTime);
        return sdf.format(cal.getTime());
    }
}
