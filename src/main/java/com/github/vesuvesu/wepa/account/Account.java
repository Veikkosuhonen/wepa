package com.github.vesuvesu.wepa.account;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

import com.github.vesuvesu.wepa.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account extends AbstractPersistable<Long> {

    @Size(min=3, max=20)
    private String username;

    @Size(min=3, max=20)
    private String password;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @OneToOne
    private User user;
}