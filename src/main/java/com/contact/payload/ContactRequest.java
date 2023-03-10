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
public class ContactRequest {
    @NotEmpty
    @Size(min = 3,message = "Username must be min of 3 character !!")
    private String firstName;

    @NotEmpty
    @Size(min = 2,message = "Username must be min of 2 character !!")
    private String lastName;
    @Email(message = "Email address is not valid !!")
    private String email;
    @NotNull
    @Size(min = 10,max = 10,message = "password must be 10 character !!")
    private String phoneNumber;
}
