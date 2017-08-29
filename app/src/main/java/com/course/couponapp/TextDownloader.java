package com.course.couponapp;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TextDownloader extends AsyncTask<String, Void, String> {

    private Callbacks callbacks;
    private int httpStatusCode;
    private String errorMessage;

    public TextDownloader(Callbacks callbacks) {
        this.callbacks = callbacks;
    }

    // We are about to begin the background networking:
    protected void onPreExecute() {
        callbacks.onAboutToBegin();
    }

    // Background networking:
    protected String doInBackground(String... urls) {

        HttpURLConnection connection = null; // Connect to a url.
        InputStream inputStream = null; // Creates a stream (comm line) to download data.
        InputStreamReader inputStreamReader = null; // Read data from the stream.
        BufferedReader bufferedReader = null; // More easy to read data.

        try {

            String link = urls[0];

            URL url = new URL(link);

            connection = (HttpURLConnection)url.openConnection();

            httpStatusCode = connection.getResponseCode();

            if(httpStatusCode != HttpURLConnection.HTTP_OK) {
                errorMessage = connection.getResponseMessage();
                return null;
            }

            // Here it means that there is no error! wiiiiiiiiiii :-)

            inputStream = connection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);

            StringBuilder downloadedText = new StringBuilder();
            String oneLine = bufferedReader.readLine();
            while(oneLine != null) {
                downloadedText.append(oneLine);
                downloadedText.append("\n");
                oneLine = bufferedReader.readLine();
            }

            return downloadedText.toString();
        }
        catch (Exception ex) {
            errorMessage = ex.getMessage();
            return null;
        }
        finally {
            if(connection != null) {
                connection.disconnect();
            }
            if(bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // End of networking:
    protected void onPostExecute(String downloadedText) {
        if(downloadedText != null) {
            callbacks.onSuccess(downloadedText);
        }
        else {
            callbacks.onError(httpStatusCode, errorMessage);
        }
    }

    public interface Callbacks {
        void onAboutToBegin();
        void onSuccess(String downloadedText);
        void onError(int httpStatusCode, String errorMessage);
    }
}
