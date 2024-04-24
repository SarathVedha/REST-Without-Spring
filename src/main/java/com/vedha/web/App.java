package com.vedha.web;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;

public class App {

    public static void invocationREST(String url) {

        // Create a Client object
        Client client = ClientBuilder.newClient();

        // Create a WebTarget object for the REST resource
        WebTarget webTarget = client.target(url);

        // Build the HTTP request
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

        // Invoke the HTTP request
        Response response = invocationBuilder.get();

        // Check the response status
        if (response.getStatus() == 200) {

            // Get the response body
            String responseBody = response.readEntity(String.class);

            // Print the response body
            System.out.println("invocationREST: " + responseBody);

        } else {

            // Handle the error response
            System.err.println("Error: " + response.getStatus());

        }

        // Close the client
        client.close();
    }

    public static void invocationSimpleREST(String url) {

        // Create a Client object
        Client client = ClientBuilder.newClient();

        Object response = client.target(url)
                .request(MediaType.APPLICATION_JSON)
//                .get(String.class);
        .get(HashMap.class);

        System.out.println("invocationSimpleREST: " + response);
    }

    public static void main(String[] args) {

        String url = "http://localhost:8080/api/v1/mail/test";
        invocationREST(url);
        invocationSimpleREST(url);
    }
}
