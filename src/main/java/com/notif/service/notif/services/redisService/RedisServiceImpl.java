package com.notif.service.notif.services.redisService;

import com.notif.service.notif.models.BlacklistDtoModel;
import com.notif.service.notif.repositories.DB.BlacklistDBRepository;
import com.notif.service.notif.utils.enums.FailureEnums;
import com.notif.service.notif.utils.enums.SuccessEnums;
import com.notif.service.notif.validators.MessageRequestValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RedisServiceImpl implements RedisService{
    Logger logger = LoggerFactory.getLogger(RedisServiceImpl.class);
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private BlacklistDBRepository blacklistDBRepository;

    @Autowired
    MessageRequestValidator messageRequestValidator;

    @Value("${redis.set}")
    private String KEY;

    @Override
    public String addToBlacklist(String phoneNumber) {
       logger.info("addToBlacklist, number = {}", phoneNumber);
        try{
            if(messageRequestValidator.phoneValidator(phoneNumber) && !checkIfExist(phoneNumber) ) {
                redisTemplate.opsForSet().add(KEY, phoneNumber);
                BlacklistDtoModel newNum = BlacklistDtoModel.builder()
                        .number(phoneNumber).build();

                blacklistDBRepository.save(newNum);
                return SuccessEnums.SUCCESS.getMessage();
            }
            else{
                logger.error(FailureEnums.REDIS_ADD_FAIL.getMessage());
                return FailureEnums.REDIS_ADD_FAIL.getMessage();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return FailureEnums.FAILED.getMessage();
        }
    }

    @Override
    public String removeFromBlacklist(String phoneNumber) {
        logger.info("removeFromBlacklist, number = {}", phoneNumber);
        try{
            if(messageRequestValidator.phoneValidator(phoneNumber)){
                redisTemplate.opsForSet().remove(KEY,phoneNumber);
                return SuccessEnums.SUCCESS.getMessage() ;
            }
            else{
                logger.error(FailureEnums.REDIS_ADD_FAIL.getMessage());
                return FailureEnums.REDIS_ADD_FAIL.getMessage();
            }

        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return FailureEnums.FAILED.getMessage();
    }

    @Override
    public Set getAllBlacklistedNumbers() {
        logger.info("getAllBlacklistedNumbers");
        return redisTemplate.opsForSet().members(KEY) ;
    }

    @Override
    public boolean checkIfExist(String number) {
        logger.info("checkIfExist, number = {}",number);
        if(messageRequestValidator.phoneValidator(number)) {
            return redisTemplate.opsForSet().isMember(KEY, number);

        }

        return false;
    }
}
