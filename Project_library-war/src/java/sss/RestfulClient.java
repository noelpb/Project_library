package sss;


import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Noel
 */
@Path("hello")
public class RestfulClient {
    @GET
    @Produces("text/plain")
    public String hello(){
    return "it works";
    }
   // @POST
  }
