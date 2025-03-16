package com.example.demo7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RatesLoader {
    public static Map<String, Double> loadRates(String filePath){
        Map<String, Double> rates = new HashMap<String, Double>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\s+");
                if (parts.length == 2) {
                    rates.put(parts[0], Double.parseDouble(parts[1]));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return rates;
    }
}
