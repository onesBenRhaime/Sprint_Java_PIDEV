/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
/**
 *
 * @author houss
 */
public class SmsServicee {
     public static final String ACCOUNT_SID = "ACdf8fec3d63da851970a8ff71d6402c56";
    public static final String AUTH_TOKEN = "ebe3081da45d4cd90d579e8072fea61c";
  //  public static final String TWILIO_NUMBER = "+16205434078";

    public static void sendSms(String body) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(new com.twilio.type.PhoneNumber("+21621866975"),new com.twilio.type.PhoneNumber("+15074797933"),body).create();
        System.out.println(message.getSid());
    }
    
    
}
