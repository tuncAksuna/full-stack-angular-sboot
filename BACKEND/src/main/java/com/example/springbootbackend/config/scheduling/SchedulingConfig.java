package com.example.springbootbackend.config.scheduling;

import com.example.springbootbackend.SpringbootBackendApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

@Configuration
@EnableScheduling
@ConditionalOnProperty(name = "scheduling.enabled", matchIfMissing = true) // disable during the tests
public class SchedulingConfig {

  private static final Logger LOGGER = LoggerFactory.getLogger(SpringbootBackendApplication.class);

  @Scheduled(fixedDelay = 10000L)
  void task() {
    LOGGER.info("Task is being ran : " + new Date());
  }

  @Scheduled(fixedDelayString = "${task2.delayProp}")
  void task2() {
    LOGGER.info("Task 2 is being ran : " + new Date());
  }

  @Scheduled(cron = "0 0 18 * * MON-FRI")
// 1-sec - 2- min - 3,4-months of the day - 5-day,6-day of the week
  void task3() {
    LOGGER.info("Task 3 is being ran : " + new Date());
  }
}
// scheduled method has to return "void" and has to have no parameter(s)
// all the scheduled tasks is ran on single thread by default

