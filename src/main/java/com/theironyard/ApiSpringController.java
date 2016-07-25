package com.theironyard;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.HashMap;

/**
 * Created by zach on 7/25/16.
 */
@RestController
public class ApiSpringController {
    static final String API_URL = "http://gturnquist-quoters.cfapps.io/api/random";

    @PostConstruct
    public void init() throws InterruptedException {
        Thread t = new Thread() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("Hello, world!");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t.start();
    }

    @RequestMapping(path = "/quote", method = RequestMethod.GET)
    public HashMap getQuote() {
        // see more complex example here:
        // https://github.com/Book-It/BookIt/blob/master/src/main/java/com/theironyard/controllers/BookItController.java#L309
        RestTemplate query = new RestTemplate();
        HashMap result = query.getForObject(API_URL, HashMap.class);
        String type = (String) result.get("type");
        if (type.equals("success")) {
            HashMap value = (HashMap) result.get("value");
            return value;
        }
        return null;
    }
}
