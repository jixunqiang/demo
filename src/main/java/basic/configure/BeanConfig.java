package basic.configure;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanConfig {

    @Bean
    @LoadBalanced   //实现负载均衡
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
