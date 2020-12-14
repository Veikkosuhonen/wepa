package com.github.vesuvesu.wepa.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor @NoArgsConstructor
public class Registration {


    @NotEmpty
    @Pattern(regexp = "[\\w]+", message = "Your username can only contain letters, numbers and underscores")
    @Size(min = 3, max = 16, message = "Your username should be between 3 and 16 characters")
    private String username;

    @NotEmpty
    @Pattern(regexp = "[\\w]+", message = "Your name can only contain letters, numbers and underscores")
    @Size(min = 3, max = 16, message = "Your name should be between 3 and 16 characters")
    private String name;

    @NotEmpty
    @Pattern(regexp = "[\\S]+", message = "Your password cannot contain whitespace")
    @Size(min = 5, max = 20, message = "Your password should be between 5 and 20 characters")
    private String password;
}
