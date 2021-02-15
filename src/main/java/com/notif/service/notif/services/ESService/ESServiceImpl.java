package com.notif.service.notif.services.ESService;

import com.notif.service.notif.models.MessageESModel;
import com.notif.service.notif.models.request.SearchByDateModel;
import com.notif.service.notif.models.request.SearchPhraseModel;
import com.notif.service.notif.repositories.ES.MessageESRepository;
import com.notif.service.notif.utils.HelperUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.SearchPage;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ESServiceImpl implements ESService{
    Logger logger = LoggerFactory.getLogger(ESService.class);
    @Autowired
    MessageESRepository messageESRepository;

    @Autowired
    HelperUtils helperUtils;

    @Override
    public Optional<MessageESModel> getById(String id) {
        logger.info("getById, id = {}",id);
        return messageESRepository.findById(id);
    }

    @Override
    public Page<MessageESModel> getAll() {
        logger.info("getAll");
        return (Page<MessageESModel>) messageESRepository.findAll();
    }

    @Override
    public Page<MessageESModel> getByText(String text, int page, int size) {
        logger.info("getByText , text = {}, page = {}, size = {}", text, page, size);
        return messageESRepository.findByMessageContaining(text,PageRequest.of(page, size));
    }

    @Override
    public SearchPage<MessageESModel> getByPhrase(SearchPhraseModel phrase) {
        logger.info("getByPhrase , text = {}, page = {}, size = {}", phrase.getPhrase(), phrase.getPage(), phrase.getSize());
        return messageESRepository.getByPhrase(phrase);
    }

    @Override
    public Page<MessageESModel> getMsgBetweenDates(SearchByDateModel dateModel) {
        logger.info("getMsgBetweenDates method invoked, startDate = {}, endDate = {}", dateModel.getStartDate(), dateModel.getEndDate());

        long startEpoch = helperUtils.dateStringToEpoch(dateModel.getStartDate());
        long endEpoch = helperUtils.dateStringToEpoch(dateModel.getEndDate());

        Page<MessageESModel> page = messageESRepository
                .findAllByCreatedAtBetween( startEpoch,
                        endEpoch, PageRequest.of(0, 2));
        logger.info("Page result : {}", page.get() );
        return page;
      }
}
