package es.recursividad.api.query.scheduler;

import es.recursividad.api.query.service.TrafficService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by vh @ visionarity.com
 */
@Slf4j
@Component
public class Scheduler {

    @Autowired private TrafficService trafficService;

    // Schedule task with 5 minutes of delay between runs
    @Scheduled(fixedDelay = 300000)
    public void captureNewTraffic() {
        log.info("Starting scheduled task @ {}", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        trafficService.captureAllTraffic();
        log.info("Scheduled task finished @ {}", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }
}
