package org.authmicroservice.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class InputValidatorRegister {

    public static boolean isValidMoroccanPhoneNumber(String phoneNumber) {
        String regex = "^(?:\\+?212|0)([5-7]\\d{8}|[1-9]\\d{8})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    public static Boolean isNull(String field) {
        return field == null || field.trim().isEmpty();
    }

    public static Boolean isValidEmail(String email) {
        final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
        var matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches() ;
    }

    public static boolean isValidPassword(String password) {
        String regex = "^(?=.*[0-9])"
                      + "(?=.*[a-z])(?=.*[A-Z])"
                      + "(?=.*[@#$%^&+=])"
                      + "(?=\\S+$).{8,20}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

}
