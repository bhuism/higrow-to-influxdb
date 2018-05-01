package nl.appsource.higrow;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class HiGrowController {

    @RequestMapping(value = "/higrow", method = RequestMethod.POST)
    public String higrow(@RequestBody HiGrowMeasurement hiGrowMeasurement) {
        log.debug("Got: " + hiGrowMeasurement);
        InfluxClient influxClient = new InfluxClient("http://10.100.0.1:8086", "root", "killer");
        influxClient.writeSensor(hiGrowMeasurement);
        return "OK";
    }
}
