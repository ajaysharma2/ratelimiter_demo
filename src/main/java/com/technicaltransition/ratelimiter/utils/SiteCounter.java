package com.technicaltransition.ratelimiter.utils;

import java.util.concurrent.atomic.AtomicLong;

public class SiteCounter {

    static AtomicLong count;

    public SiteCounter() {
        count = new AtomicLong(0);
    }

    public long incrementAndGetCount() {
        return count.incrementAndGet();
    }
}
