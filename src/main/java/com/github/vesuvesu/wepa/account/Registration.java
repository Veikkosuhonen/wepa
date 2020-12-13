package com.github.vesuvesu.wepa.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor @NoArgsConstructor
public class Registration {
    @NotEmpty
    @Size(min = 3, max = 16, message = "Your username should be between 3 and 16 characters")
    private String username;

    @NotEmpty
    @Size(min = 3, max = 16, message = "Your name should be between 3 and 16 characters")
    private String name;

    @NotEmpty
    @Size(min = 5, max = 20, message = "Your password should be between 5 and 20 characters")
    private String password;
}
