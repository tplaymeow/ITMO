package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class CustomBufferReader extends BufferedReader {
    public CustomBufferReader(Reader in, int sz) {
        super(in, sz);
    }

    public CustomBufferReader(Reader in) {
        super(in);
    }

    @Override
    public String readLine() throws IOException {
        String request = super.readLine();
        return request.equals("") ? null : request;
    }
}
