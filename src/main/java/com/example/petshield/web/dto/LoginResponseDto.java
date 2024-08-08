package com.example.petshield.web.dto;

import com.example.petshield.domain.User;
import lombok.*;

@Data
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto {
    boolean loginSuccess;
    User user;


    public void setUser(User user) {
        this.user = user;
    }

}
