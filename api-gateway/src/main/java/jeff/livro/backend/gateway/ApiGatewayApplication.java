package jeff.livro.backend.gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiGatewayApplication {
	@Value("${USER_API_URL:http://localhost:8080}")
	private String userAPiUrl;

	@Value("${PRODUCT_API_URL:http://localhost:8081}")
	private String productApiUrl;

	@Value("${SHOP_API_URL:http://localhost:8082}")
	private String shopApiUrl;


	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}
	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder){
		return builder.routes()
				.route("user_route", r -> r.path("/user/**").uri(userAPiUrl))
				.route("product_route", (t -> t.path("/product/**").uri(productApiUrl)))
				.route("shop_route", (t -> t.path("/shopping/**").uri(shopApiUrl)))
				.build();
	}

}
