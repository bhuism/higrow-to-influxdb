package nl.appsource.higrow;

import java.util.concurrent.TimeUnit;

import org.influxdb.BatchOptions;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Point;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class InfluxClient {

    final String url;
    final String username;
    final String password;

    public void writeSensor(HiGrowMeasurement hiGrowMeasurement) {

        InfluxDB influxDB = null;

        try {

            influxDB = InfluxDBFactory.connect(url, username, password);

            String dbName = "lorawan";

            influxDB.createDatabase(dbName);

            influxDB.setDatabase(dbName);

            influxDB.enableBatch(BatchOptions.DEFAULTS.exceptionHandler((failedPoints, e) -> {
                log.error("", e);
            }));

            // String rpName = "aRetentionPolicy";
            // influxDB.createRetentionPolicy(rpName, dbName, "30d", "30m", 2,
            // true);
            // influxDB.setRetentionPolicy(rpName);

            Point point = Point.measurement("sensor")
                    .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                    .tag("app_id", "terlips87")
                    .tag("dev_id", hiGrowMeasurement.getDeviceId())
                    .addField("humidity", hiGrowMeasurement.getHumidity())
                    .addField("moisture", hiGrowMeasurement.getMoisture())
                    .addField("temperature", hiGrowMeasurement.getTemperature()).build();

            influxDB.write(point);
            influxDB.flush();
            
        } finally {
            if (influxDB != null) {
                try {
                    influxDB.close();
                } catch (Exception e) {
                    log.error("influxDB.close() failed", e);
                } finally {
                    influxDB = null;
                }
            }
        }

    }

}
