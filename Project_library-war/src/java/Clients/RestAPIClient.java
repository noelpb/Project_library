/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

/**
 *
 * @author Noel
 */
@ApplicationScoped
@ManagedBean
public class RestAPIClient {

    public static void main(String[] args) throws MalformedURLException, IOException {
        Client client = ClientBuilder.newClient();
        URL whatismyip = new URL("http://checkip.amazonaws.com");
        BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));

        String ip = in.readLine(); //you get the IP as a String

       
        System.out.println(ip);
        WebTarget target = client.target("http://geo.groupkt.com/ip/"+ip+"/json");
        Invocation.Builder request = target.request();
        Response res = request.get();
        JsonObject msg;
        msg = res.readEntity(JsonObject.class);
        System.out.println(msg);

    }

}
