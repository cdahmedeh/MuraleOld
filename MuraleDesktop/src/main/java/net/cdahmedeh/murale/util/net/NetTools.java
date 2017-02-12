package net.cdahmedeh.murale.util.net;

import net.cdahmedeh.murale.app.AppConstants;
import net.cdahmedeh.murale.error.InternetConnectionException;
import net.cdahmedeh.murale.util.io.CloseableTools;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

import static net.cdahmedeh.murale.app.AppConstants.APP_CODENAME;
import static net.cdahmedeh.murale.app.AppConstants.APP_NAME;
import static net.cdahmedeh.murale.app.AppConstants.APP_VERSION;
import static org.apache.commons.codec.binary.Base64.encodeBase64String;

/**
 * Various methods for dealing with HTTP requests.
 *
 * Created by cdahmedeh on 1/22/2017.
 */
public class NetTools {
    public static final String USER_AGENT = APP_NAME + "/" + APP_VERSION;

    /**
     * Creates the value required for the 'Authentication' header if using
     * HTTP Basic Authentication.
     *
     * @param username The username to authenticate with.
     * @param password The password to authenticate with.
     * @return The value for the 'Authentication' header.
     */
    public static String generateBasicAuthHeader(String username, String password) {
        String userPass = username + ":" + password;
        return "Basic" + " " + encodeBase64String(userPass.getBytes());
    }

    /**
     * Retrieves the MIME Type from response of the provided URL link.
     *
     * Examples are 'image/jpeg' or 'text/html; charset=UTF-8'.
     *
     * @param url The URL to get the content type for. Should be fully qualified.
     * @return The MIME Type name for the response.
     */
    public static String getContentType(String url) {
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;

        try {
            HttpGet request = new HttpGet(url);
            client = createHttpClient();
            response = client.execute(request);

            return response.getFirstHeader("Content-Type").getValue();
        } catch (IOException e) {
            throw new InternetConnectionException("Failed to get content type for URL " + url, e);
        } finally {
            CloseableTools.safeClose(response, client);
        }
    }

    /**
     * Creates an HTTP client with proper user agent.
     *
     * @return A reference to the HTTP Client.
     */
    public static CloseableHttpClient createHttpClient() {
        return HttpClientBuilder.create().
                setUserAgent(USER_AGENT).
                build();
    }
}
