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
    @Size(min = 3, max = 10)
    private String username;

    @NotEmpty
    @Size(min = 3, max = 16)
    private String name;

    @NotEmpty
    @Size(min = 3, max = 16)
    private String password;
}
