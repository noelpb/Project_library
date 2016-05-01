/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import javax.ejb.Local;

/**
 *
 * @author Nudista
 */
@Local
public interface InfoDatabseLocal {

    String getDatabaseInfo();

    String getInfoUsers();

    String getInfoOrders();

    String getInfoBooks();

    String test();
    
}
