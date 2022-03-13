package nsu.gaiduk.database.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NodeDto {
    private List<TagDto> tags;
    private BigInteger id;
    private Double lat;
    private Double lon;
    private String username;
    private BigInteger uid;
    private Boolean visible;
    private BigInteger version;
    private BigInteger changeset;
    private LocalDate timestamp;
}
