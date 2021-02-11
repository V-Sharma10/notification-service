package com.notif.service.notif.repositories.DB;

import com.notif.service.notif.models.BlacklistDtoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlacklistDBRepository extends JpaRepository<BlacklistDtoModel,Integer> {

}
