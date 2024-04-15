package com.sid.msorder.emails;

public interface IMailService {
    void send(String toEmail,String object,String body);
}
