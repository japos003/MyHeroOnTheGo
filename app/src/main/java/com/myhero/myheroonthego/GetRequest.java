package com.myhero.myheroonthego;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

/**
 * Created by Raul Madrigal Jr on 3/4/2015.
 */
public class GetRequest {

    public String request(String URL) throws ClientProtocolException, IOException {
        // HttpGet represents the request that we are making
        HttpGet httpGet = new HttpGet(URL);
        // ResponseHandler handles the response from this request.
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        // HttpClient glues together the request and the response.
        HttpClient httpClient = new DefaultHttpClient();
        // make the request and response happen!
        String response = httpClient.execute(httpGet, responseHandler);
        // return the response.
        return response;
    }
}
