package org.example;

import org.springframework.stereotype.Component;

@Component
public class SnapDragon implements MobileProcessor{
    @Override
    public void process() {
        System.out.println("SnapDragon no.1");
    }
}
