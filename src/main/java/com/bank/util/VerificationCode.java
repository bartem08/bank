package com.bank.util;

import java.util.Random;

public class VerificationCode {

    public static int generate() {
        return new Random().nextInt(239-100) + 100;
    }
}
