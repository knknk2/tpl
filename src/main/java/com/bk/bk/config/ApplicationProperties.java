package com.bk.bk.config;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.cors.CorsConfiguration;

/**
 * Properties specific to application.
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

  private final Cache cache = new Cache();

  private final Http http = new Http();

  private final Metrics metrics = new Metrics();

  private final Logging logging = new Logging();

  private final CorsConfiguration cors = new CorsConfiguration();

  private final Security security = new Security();

  private final ClientApp clientApp = new ClientApp();

  /**
   * <p>
   * Getter for the field <code>security</code>.
   * </p>
   *
   */
  public Security getSecurity() {
    return security;
  }

  /**
   * <p>
   * Getter for the field <code>metrics</code>.
   * </p>
   *
   * @return a {@link com.bk.bk.config.ApplicationProperties.Metrics} object.
   */
  public Metrics getMetrics() {
    return metrics;
  }

  /**
   * <p>
   * Getter for the field <code>logging</code>.
   * </p>
   *
   * @return a {@link com.bk.bk.config.ApplicationProperties.Logging} object.
   */
  public Logging getLogging() {
    return logging;
  }

  /**
   * <p>
   * Getter for the field <code>cors</code>.
   * </p>
   *
   * @return a {@link org.springframework.web.cors.CorsConfiguration} object.
   */
  public CorsConfiguration getCors() {
    return cors;
  }

  /**
   * <p>
   * Getter for the field <code>http</code>.
   * </p>
   *
   * @return a {@link com.bk.bk.config.ApplicationProperties.Http} object.
   */
  public Http getHttp() {
    return http;
  }

  /**
   * @return the cache
   */
  public Cache getCache() {
    return cache;
  }

  /**
   * <p>
   * Getter for the field <code>clientApp</code>.
   * </p>
   *
   */
  public ClientApp getClientApp() {
    return clientApp;
  }

  public static class Cache {

    private final Ehcache ehCache = new Ehcache();

    /**
     * @return the ehCache
     */
    public Ehcache getEhCache() {
      return ehCache;
    }

    public static class Ehcache {

      private int timeToLiveSeconds = 3600;

      private long maxEntries = 100;

      public int getTimeToLiveSeconds() {
        return timeToLiveSeconds;
      }

      public void setTimeToLiveSeconds(int timeToLiveSeconds) {
        this.timeToLiveSeconds = timeToLiveSeconds;
      }

      public long getMaxEntries() {
        return maxEntries;
      }

      public void setMaxEntries(long maxEntries) {
        this.maxEntries = maxEntries;
      }
    }
  }

  public static class Metrics {

    private final Logs logs = new Logs();

    public Logs getLogs() {
      return logs;
    }

    public static class Logs {

      private boolean enabled = false;

      private long reportFrequency = 60;

      public boolean isEnabled() {
        return enabled;
      }

      public void setEnabled(boolean enabled) {
        this.enabled = enabled;
      }

      public long getReportFrequency() {
        return reportFrequency;
      }

      public void setReportFrequency(long reportFrequency) {
        this.reportFrequency = reportFrequency;
      }
    }
  }

  public static class Logging {

    private boolean useJsonFormat = false;

    private final Logstash logstash = new Logstash();

    public boolean isUseJsonFormat() {
      return useJsonFormat;
    }

    public void setUseJsonFormat(boolean useJsonFormat) {
      this.useJsonFormat = useJsonFormat;
    }

    public Logstash getLogstash() {
      return logstash;
    }

    public static class Logstash {

      private boolean enabled = false;

      private String host = "localhost";

      private int port = 5000;

      private int queueSize = 512;

      public boolean isEnabled() {
        return enabled;
      }

      public void setEnabled(boolean enabled) {
        this.enabled = enabled;
      }

      public String getHost() {
        return host;
      }

      public void setHost(String host) {
        this.host = host;
      }

      public int getPort() {
        return port;
      }

      public void setPort(int port) {
        this.port = port;
      }

      public int getQueueSize() {
        return queueSize;
      }

      public void setQueueSize(int queueSize) {
        this.queueSize = queueSize;
      }
    }
  }

  public static class Http {

    private final Cache cache = new Cache();

    public Cache getCache() {
      return cache;
    }

    public static class Cache {

      private int timeToLiveInDays = 1461; // 4 years (including leap day)

      public int getTimeToLiveInDays() {
        return timeToLiveInDays;
      }

      public void setTimeToLiveInDays(int timeToLiveInDays) {
        this.timeToLiveInDays = timeToLiveInDays;
      }
    }
  }

  public static class Security {

    private final ClientAuthorization clientAuthorization = new ClientAuthorization();

    private final Authentication authentication = new Authentication();

    private final RememberMe rememberMe = new RememberMe();

    public ClientAuthorization getClientAuthorization() {
      return clientAuthorization;
    }

    public Authentication getAuthentication() {
      return authentication;
    }

    public RememberMe getRememberMe() {
      return rememberMe;
    }

    public static class ClientAuthorization {

      private String accessTokenUri = null;

      private String tokenServiceId = null;

      private String clientId = null;

      private String clientSecret = null;

      public String getAccessTokenUri() {
        return accessTokenUri;
      }

      public void setAccessTokenUri(String accessTokenUri) {
        this.accessTokenUri = accessTokenUri;
      }

      public String getTokenServiceId() {
        return tokenServiceId;
      }

      public void setTokenServiceId(String tokenServiceId) {
        this.tokenServiceId = tokenServiceId;
      }

      public String getClientId() {
        return clientId;
      }

      public void setClientId(String clientId) {
        this.clientId = clientId;
      }

      public String getClientSecret() {
        return clientSecret;
      }

      public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
      }
    }

    public static class Authentication {

      private final Jwt jwt = new Jwt();

      public Jwt getJwt() {
        return jwt;
      }

      public static class Jwt {

        private String secret = null;

        private String base64Secret = null;

        private long tokenValidityInSeconds = 1800; // 30 minutes

        private long tokenValidityInSecondsForRememberMe = 2592000; // 30 days

        public String getSecret() {
          return secret;
        }

        public void setSecret(String secret) {
          this.secret = secret;
        }

        public String getBase64Secret() {
          return base64Secret;
        }

        public void setBase64Secret(String base64Secret) {
          this.base64Secret = base64Secret;
        }

        public long getTokenValidityInSeconds() {
          return tokenValidityInSeconds;
        }

        public void setTokenValidityInSeconds(long tokenValidityInSeconds) {
          this.tokenValidityInSeconds = tokenValidityInSeconds;
        }

        public long getTokenValidityInSecondsForRememberMe() {
          return tokenValidityInSecondsForRememberMe;
        }

        public void setTokenValidityInSecondsForRememberMe(long tokenValidityInSecondsForRememberMe) {
          this.tokenValidityInSecondsForRememberMe = tokenValidityInSecondsForRememberMe;
        }
      }
    }

    public static class RememberMe {

      @NotNull
      private String key = null;

      public String getKey() {
        return key;
      }

      public void setKey(String key) {
        this.key = key;
      }
    }
  }

  public static class ClientApp {

    private String name = "";

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }
  }
}
