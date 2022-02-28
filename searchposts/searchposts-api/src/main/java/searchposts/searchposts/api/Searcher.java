/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchposts.searchposts.api;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.ws.rs.WebApplicationException;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.*;

/**
 *
 * @author Ruslan
 */
public class Searcher {

    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    public String getPostsBySubject(String subject) throws Exception {
        return sendGet(String.format("https://api.stackexchange.com/2.3/search?order=desc&sort=activity&intitle=%s&site=stackoverflow", subject));
    }

    private String sendGet(String url) throws Exception {
        HttpGet request = new HttpGet(url);
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            System.out.println(response.getStatusLine().toString());

            HttpEntity entity = response.getEntity();
            Header headers = entity.getContentType();

            if (entity != null) {
                // return it as a String
                String result = convertToHtml(EntityUtils.toString(entity));
                return result;
            }
        }
        throw new WebApplicationException("Request error");
    }

    private String convertToHtml(String baseData) {
        
        if (baseData == "" || baseData == null)
            return "Empty result";
        
        JSONObject obj = new JSONObject(baseData);        
        if (!obj.has("items"))
            return "Wrong request";    
        
        JSONArray arr = obj.getJSONArray("items");
        if (arr.length() == 0)
            return "No subjects found";
        
        String result = "<table border=\"1\"><tr><th>Created</th><th>Post</th><th>Author</th></tr>";
        for (int i = 0; i < arr.length(); i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(arr.getJSONObject(i).getLong("creation_date") * 1000);
            Date creationDate = calendar.getTime();
            String color = arr.getJSONObject(i).getBoolean("is_answered") == true ? "green" : "red";
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
            result += String.format("<tr bgcolor=\"%s\"><td>%s</td><td><a href=\"%s\">%s</a></td><td>%s</td></tr>\n",
                    color,
                    dateFormat.format(creationDate),
                    arr.getJSONObject(i).getString("link"),
                    arr.getJSONObject(i).getString("title"),
                    arr.getJSONObject(i).getJSONObject("owner").getString("display_name"));
            
        }
        result += "</table>";
        return result;
    }
}
