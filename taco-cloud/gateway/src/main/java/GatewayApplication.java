import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import taco.TacoCloudApplication;

/**
 * @Author: xiaguangyong
 * @ClassName: GatewayApplication
 * @Description: 启动类
 * Zuul 提供=代理+路由+过滤三大功能
 * @Date: 2021/2/9 14:12
 * @Version: 1.0
 * @Modify:
 */
//@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
