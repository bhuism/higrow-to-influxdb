package nl.appsource.higrow;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * HiGrowMeasurement.
 *
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HiGrowMeasurement {
    private String DeviceId;
    private Integer Temperature;
    private Integer Humidity;
    private Integer Moisture;
}
