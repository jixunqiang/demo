package basic.component;

import basic.configure.RabbitMqConfig;
import basic.services.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@RabbitListener(queues = RabbitMqConfig.QUEEN_PUBLISH_IMPORT)
public class MessageReceiver {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //开启10个线程处理消息
    private static final ExecutorService threadPool = Executors.newFixedThreadPool(10);

    @Resource
    private RedisService redisService;


    @RabbitHandler
    @RabbitListener(queues = RabbitMqConfig.QUEEN_PUBLISH_IMPORT)
    public void process(String content) {
        logger.info("接收处理队列A当中的消息： " + content);
        threadPool.execute(() ->{
            
        });
    }
}
