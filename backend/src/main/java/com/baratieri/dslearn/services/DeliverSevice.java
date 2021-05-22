package com.baratieri.dslearn.services;

import com.baratieri.dslearn.dto.DeliverRevisionDTO;
import com.baratieri.dslearn.entities.Deliver;
import com.baratieri.dslearn.observers.DeliverRevisionObserver;
import com.baratieri.dslearn.repositories.DeliverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class DeliverSevice {

    @Autowired
    private DeliverRepository repository;

    private Set<DeliverRevisionObserver> deliverRevisionObservers = new LinkedHashSet<>();

    @Transactional
    public void saveRevision(Long id, DeliverRevisionDTO dto) {
        Deliver deliver = repository.getOne(id);
        deliver.setStatus(dto.getStatus());
        deliver.setFeedback(dto.getFeedback());
        deliver.setCorrectCount(dto.getCorrectCount());
        repository.save(deliver);
        for ( DeliverRevisionObserver observer : deliverRevisionObservers){
            observer.onSaveRevision(deliver);
        }
    }

    public void subscribeDeliverRevisionObserver(DeliverRevisionObserver observer) {
        deliverRevisionObservers.add(observer);
    }
}
