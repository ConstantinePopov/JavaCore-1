import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

public class CatsFacts {
    public static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(5000)
                .setSocketTimeout(30000)
                .setRedirectsEnabled(false)
                .build();

        try (CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setUserAgent("My Program")
                .setDefaultRequestConfig(config)
                .build()) {
            HttpGet request =
                    new HttpGet("https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats");
            request.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());

            try (CloseableHttpResponse response = httpClient.execute(request)) {

                List<AboutCats> aboutCatsList = mapper.readValue(response.getEntity().getContent(), new TypeReference<List<AboutCats>>() {
                });
                Stream<AboutCats> streamData = aboutCatsList.stream()
                        .filter(value -> value.getUpvotes() > 0);
                streamData.forEach(System.out::println);
            }
        }
    }
}
