package com.bank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    @Bean
    public JavaMailSender javaMailService() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        /*javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setPort(587);
        javaMailSender.setUsername("artem2549@gmail.com");
        javaMailSender.setPassword("npb5d6qbr7lrl1");
        javaMailSender.setJavaMailProperties(getMailProperties());*/

        javaMailSender.setHost("smtp.mailgun.org");
        javaMailSender.setPort(587);
        javaMailSender.setUsername("postmaster@appc6c1305178314ebe9fab84460643d30c.mailgun.org");
        javaMailSender.setPassword("4355942a1e2d21c137cf6bff8be67803");
        //javaMailSender.setJavaMailProperties(getMailProperties());

        return javaMailSender;
    }

    private Properties getMailProperties() {
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        return properties;
    }
}
