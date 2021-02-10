package com.notif.service.notif.services.redisService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

import static com.notif.service.notif.utils.Validator.isValidIndianMobileNumber;

@Service
public class RedisServiceImpl implements RedisService{
    @Autowired
    private RedisTemplate redisTemplate;

    @Value("${redis.set}")
    private String KEY;

    @Override
    public String addToBlacklist(String phoneNumber) {
        try{
            if(isValidIndianMobileNumber(phoneNumber)) {
                redisTemplate.opsForSet().add(KEY, phoneNumber);
                return "Successfully Added";
            }
            else{
                return "Phone Number is not valid";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "failed to add to the blacklist";
        }
    }

    @Override
    public String removeFromBlacklist(String phoneNumber) {
        try{
            if(isValidIndianMobileNumber(phoneNumber)){
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
        if(isValidIndianMobileNumber(number)) {
            return redisTemplate.opsForSet().isMember(KEY, number);

        }

        return false;
    }
}
