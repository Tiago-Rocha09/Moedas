package com.tiago.moedas.service;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.tiago.moedas.model.Currency;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class HTTPService extends AsyncTask<Void, Void, Currency> {

    @Override
    protected Currency doInBackground(Void... voids) {
        StringBuilder response = new StringBuilder();
        try {
            URL url = new URL("https://economia.awesomeapi.com.br/json/all/USD-BRL,EUR-BRL");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            connection.setConnectTimeout(5000);
            connection.connect();

            Scanner scanner = new Scanner(url.openStream());
            while (scanner.hasNextLine()){
                response.append(scanner.next());
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(response.toString());
        return new Gson().fromJson(response.toString(), Currency.class);
    }
}
