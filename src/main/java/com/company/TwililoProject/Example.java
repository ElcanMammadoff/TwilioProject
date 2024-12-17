package com.company.TwililoProject;

import com.twilio.Twilio;
import com.twilio.rest.lookups.v1.PhoneNumber;  // Twilio Lookup API ilə əlaqəli
import com.twilio.exception.ApiException; // Xətaları idarə etmək üçün
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class Example {

    public static final String ACCOUNT_SID = "AC836ad6c9d93346693380d13c0a786bc3";
    public static final String AUTH_TOKEN = "7ba7ddae91cdc764642b293e72a863b3";

    @GetMapping("/main")
    public void main() {
        System.out.println("ACCOUNT_SID: " + ACCOUNT_SID);
        System.out.println("AUTH_TOKEN: " + AUTH_TOKEN);

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);


        List<String> list = new ArrayList();
        list.add("(555) 123-4567");
        list.add("(555) 234-5678");
        list.add("(555) 345-6789");
        list.add("+994507190275");

        for (int i = 0; i < list.size() - 1; i++) {

            String phoneNumber=list.get(i);

            try {

                PhoneNumber number = PhoneNumber.fetcher(phoneNumber)
                        .fetch();

                String carrierType = number.getCarrier() != null ? number.getCarrier().getType() : "unknown";

                if ("landline".equalsIgnoreCase(carrierType)) {
                    System.out.println("Bu nömrə landline nömrəsidir. SMS göndərilə bilməz.");
                } else {
                    System.out.println("Bu nömrə " + carrierType + " nömrəsidir. SMS göndərilə bilər.");
                    Message message = Message
                            .creator(new com.twilio.type.PhoneNumber("+14159352345"),
                                    new com.twilio.type.PhoneNumber("+14158141829"),
                                    "Where's Wallace?")
                            .create();
                }
            } catch (ApiException e) {
                System.out.println("Xəta baş verdi: " + e.getMessage());
            }
        }
    }
}
