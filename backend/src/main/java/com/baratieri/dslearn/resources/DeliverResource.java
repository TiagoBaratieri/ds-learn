package com.baratieri.dslearn.resources;

import com.baratieri.dslearn.dto.DeliverRevisionDTO;
import com.baratieri.dslearn.services.DeliverSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/deliveries")
public class DeliverResource {

    @Autowired
    private DeliverSevice sevice;

    @PreAuthorize("hasAnyRole('ADMIN','INSTRUCTOR')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> saveRevision(@PathVariable Long id, @RequestBody DeliverRevisionDTO dto){
        sevice.saveRevision(id, dto);
        return ResponseEntity.noContent().build();
    }
}
