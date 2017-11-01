package com.company;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Calendar;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;


interface IClock {
    LocalDateTime now();
}

interface IMailer {
    void sendMail(String to, String subject, String message);
}

class Mailer1 implements IMailer {

    @Override
    public void sendMail(String emailTo, String emailSubject, String emailMessage) {


        final String username = "5012224098";
        final String password = "greenUTS270";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("from-email@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(emailTo));
            message.setSubject(emailSubject);
            message.setText(emailMessage);

            Transport.send(message);

            System.out.println("Sent Successfully");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}

class Clock implements IClock {

    @Override
    public LocalDateTime now() {

        return LocalDateTime.now();
    }
}

class MockClock implements IClock {

    @Override
    public LocalDateTime now() {

        LocalDateTime now = LocalDateTime.of(2015, Month.JULY, 29, 0, 00, 00);

        return now;
    }
}


public class ClockMailer {


    private IClock clock;
    private IMailer mail;
    LocalDateTime localDateTime;


    public ClockMailer(IClock clock, IMailer mail) {

        this.clock = clock;
        this.mail = mail;

        localDateTime = LocalDateTime.of(2015, Month.JULY, 29, 0, 00, 00);
        ;
    }


    public void sendMailAtMidnight() {


        Timer timer = new Timer();
        Calendar date = Calendar.getInstance();
        date.set(Calendar.HOUR, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                sendMail();
            }
        }, date.getTime());

    }


    public boolean is12oClock() {

        if (clock.now().getHour() == localDateTime.getHour() && clock.now().getMinute() == localDateTime.getMinute()) {
            return true;
        }
        return false;
    }

    public void sendMail() {

        if(is12oClock()){
            mail.sendMail("aylifftouro2@gmail.com", "HW 2", "Somehow got it done");
        }
    }


    public static void main(String[] args) {
        MockClock clock1 = new MockClock();
        Mailer1 mailer1 = new Mailer1();

        ClockMailer clockMailer = new ClockMailer(clock1, mailer1);
        clockMailer.sendMailAtMidnight();


    }


}
