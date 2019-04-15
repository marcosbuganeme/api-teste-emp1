package teste.maxima.sistemas.domain.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaAuditing
@EnableTransactionManagement
@EntityScan(basePackages = { "api.carrinho.compra.domain.model" })
@EnableJpaRepositories(basePackages = { "api.carrinho.compra.domain.repository" })
public class JpaConfig {

}