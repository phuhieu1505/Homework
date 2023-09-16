package org.example;

import org.springframework.stereotype.Component;

@Component
public class MediaTek implements MobileProcessor {
    public void process(){
        System.out.println("MediaTeK no.2");
    }
}
