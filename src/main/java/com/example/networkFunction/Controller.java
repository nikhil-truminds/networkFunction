package com.example.networkFunction;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONArray;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.*;
import org.springframework.jmx.export.annotation.ManagedNotification;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import org.json.JSONObject;
import java.util.Scanner;
import static java.lang.System.out;
import java.io.OutputStream;

@RestController
public class Controller extends Functionality implements CommandLineRunner {

    @PostMapping("/sendNotify")
    public ResponseEntity<String> getNotify()
    { return new ResponseEntity<String>("Notification Received", HttpStatus.NO_CONTENT); }


    @Override
    public void run(String... args) throws Exception {

        Scanner input = new Scanner(System.in);
        String subID = "";

        while(true)
        {
            System.out.println("\n");

            Thread.sleep(1000);
            System.out.print("Enter Option: ");
            String opt = input.nextLine().toLowerCase();

            System.out.println();

            if(opt.equals("subscribe") || opt.equals("update"))
            {
                if(opt.equals("update") && subID.isEmpty())
                {
                    out.println("Not subsribed yet.");
                    continue;
                }

                out.print("EventID: ");
                int eventID = input.nextInt();

                out.print("Notification Method: ");
                int notifMethod = input.nextInt();

                out.print("Repetition Period: ");
                int repPeriod = input.nextInt();

                out.print("Load-level Threshold: ");
                int ldLevel = input.nextInt();

                System.out.println();

                if(opt.equals("subscribe"))
                { subID = subscribe(eventID, notifMethod, repPeriod, ldLevel); }

                else
                { update(subID, eventID, notifMethod, repPeriod, ldLevel); }

            }

            else if(opt.equals("unsubsribe"))
            {
                if(subID.isEmpty())
                {
                    out.println("Not subsribed yet.");
                    continue;
                }

                unsubscribe(subID);
            }

            else if(opt.equals("exit"))
            { break; }
        }

    }
}
