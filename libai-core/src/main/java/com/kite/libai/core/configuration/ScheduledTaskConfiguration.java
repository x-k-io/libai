package com.kite.libai.core.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
@ConditionalOnProperty(value = "kite.job.enabled", havingValue = "true")
public class ScheduledTaskConfiguration {


}
