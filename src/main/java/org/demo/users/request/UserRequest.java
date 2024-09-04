package org.demo.users.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest implements Serializable {

    private Long id;

    private String name;

    @NotBlank(message = "email must not be blank")
    @Email(message = "email must be a well-formed email address")
    private String email;

    @Size(min = 8, max = 12, message = "password size must be between 8 and 12")
    @Pattern(regexp="^(?=.*[A-Z])(?=(?:.*\\d.*\\d)[^0-9]*$)[a-zA-Z\\d]+$", message = "password must contain at least 2 number and uppercase character")
    private String password;

    @JsonProperty
    private List<PhoneRequest> phones;

}
