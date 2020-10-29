package com.example.demo.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Md5Util {
    private final static Log log = LogFactory.getLog(Md5Util.class);

    static {

    }

    private static MessageDigest getMessageDigest() throws NoSuchAlgorithmException {
        return MessageDigest.getInstance("MD5");
    }

    /**
     * 对一个文件求他的md5值
     *
     * @param f
     *            要求md5值的文件
     * @return md5串
     */
    public static byte[] md5(File f) {
        FileInputStream fis = null;
        try {
            MessageDigest md = getMessageDigest();
            fis = new FileInputStream(f);
            FileChannel ch = fis.getChannel();
            MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0, f.length());
            md.update(byteBuffer);
            return md.digest();
        } catch (FileNotFoundException e) {
            log.error("md5 file " + f.getAbsolutePath() + " failed:" + e.getMessage());
            return null;
        } catch (IOException e) {
            log.error("md5 file " + f.getAbsolutePath() + " failed:" + e.getMessage());
            return null;
        } catch (NoSuchAlgorithmException e) {
            // impossible
            return null;
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String md5Str(File f) {
        return bytesToHex(md5(f));
    }

    /**
     * 字符串摘要
     */
    public static String md5Str(String data) throws NoSuchAlgorithmException {
        try {
            return bytesToHex(getMessageDigest().digest(data.getBytes("utf-8")));
        } catch (UnsupportedEncodingException e) {
        }
        return "";
    }

    private static String bytesToHex(byte bytes[]) {
        return bytesToHex(bytes, 0, bytes.length);
    }

    private static String bytesToHex(byte bytes[], int m, int n) {

        if (bytes == null) {
            return null;
        }

        StringBuilder stringbuffer = new StringBuilder(2 * n);
        int k = m + n;
        for (int l = m; l < k; l++) {
            appendHexPair(bytes[l], stringbuffer);
        }
        return stringbuffer.toString();
    }

    private static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
            'f' };

    private static void appendHexPair(byte bt, StringBuilder stringbuffer) {
        char c0 = hexDigits[(bt & 0xf0) >> 4];
        char c1 = hexDigits[bt & 0xf];
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }

    private static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++)
            resultSb.append(byteToHexString(b[i]));

        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n += 256;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexStringDigits[d1] + hexStringDigits[d2];
    }

    public static String MD5Encode(String origin, String charsetname) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname == null || "".equals(charsetname))
                resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
            else
                resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
        } catch (Exception exception) {
        }
        return resultString;
    }

    private static final String hexStringDigits[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

//    public static void main(String[] args) throws IOException, InterruptedException {
//
//        final File file = new File("/md5test.bin");
//
//        for (int i = 0; i < 30; i++) {
//            Thread thread = new Thread() {
//                @Override
//                public void run() {
//                    long start = System.currentTimeMillis();
//                    String md51 = md5Str(file);
//                    long end = System.currentTimeMillis();
//                    System.out.println(md51 + " use " + (end - start) + "ms");
//                }
//            };
//            thread.start();
//        }
//
//        TimeUnit.SECONDS.sleep(10);

        // file = new
        // File("C:/Users/jiacheo/Desktop/ExportItemlList2012113020541.back.csv");
        //
        // String md52 = md5Str(file);
        //
        // Assert.isTrue(md52.equals(md51));
        //
        // System.out.println(md52);

        // FileWriter writer = new FileWriter(new File("/md5test.bin"));
        //
        // BufferedWriter bw = new BufferedWriter(writer);
        //
        // for(int i=0; i< 10000000; i++){
        // bw.write("sfdsfsdfsdflsdfjsldfjsldjfksdlfjlsdkfjlsdkfjdlskjflakdfjlskfjslkdfjlskfjalkfjaslfjdlksajflkjdsaflkjdasklfjladsfjd"
        // + i);
        // }
        // bw.close();
//    }

}
