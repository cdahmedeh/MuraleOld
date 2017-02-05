package net.cdahmedeh.murale.util.net;

import com.sun.xml.internal.bind.v2.util.QNameMap;
import lombok.Getter;
import lombok.Setter;
import net.cdahmedeh.murale.error.InternetConnectionException;
import net.cdahmedeh.murale.util.net.NetTools;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Created by cdahmedeh on 1/28/2017.
 */
public abstract class GetRequest {
    protected String responseContent = null;

    public GetRequest() {

    }

    public abstract String getUrl();

    public abstract Map<String, String> getHeaders();

    public String get() {
        if (responseContent == null) {
            try {
                HttpGet request = new HttpGet(getUrl());

                for (Entry<String, String> header : getHeaders().entrySet()) {
                    request.setHeader(header.getKey(), header.getValue());
                }

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
