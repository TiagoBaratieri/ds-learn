package com.baratieri.dslearn.services;

import com.baratieri.dslearn.dto.NotificationDTO;
import com.baratieri.dslearn.entities.Deliver;
import com.baratieri.dslearn.entities.Notification;
import com.baratieri.dslearn.entities.User;
import com.baratieri.dslearn.observers.DeliverRevisionObserver;
import com.baratieri.dslearn.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.Instant;

@Service
public class NotificationService implements DeliverRevisionObserver {

    @Autowired
    private NotificationRepository repository;

    @Autowired
    private AuthService authService;

    @Autowired
    private DeliverSevice deliverSevice;

    @PostConstruct
    private void initialize(){
        deliverSevice.subscribeDeliverRevisionObserver(this);
    }

    @Transactional(readOnly = true)
    public Page<NotificationDTO> notificationsForCurrentUser(boolean unreadOnly, Pageable pageable){
        User user = authService.authenticated();
        Page<Notification> page = repository.find(user, unreadOnly, pageable);
        return page.map(x -> new NotificationDTO(x));
    }

    @Transactional
    public void saveDeliverNotification(Deliver deliver){
        Long sectionId = deliver.getLesson().getSection().getId();
        Long resourceId = deliver.getLesson().getSection().getResource().getId();
        Long offerId = deliver.getLesson().getSection().getResource().getOffer().getId();

        String route = "/offer/" + offerId + "resources/" + resourceId + "/sections/" + sectionId;
        String text = deliver.getFeedback();
        Instant moment = Instant.now();
        User user = deliver.getEnrollment().getStudent();

        Notification notification = new Notification(null, text, moment, false, route, user);
        repository.save(notification);
    }

    @Override
    public void onSaveRevision(Deliver deliver) {
       saveDeliverNotification(deliver);
    }
}
