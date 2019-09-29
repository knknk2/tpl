package com.bk.bk.config;

import com.bk.bk.repository.UserRepository;

import com.github.benmanes.caffeine.jcache.configuration.CaffeineConfiguration;

import java.util.OptionalLong;
import java.util.concurrent.TimeUnit;

import org.hibernate.cache.jcache.ConfigSettings;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

  private final javax.cache.configuration.Configuration<Object, Object> cacheConfiguration;

  private final ApplicationProperties applicationProperties;

  public CacheConfiguration(ApplicationProperties applicationProperties) {
    this.applicationProperties = applicationProperties;
    ApplicationProperties.Cache.Caffeine caffeine = this.applicationProperties.getCache().getCaffeine();

    CaffeineConfiguration caffeineConfiguration = new CaffeineConfiguration();
    caffeineConfiguration.setMaximumSize(OptionalLong.of(caffeine.getMaxEntries()));
    caffeineConfiguration.setExpireAfterWrite(OptionalLong.of(TimeUnit.SECONDS.toNanos(caffeine.getTimeToLiveSeconds())));
    caffeineConfiguration.setStatisticsEnabled(true);
    cacheConfiguration = caffeineConfiguration;
  }

  @Bean
  public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
    return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
  }

  @Bean
  public JCacheManagerCustomizer cacheManagerCustomizer() {
    return cm -> {
      createCache(cm, UserRepository.USERS_BY_LOGIN_CACHE);
      createCache(cm, com.bk.bk.domain.User.class.getName());
    };
  }

  private void createCache(javax.cache.CacheManager cm, String cacheName) {
    javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
    if (cache != null) {
      cm.destroyCache(cacheName);
    }
    cm.createCache(cacheName, cacheConfiguration);
  }
}
