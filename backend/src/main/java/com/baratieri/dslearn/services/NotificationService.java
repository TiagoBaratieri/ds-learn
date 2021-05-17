package com.baratieri.dslearn.services;

import com.baratieri.dslearn.dto.NotificationDTO;
import com.baratieri.dslearn.entities.Notification;
import com.baratieri.dslearn.entities.User;
import com.baratieri.dslearn.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository repository;

    @Autowired
    private AuthService authService;

    public Page<NotificationDTO> notificationsForCurrentUser(boolean unreadOnly, Pageable pageable){
        User user = authService.authenticated();
        Page<Notification> page = repository.find(user, unreadOnly, pageable);
        return page.map(x -> new NotificationDTO(x));
    }
}
