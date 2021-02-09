package com.notif.service.notif.services.ESService;

import com.notif.service.notif.models.MessageESModel;
import com.notif.service.notif.models.request.SearchByDateModel;
import com.notif.service.notif.repositories.ES.MessageESRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class ESServiceImpl implements ESService{
    Logger logger = LoggerFactory.getLogger(ESService.class);
    @Autowired
    MessageESRepository messageESRepository;

    @Override
    public Optional<MessageESModel> getById(String id) {

        return messageESRepository.findById(id);
    }

    @Override
    public Page<MessageESModel> getAll() {
        return (Page<MessageESModel>) messageESRepository.findAll();
    }

    @Override
    public Page<MessageESModel> getMsgBetweenDates(SearchByDateModel dateModel) {
        logger.info("getMsgBetweenDates method invoked");
        System.out.println(dateModel.getStartDate().toString());
        System.out.println(dateModel.getEndDate().toString());
        LocalDateTime localStartDateTime = LocalDateTime.parse(dateModel.getStartDate().toString(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss") );
        LocalDateTime localEndDateTime = LocalDateTime.parse(dateModel.getEndDate().toString(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss") );
        long startMillis = localStartDateTime
                .atZone(ZoneId.systemDefault())
                .toInstant().toEpochMilli();
        long endMillis = localEndDateTime
                .atZone(ZoneId.systemDefault())
                .toInstant().toEpochMilli();
        Page<MessageESModel> page = messageESRepository
                .findAllByCreatedAtBetween( startMillis,
                        endMillis, PageRequest.of(0, 2));
        logger.info("Page result : {}", page.get() );
        return page;
      }
}
