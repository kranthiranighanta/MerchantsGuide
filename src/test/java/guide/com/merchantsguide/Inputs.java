package guide.com.merchantsguide;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties("guide.com")
@Data
public class Inputs {
    List<String> validInputs;
    List<String> invalidInputs;
}
