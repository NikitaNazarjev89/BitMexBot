package bitmexBot.model;

import java.net.URI;
import java.net.http.HttpRequest;
import com.google.gson.Gson;
import lombok.Getter;

public class OrderHttpRequest {

    private static final Gson gson = new Gson();

    @Getter
    private HttpRequest httpRequest;
    private final Order order;

    public OrderHttpRequest(Order order, String baseURL, String endpoint, String httpMethod, AuthenticationHeaders authenticationHeaders) {
        this.order = order;
        createHttpRequest(baseURL,endpoint,httpMethod,authenticationHeaders);
    }

    private void createHttpRequest( String baseURL, String endpoint, String httpMethod, AuthenticationHeaders authenticationHeaders){
        String data;
        if(order == null){
            data = "";
        }
        else {
            OrderRequest orderRequest = OrderRequest.toRequest(order);

            data = gson.toJson(orderRequest);
        }
        HttpRequest.BodyPublisher bodyPublisher = httpMethod.equals("GET") ? HttpRequest.BodyPublishers.noBody()
                : HttpRequest.BodyPublishers.ofString(data);

        httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(baseURL+endpoint))
                .method(httpMethod, bodyPublisher)
                .header("api-key",authenticationHeaders.getApiKey())
                .header("api-signature",authenticationHeaders.getSignature())
                .header("api-expires",authenticationHeaders.getExpires())
                .build();
    }


}
