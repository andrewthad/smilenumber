package com.example.smilenumber;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Scanner;
import java.math.BigDecimal;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.smile.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
	Scanner scanner = new Scanner(System.in);
	String s = scanner.nextLine();
	BigDecimal number = new BigDecimal(s.trim());
	SmileFactory sf = new SmileFactory();
        byte[] encoded = _smileDoc(sf, number);
	System.out.write(encoded);
    }
    private static byte[] _smileDoc(SmileFactory smileFactory, BigDecimal number) throws IOException
    {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        JsonGenerator g = smileGenerator(out, true);
	g.writeNumber(number);
        g.close();
        return out.toByteArray();
    }

    private static SmileGenerator smileGenerator(OutputStream result, boolean addHeader)
        throws IOException
    {
        return smileGenerator(new SmileFactory(), result, addHeader);
    }
    private static SmileGenerator smileGenerator(SmileFactory f, OutputStream result, boolean addHeader)
        throws IOException
    {
        f.configure(SmileGenerator.Feature.WRITE_HEADER, addHeader);
        f.configure(SmileGenerator.Feature.CHECK_SHARED_NAMES, false);
        f.configure(SmileGenerator.Feature.CHECK_SHARED_STRING_VALUES, false);
        return f.createGenerator(result, null);
    }
}
