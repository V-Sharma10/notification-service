package com.notif.service.notif.repositories.DB;

import com.notif.service.notif.models.BlacklistDtoModel;
import com.notif.service.notif.models.MessageDtoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// indexing in DB
public interface MessageDBRepository  extends JpaRepository<MessageDtoModel,String>
{

}
