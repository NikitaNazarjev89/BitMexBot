package bitmexBot.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationHeaders {
    private String expires;
    private String apiKey;
    private String signature;
}
