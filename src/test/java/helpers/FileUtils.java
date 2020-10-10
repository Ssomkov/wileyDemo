package helpers;

import java.io.*;

public class FileUtils {

    public static String readFile(String path) {
        StringBuffer buffer = null;
        try {
            BufferedReader in = getBufferedReaderForFile(path);
            String inputLine;
            buffer = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                buffer.append(inputLine);
            }
            in.close();
        } catch (IOException e) {
            throw new RuntimeException("Error reading the file");
        }
        return buffer.toString();
    }

    private static BufferedReader getBufferedReaderForFile(final String path) {
        InputStream in = null;
        try {
            in = new FileInputStream(path);

        } catch (FileNotFoundException e) {
            throw new RuntimeException("Couldn't read the template file");
        }
        BufferedReader bf = null;
        try {
            bf = new BufferedReader(new InputStreamReader(in, "UTF8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Couldn't read the template file in the specified encoding");
        }
        return bf;
    }
}
