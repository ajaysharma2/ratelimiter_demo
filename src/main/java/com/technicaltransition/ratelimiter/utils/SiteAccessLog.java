package com.technicaltransition.ratelimiter.utils;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class SiteAccessLog {

    private Date lastAccessedTime;
    private Date firstAccessedTime;
    private AtomicInteger count = new AtomicInteger(0);

    public Date getFirstAccessedTime() {
        return firstAccessedTime;
    }

    public void setFirstAccessedTime(Date firstAccessedTime) {
        this.firstAccessedTime = firstAccessedTime;
    }

    public SiteAccessLog() {

    }

    public SiteAccessLog(Date lastAccessedTime, Date firstAccessedTime, AtomicInteger count) {
        this.lastAccessedTime = lastAccessedTime;
        this.firstAccessedTime = firstAccessedTime;
        this.count = count;
    }

    public Date getLastAccessedTime() {
        return lastAccessedTime;
    }

    public void setLastAccessedTime(Date lastAccessedTime) {
        this.lastAccessedTime = lastAccessedTime;
    }

    public AtomicInteger getCount() {
        return count;
    }

    public void setCount(AtomicInteger count) {
        this.count = count;
    }

    public void resetCounter() {
        count = new AtomicInteger(0);
    }

    public int getAndIncreaseCount() {
        return count.incrementAndGet();
    }

    public int getCountValue() {
        return count.intValue();
    }
}
