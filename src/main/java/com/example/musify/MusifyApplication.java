package com.example.musify;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.time.Duration;
import java.util.concurrent.Executor;

@SpringBootApplication
@EnableAsync
@EnableCaching
public class MusifyApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusifyApplication.class, args);
	}

	@Bean
	public Executor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(10);
		executor.setMaxPoolSize(50);
		executor.setQueueCapacity(1000);
		executor.setThreadNamePrefix("Musify-");
		executor.initialize();
		return executor;
	}

	@Bean
	public Customizer<Resilience4JCircuitBreakerFactory> globalCustomConfiguration() {
		TimeLimiterConfig timeLimiterConfig = TimeLimiterConfig.custom()
				.timeoutDuration(Duration.ofSeconds(5))
				.build();
		CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
				.failureRateThreshold(30)
				.waitDurationInOpenState(Duration.ofMillis(5000))
				.permittedNumberOfCallsInHalfOpenState(5)
				.slidingWindowSize(2)
				.build();

		return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
				.timeLimiterConfig(timeLimiterConfig)
				.circuitBreakerConfig(circuitBreakerConfig)
				.build());
	}

	@Bean
	public Customizer<Resilience4JCircuitBreakerFactory> artistCBCustomConfiguration() {
		TimeLimiterConfig timeLimiterConfig = TimeLimiterConfig.custom()
				.timeoutDuration(Duration.ofSeconds(10))
				.build();
		CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
				.failureRateThreshold(30)
				.waitDurationInOpenState(Duration.ofMillis(5000))
				.permittedNumberOfCallsInHalfOpenState(10)
				.slidingWindowSize(2)
				.build();

		return factory -> factory.configure(builder -> builder.circuitBreakerConfig(circuitBreakerConfig)
				.timeLimiterConfig(timeLimiterConfig)
				.circuitBreakerConfig(circuitBreakerConfig)
				.build(), "artistCB");
	}

}
