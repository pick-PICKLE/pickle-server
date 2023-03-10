package com.pickle.server.common.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException{
    public static String USER_NOT_LOGIN ="로그인이 필요한 작업입니다.";
    public UserNotFoundException(String message){super(message);}
    public UserNotFoundException(){super(USER_NOT_LOGIN);}
}
