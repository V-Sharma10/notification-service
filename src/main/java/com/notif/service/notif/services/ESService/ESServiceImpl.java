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
        Pageable firstPageWithFiveElements = PageRequest.of(0, 5);
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return messageESRepository
                .findByCreatedAtAfterAndCreatedAtBefore(
                        dateModel.getStartDate(),
                       dateModel.getEndDate(),
                        firstPageWithFiveElements);
//        return messageESRepository.customRandomFunction(dateModel.getStartDate(),dateModel.getEndDate());
    }
}
