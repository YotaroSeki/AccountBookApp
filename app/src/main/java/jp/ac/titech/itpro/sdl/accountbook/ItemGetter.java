package jp.ac.titech.itpro.sdl.accountbook;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class ItemGetter {

    private final String baseUrl;

    public ItemGetter(String baseUrl) {
      this.baseUrl = baseUrl;
    }

    public InputStream items(String query) {
      InputStream in = null;
      try {
        HttpURLConnection conn = getConnection(getURL(query));
        try {
          in = conn.getInputStream();
          return in;
        } finally {
          conn.disconnect();
        }
      } catch (Exception e) {
        return in;
      }
    }

    private URL getURL(String query) throws IOException {
      String encodedQuery = URLEncoder.encode(query, "UTF-8");
      return new URL(baseUrl + encodedQuery);
    }

    private HttpURLConnection getConnection(URL url) throws IOException {
      HttpURLConnection result = (HttpURLConnection) url.openConnection();
      result.setConnectTimeout(10000);
      result.setDoInput(true);
      result.connect();
      return result;
    }
  }

}
