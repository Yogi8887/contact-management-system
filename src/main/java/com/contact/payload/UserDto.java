package com.contact.payload;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private int id;
    @NotEmpty
    @Size(min = 4,message = "Username must be min of 4 character !!")
    private String name;
    @Email(message = "Email address is not valid !!")
    private String email;
    @NotNull
    @Size(min = 3,max = 10,message = "password must be 3-10 character !!")
    private String password;
}
