package io.github.brewagebear.domain.notification;

public interface NotificationService {
    void sendEmail(String email, String title, String description);
    void sendKakao(String phoneNo, String description);
    void sendSms(String phoneNo, String description);
}
