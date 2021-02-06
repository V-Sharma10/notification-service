package com.notif.service.notif.services.redisService;

import java.util.Set;

public interface RedisService {
    //    Add a phone_number to blacklist
    String addToBlacklist(String phoneNumber);
    //    Remove a number from blacklist
    String removeFromBlacklist(String phoneNumber);
    //    Get list of blacklisted numbers
    Set getAllBlacklistedNumbers();

    boolean checkIfExist(String number);
}
