package com.baratieri.dslearn.observers;

import com.baratieri.dslearn.entities.Deliver;

public interface DeliverRevisionObserver {

    void onSaveRevision(Deliver deliver);
}
