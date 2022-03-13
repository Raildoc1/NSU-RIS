package nsu.gaiduk.database.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WayDto {
    private BigInteger id;
    private String username;
    private BigInteger uid;
    private Boolean visible;
    private BigInteger version;
    private BigInteger changeset;
    private LocalDate timestamp;
}
