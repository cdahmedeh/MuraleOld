package net.cdahmedeh.murale.util.net;

import net.cdahmedeh.murale.error.InternetConnectionException;
import org.apache.commons.io.IOUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by cdahmedeh on 1/28/2017.
 */
public abstract class PostRequest {
    protected String responseContent = null;

    public PostRequest() {

    }

    public abstract String getUrl();

    public abstract Map<String, String> getHeaders();

    public abstract Map<String, String> getParams();

    public String get() {
        if (responseContent == null) {
            try {
                HttpPost request = new HttpPost(getUrl());

                for (Map.Entry<String, String> header : getHeaders().entrySet()) {
                    request.setHeader(header.getKey(), header.getValue());
                }

                List<NameValuePair> postParams = new ArrayList<>();
                for (Map.Entry<String, String> param : getParams().entrySet()) {
                    postParams.add(new BasicNameValuePair(param.getKey(), param.getValue()));
                }
                request.setEntity(new UrlEncodedFormEntity(postParams));

                CloseableHttpClient client = NetTools.createHttpClient();

                CloseableHttpResponse response = client.execute(request);

                responseContent = IOUtils.toString(response.getEntity().getContent());
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                throw new InternetConnectionException("Unable to make HTTP request", ex);
            }
        }

        return responseContent;
    }
}
