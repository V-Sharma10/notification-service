package com.notif.service.notif.repositories;

import com.notif.service.notif.models.MessageDtoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageDBRepository  extends JpaRepository<MessageDtoModel,String> {
}
