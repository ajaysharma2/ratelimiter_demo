package com.technicaltransition.ratelimiter;

import com.technicaltransition.ratelimiter.utils.SiteAccessLog;

import java.util.Date;
import java.util.Hashtable;

public class RateLimitterDemo {


    //Fixed Window
    //Fixed bucket

    final private Hashtable<String, SiteAccessLog> requestCountMap = new Hashtable<>();
    private final long intervalLimit = 10000 * 5; //in milliseconds
    private final int hitCount = 5;

    public boolean rateLimit(String customerId) {
        //5 request per minute
        //check existing hit count for url
        // the current date
        Date dateTime = new Date();

        SiteAccessLog siteAccessLog = new SiteAccessLog();
        //for fixed time window 5 minute === 60000ms
        if (requestCountMap.containsKey(customerId)) {
            siteAccessLog = requestCountMap.get(customerId);
            //withing 5min and 5 request
            if ((dateTime.getTime() - siteAccessLog.getFirstAccessedTime().getTime()) < intervalLimit && siteAccessLog.getCountValue() < hitCount) {
                siteAccessLog.getAndIncreaseCount();
                siteAccessLog.setFirstAccessedTime(dateTime);
                requestCountMap.put(customerId, siteAccessLog);
                return true;
            } else if ((dateTime.getTime() - siteAccessLog.getFirstAccessedTime().getTime()) > intervalLimit) {
                //beyond fixed window and counter within 5 requests
                //reset site log
                siteAccessLog.resetCounter();
                siteAccessLog.getAndIncreaseCount();
                siteAccessLog.setFirstAccessedTime(dateTime);
                siteAccessLog.setLastAccessedTime(dateTime);
                requestCountMap.put(customerId, siteAccessLog);
                return true;
            } else if ((dateTime.getTime() - siteAccessLog.getFirstAccessedTime().getTime()) < intervalLimit && siteAccessLog.getCountValue() >= hitCount) {
                //within fixed window and counter after 5 requests
                //increase request number
                //set last accessed time
                //return false
                //siteAccessLog.resetCounter();
                siteAccessLog.getAndIncreaseCount();
                //siteAccessLog.setFirstAccessedTime(dateTime);
                siteAccessLog.setLastAccessedTime(dateTime);
                requestCountMap.put(customerId, siteAccessLog);
                return false;
            }
        } else {
            siteAccessLog.resetCounter();
            siteAccessLog.getAndIncreaseCount();
            siteAccessLog.setFirstAccessedTime(dateTime);
            siteAccessLog.setLastAccessedTime(dateTime);
            requestCountMap.put(customerId, siteAccessLog);
        }

        return true;
    }
}
