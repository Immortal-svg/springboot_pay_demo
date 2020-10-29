package com.example.demo.common.utils;




import  java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Administrator
 */
public class FileUtils {


    /**
     * 获取路径文件夹下的所有文件
     *
     * @param path
     * @return
     */
    public static File[] getKeywordFiles(String path) {
        File dir = new File(path);
        if (!dir.exists()) {
            return null;
        }
        File[] fs = dir.listFiles();
        return fs;
    }

    /**
     * 删除文件夹 param folderPath 文件夹完整绝对路径
     */
    private static void delFolder(String folderPath) {
        try {
            String filePath = folderPath;
            filePath = filePath.toString();
            File myFilePath = new File(filePath);
            myFilePath.delete(); // 删除空文件夹
        } catch (Exception e) {

        }
    }

    /**
     * 读取一个文件
     *
     * @param filePathAndName
     * @return
     * @throws IOException
     */
    public static List<String> readFile(String filePathAndName)
            throws IOException {
        FileInputStream fis = new FileInputStream(filePathAndName);
        InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        LineNumberReader lnr = new LineNumberReader(br);

        List<String> returnValue = new ArrayList<String>();
        int cnt = 0;
        while (true) {
            cnt++;
            String tempStr = lnr.readLine();
            if (tempStr == null)
                break;
            if (tempStr.length() < 2)
                continue;
            returnValue.add(tempStr);
        }
        lnr.close();
        br.close();
        isr.close();
        fis.close();
        return returnValue;
    }

    /**
     * 读取一个文件,并排重后返回
     */
    public static List<String> readFileNoDup(String filePathAndName)
            throws IOException {
        FileInputStream fis = new FileInputStream(filePathAndName);
        InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        LineNumberReader lnr = new LineNumberReader(br);

        Set<String> set = new HashSet<String>();
        while (true) {
            String tempStr = lnr.readLine();
            if (tempStr == null) {
                break;
            }
            if (tempStr.length() < 2) {
                continue;
            }
            set.add(tempStr.trim());
        }
        lnr.close();
        br.close();
        isr.close();
        fis.close();
        List<String> returnValue = new ArrayList<String>(set.size());
        returnValue.addAll(set);
        return returnValue;
    }

    /**
     * 添加内容到指定文件 如果该文件不存在，则创建并添加内容 如果该文件已存在，则添加内容到已有内容最后
     * flag为true，则向现有文件中添加内容，否则覆盖原有内容
     */
    public static void writeFile(String filePathAndName, String fileContent,
                                 boolean flag) throws IOException {
        if (null == fileContent || fileContent.length() < 1)
            return;
        File file = new File(filePathAndName);

        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(filePathAndName, flag);
        OutputStreamWriter osw = new OutputStreamWriter(fos, "utf-8");
        osw.write(fileContent + "\r\n");
        osw.flush();
        osw.close();
    }

    /**
     * 添加内容到指定文件 如果该文件不存在，则创建并添加内容 如果该文件已存在，则添加内容到已有内容最后
     * flag为true，则向现有文件中添加内容，否则覆盖原有内容
     */
    public static void writeFile(String filePathAndName,
                                 List<String> fileContent, boolean flag) throws IOException {
        File file = new File(filePathAndName);

        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(filePathAndName, flag);
        OutputStreamWriter osw = new OutputStreamWriter(fos, "utf-8");
        for (String temp : fileContent)
            osw.write(temp + "\r\n");
        osw.flush();
        osw.close();
    }

    /**
     * 添加内容到指定文件 如果该文件不存在，则创建并添加内容 如果该文件已存在，则添加内容到已有内容最后
     * flag为true，则向现有文件中添加内容，否则覆盖原有内容
     */
    public static void writeFile(String filePath, String filename,
                                 List<String> fileContent, boolean flag) throws IOException {
        File file = new File(filePath);

        if (!file.exists()) {
            boolean tempFlag = file.mkdirs();
            if (!tempFlag) {

                return;
            }
        }

        file = new File(filePath, filename);

        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(filePath + filename, flag);
        OutputStreamWriter osw = new OutputStreamWriter(fos, "utf-8");
        for (String temp : fileContent)
            osw.write(temp + "\r\n");
        osw.flush();
        osw.close();
    }
}
