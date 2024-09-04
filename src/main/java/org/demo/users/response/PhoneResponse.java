package org.demo.users.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhoneResponse implements Serializable {
    private String number;
    private String cityCode;
    private String countryCode;
}
