package com.notif.service.notif.services.redisService;

import com.notif.service.notif.models.BlacklistDtoModel;
import com.notif.service.notif.repositories.DB.BlacklistDBRepository;
import com.notif.service.notif.validators.MessageRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RedisServiceImpl implements RedisService{
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
        try{
            if(messageRequestValidator.phoneValidator(phoneNumber) && !checkIfExist(phoneNumber) ) {
                redisTemplate.opsForSet().add(KEY, phoneNumber);
                BlacklistDtoModel newNum = BlacklistDtoModel.builder()
                        .number(phoneNumber).build();

                blacklistDBRepository.save(newNum);
                return "Successfully Added";
            }
            else{
                return "Either Phone Number is not Valid or it has already been blacklisted.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "failed to add to the blacklist";
        }
    }

    @Override
    public String removeFromBlacklist(String phoneNumber) {
        try{
            if(messageRequestValidator.phoneValidator(phoneNumber)){
                redisTemplate.opsForSet().remove(KEY,phoneNumber);
                return "Number successfully removed from blacklist" ;
            }
            else{
                return "Phone Number is not valid";
            }

        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return "Failed to remove from blacklist";
    }

    @Override
    public Set getAllBlacklistedNumbers() {
        return redisTemplate.opsForSet().members(KEY) ;
    }

    @Override
    public boolean checkIfExist(String number) {
        if(messageRequestValidator.phoneValidator(number)) {
            return redisTemplate.opsForSet().isMember(KEY, number);

        }

        return false;
    }
}
