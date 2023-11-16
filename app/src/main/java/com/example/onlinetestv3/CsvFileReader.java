package com.example.onlinetestv3;

import android.content.Context;
import android.content.res.AssetManager;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class CsvFileReader {
    public static List<String[]> readCsvFile(Context context, String csvFileName) {
        AssetManager assetManager = context.getAssets();
        try {
            // Open the CSV file from assets folder
            InputStream inputStream = assetManager.open(csvFileName);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

            // Create CSVReader object
            CSVReader reader = new CSVReader(inputStreamReader);

            // Read all lines at once into a List of String arrays
            List<String[]> data = reader.readAll();

            // Close the reader
            reader.close();

            // Return the data read from the CSV file
            return data;
        } catch (IOException | CsvException e) {
            e.printStackTrace();
            return null;
        }
    }
}
