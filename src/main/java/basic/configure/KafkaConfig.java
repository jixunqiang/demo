package basic.configure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    //设置队列
    public final  static String QUEEN_PUBLISH_ONE = "queen_publish_one";
    public final  static String QUEEN_PUBLISH_two = "queen_publish_two";
    public final  static String QUEEN_PUBLISH_three = "queen_publish_three";
    public final  static String QUEEN_PUBLISH_four = "queen_publish_four";
    public final  static String QUEEN_PUBLISH_five = "queen_publish_five";
    public final  static String QUEEN_PUBLISH_six = "queen_publish_six";
    public final  static String QUEEN_PUBLISH_seven = "queen_publish_seven";
    public final  static String QUEEN_PUBLISH_eight = "queen_publish_eight";
    public final  static String QUEEN_PUBLISH_nine = "queen_publish_nine";
    public final  static String QUEEN_PUBLISH_ten = "queen_publish_ten";
    
}
