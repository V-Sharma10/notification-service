package com.notif.service.notif.services.redisService;

import com.notif.service.notif.models.BlacklistDtoModel;
import com.notif.service.notif.repositories.DB.BlacklistDBRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

import static com.notif.service.notif.utils.Validator.isValidIndianMobileNumber;

@Service
public class RedisServiceImpl implements RedisService{
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private BlacklistDBRepository blacklistDBRepository;

    @Value("${redis.set}")
    private String KEY;

    @Override
    public String addToBlacklist(String phoneNumber) {
        try{
            if(isValidIndianMobileNumber(phoneNumber) && !checkIfExist(phoneNumber) ) {
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
            if(isValidIndianMobileNumber(phoneNumber)){
                redisTemplate.opsForSet().remove(KEY,phoneNumber);

                /**
                 * Remove the number from DB as well.
                 **/


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
