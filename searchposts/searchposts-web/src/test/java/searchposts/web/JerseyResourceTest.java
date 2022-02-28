/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchposts.web;

import com.github.tomakehurst.wiremock.WireMockServer;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import static junit.framework.Assert.assertEquals;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ruslan
 */
public class JerseyResourceTest {
    
    public JerseyResourceTest() {
    }

    /**
     * Test of getHTML method, of class JerseyResource.
     */
    @org.junit.Test
    public void testGetHTML() throws Exception {
        System.out.println("getHTML");
        String subject = "тест_тест_тест";
        JerseyResource instance = new JerseyResource();
        String expResult = "No subjects found";
        String result = instance.getHTML(subject);
        assertEquals(expResult, result);
    }
    
    @Test
    // ??? не совсем понятно что мне даст этот тест в текущей задаче.
    public void Test() throws Exception {
        WireMockServer wireMockServer = new WireMockServer(options().port(8081));
        wireMockServer.start();
        configureFor("127.0.0.1", 8081);
        stubFor(get(urlEqualTo("/test")).willReturn(aResponse().withBody("hello world!")));
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet("http://127.0.0.1:8081/test");
        HttpResponse httpResponse = httpClient.execute(request);
        String responseString = convertResponseToString(httpResponse);
        verify(getRequestedFor(urlEqualTo("/test")));
        assertEquals("hello world!", responseString);
        wireMockServer.stop();
    }
    
    private String convertResponseToString(HttpResponse response) throws IOException {
        InputStream responseStream = response.getEntity().getContent();
        String responseString;
        try (Scanner scanner = new Scanner(responseStream, "UTF-8")) {
            responseString = scanner.useDelimiter("\\Z").next();
        }
        return responseString;
    }
    
}
