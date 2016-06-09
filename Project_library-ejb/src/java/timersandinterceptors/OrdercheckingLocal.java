/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timersandinterceptors;

import java.util.Date;
import javax.ejb.Local;

/**
 *
 * @author Nudista
 */
@Local
public interface OrdercheckingLocal {
    
    public void myTimer();

    String checkOrder(Date today);
}
