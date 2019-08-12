package com.bk.bk.config;

import ch.qos.logback.classic.LoggerContext;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.bk.bk.config.ApplicationProperties;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

import static com.bk.bk.config.logging.LoggingUtils.*;

/*
 * Configures the console and Logstash log appenders from the app properties
 */
@Configuration
public class LoggingConfiguration {

  private final ApplicationProperties applicationProperties;

  public LoggingConfiguration(@Value("${spring.application.name}") String appName,
      @Value("${server.port}") String serverPort, ApplicationProperties applicationProperties, ObjectMapper mapper)
      throws JsonProcessingException {

    this.applicationProperties = applicationProperties;
    LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();

    Map<String, String> map = new HashMap<>();
    map.put("app_name", appName);
    map.put("app_port", serverPort);
    String customFields = mapper.writeValueAsString(map);

    ApplicationProperties.Logging loggingProperties = this.applicationProperties.getLogging();
    ApplicationProperties.Logging.Logstash logstashProperties = loggingProperties.getLogstash();

    if (loggingProperties.isUseJsonFormat()) {
      addJsonConsoleAppender(context, customFields);
    }
    if (logstashProperties.isEnabled()) {
      addLogstashTcpSocketAppender(context, customFields, logstashProperties);
    }
    if (loggingProperties.isUseJsonFormat() || logstashProperties.isEnabled()) {
      addContextListener(context, customFields, loggingProperties);
    }
    if (this.applicationProperties.getMetrics().getLogs().isEnabled()) {
      setMetricsMarkerLogbackFilter(context, loggingProperties.isUseJsonFormat());
    }
  }
}
