package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class UserReader {
    private BufferedReader userScanner = new BufferedReader(new InputStreamReader(System.in));
    private boolean fileModOn = false;

    public BufferedReader getUserScanner() {
        return userScanner;
    }

    public void setUserScanner(BufferedReader userScanner) {
        this.userScanner = userScanner;
    }

    public boolean isFileModOn() {
        return fileModOn;
    }

    public void setFileModOn(boolean fileModOn) {
        this.fileModOn = fileModOn;
    }

    @Override
    public String toString() {
        return "UserAsker (вспомогательный класс для запросов пользователю)";
    }
}
