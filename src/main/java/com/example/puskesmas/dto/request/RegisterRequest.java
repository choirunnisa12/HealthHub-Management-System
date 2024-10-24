package com.example.puskesmas.dto.request;

import com.example.puskesmas.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    private String name;
    private String email;
    private String password;

//    public static User fromRegisterToUserMapper(RegisterRequest registerRequest){
//        return User.builder().na
    }

//}
