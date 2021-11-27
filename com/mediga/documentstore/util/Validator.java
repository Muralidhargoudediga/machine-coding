package com.mediga.documentstore.util;

import com.mediga.documentstore.exception.UserNotFoundException;
import com.mediga.documentstore.model.User;

public class Validator {
    public static void validateUser(User user) throws UserNotFoundException {
        if(user == null || StringUtil.isEmpty(user.getName())) {
            throw new UserNotFoundException("User not found ");
        }
    }
}
