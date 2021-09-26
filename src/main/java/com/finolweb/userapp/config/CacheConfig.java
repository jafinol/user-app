package com.finolweb.userapp.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableCaching
@Configuration
public class CacheConfig {

	
	@Bean
	public CacheManager getCache() {
	return new ConcurrentMapCacheManager("users");
	}
	
	
//	@Bean(destroyMethod ="shutdown")
//	public RedissonClient redisson() {
//		Config config=new Config();
//		config.useSingleServer()
//		.setAddress("redis://finolweb.redis.cache.windows.net:6379")
//		.setPassword("sIfuV6s2EAcqTLtPokB4+nOZgBnPRI7R13+89BW8S0I=");
//		return Redisson.create(config);
//	}
}
