package org.demo.users.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhoneRequest implements Serializable {
    private Long number;
    @JsonProperty("citycode")
    private Integer cityCode;
    @JsonProperty("countrycode")
    private String countryCode;
}
