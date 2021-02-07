package com.notif.service.notif.services.ESService;

import com.notif.service.notif.models.MessageESModel;
import com.notif.service.notif.models.request.SearchByDateModel;
import com.notif.service.notif.repositories.MessageESRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Service
public class ESServiceImpl implements ESService{
    @Autowired
    MessageESRepository messageESRepository;

    @Override
    public Page<MessageESModel> getAll() {
        return (Page<MessageESModel>) messageESRepository.findAll();
    }

    @Override
    public Page<MessageESModel> getMsgBetweenDates(SearchByDateModel dateModel) {
        System.out.println("ESServiceImpl: "+dateModel);
        Pageable firstPageWithFiveElements = PageRequest.of(0, 5);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return messageESRepository
                .findAllByCreatedAtAfterAndCreatedAtBefore(
                        formatter.format(dateModel.getStartDate()),
                        formatter.format(dateModel.getEndDate()),
                        firstPageWithFiveElements);
    }
}
