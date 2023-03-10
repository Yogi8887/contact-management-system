package com.contact.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ContactResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;
}
