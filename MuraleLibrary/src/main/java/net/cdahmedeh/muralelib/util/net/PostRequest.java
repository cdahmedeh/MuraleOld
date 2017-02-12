package net.cdahmedeh.muralelib.util.net;

import com.google.gson.Gson;
import net.cdahmedeh.muralelib.error.DataFormatException;
import net.cdahmedeh.muralelib.error.InternetConnectionException;
import net.cdahmedeh.muralelib.logging.Logging;
import org.apache.commons.io.IOUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Template for a JSON POST HTTP Request
 *
 * Created by cdahmedeh on 1/28/2017.
 */
public abstract class PostRequest {
    protected String responseContent = null;

    public abstract String getUrl();

    public Map<String, String> getHeaders() {
        return new HashMap<>();
    };

    public Map<String, String> getParams() {
        return new HashMap<>();
    };

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
                String message = "Unable to parse request";
                Logging.error(ex, message);
                throw new DataFormatException(message, ex);
            } catch (IOException ex) {
                String message = "Unable to make HTTP request";
                Logging.error(ex, message);
                throw new InternetConnectionException(message, ex);
            }
        }

        return responseContent;
    }

    public <E> E getJson(Class<E> clazz) {
        get();
        return new Gson().fromJson(responseContent, clazz);
    }
}
