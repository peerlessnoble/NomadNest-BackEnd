package com.sid.usermicroservice.email;

public interface EmailSender {
    void send(String to, String email);
}
