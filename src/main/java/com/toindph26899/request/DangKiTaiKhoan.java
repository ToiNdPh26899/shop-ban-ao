package com.toindph26899.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class DangKiTaiKhoan {

    @NotBlank(message = "Username khong duoc bo trong")
    private String username;

    @NotBlank(message = "Password khong duoc bo trong")
    private String password;

    @NotBlank(message = "First name khong duoc bo trong")
    private String firstName;

    @NotBlank(message = "Last name khong duoc bo trong")
    private String lastName;

    @NotBlank(message = "Username khong duoc bo trong")
    @Pattern(regexp = "^a-z0-9@a-z.a-z$")
    private String email;

}
