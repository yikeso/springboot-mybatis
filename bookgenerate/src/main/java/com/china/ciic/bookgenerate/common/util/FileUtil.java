package com.china.ciic.bookgenerate.common.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class FileUtil {

    private static Logger log = LoggerFactory.getLogger(FileUtil.class);
    //缓存大小
    private static final int BUF_SIZE = 1024 * 50;

    /**
     * 保存文件信息
     *
     * @param content
     * @param path 文件的路径
     */
    public static synchronized void saveFile(String content, String path) throws IOException {
        if (content == null || content.trim().length() == 0) {
            return;
        }
        File file = new File(path);
        log.info("保存文件：\n"+file.getAbsolutePath());
        File dir = file.getParentFile();
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream outSTr = null;
        try {
            outSTr = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(outSTr,"UTF-8");
            PrintWriter pw = new PrintWriter(osw);
            pw.print(content);
            pw.flush();
        }  finally {
            if (null != outSTr) {
                try {
                    outSTr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 创建文件夹
     *
     * @param path
     */
    public static void createrFolder(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    /**
     * 读取文件中的txt
     *
     * @param path
     * @return
     */
    public static String readerText(String path, String code) throws IOException {
        return readerText(new File(path), code);
    }

    /**
     * 读取文件中的txt
     *
     * @param txt
     * @return
     */
    public static String readerText(File txt, String code) throws IOException {
        byte[] buffer = new byte[1024 * 50];
        FileInputStream fis = null;
        ByteArrayOutputStream bos = null;
        try {
            fis = new FileInputStream(txt);
            int length = -1;
            bos = new ByteArrayOutputStream();
            while ((length = fis.read(buffer)) != -1) {
                bos.write(buffer, 0, length);
            }
            return new String(bos.toByteArray(), code);
        } finally {
            if (fis != null) {
                fis.close();
            }
            if (bos != null) {
                bos.close();
            }
        }
    }

    /**
     * 读取文件中的txt
     *
     * @param path
     * @return
     */
    public static String readerText(String path) throws IOException {
        return readerText(new File(path));
    }

    /**
     * 读取文件中的txt
     *
     * @param txt
     * @return
     */
    public static String readerText(File txt) throws IOException {
        byte[] buffer = new byte[1024 * 50];
        FileInputStream fis = null;
        ByteArrayOutputStream bos = null;
        try {
            fis = new FileInputStream(txt);
            int length = -1;
            bos = new ByteArrayOutputStream();
            while ((length = fis.read(buffer)) != -1) {
                bos.write(buffer, 0, length);
            }
            return new String(bos.toByteArray());
        } finally {
            if (fis != null) {
                fis.close();
            }
            if (bos != null) {
                bos.close();
            }
        }
    }

    /**
     * 格式化文件路径
     *
     * @param filePath
     * @return String
     * @Title: formatFilePath
     * @Description:
     * @author kakasun
     * @date 2017年3月7日下午3:20:04
     */
    public static String formatFilePath(String filePath) {
        return filePath.replaceAll("[\\\\/]+", "/");
    }


    /**
     * 读取传入的字节流，返回读取到的字节数组
     *
     * @param is 输入流
     * @return 字节数组
     * @throws IOException
     * @author kakasun
     */
    public static byte[] getBytes(InputStream is) throws IOException {
        byte[] buffer = new byte[BUF_SIZE];// 创建缓冲区
        int read = -1;
        // 创建字节缓存输出流
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            while ((read = is.read(buffer)) != -1) {
                // 读取文件的字节数据写入缓存流中
                bos.write(buffer, 0, read);
            }
            // 将缓存流中的字节数据转换为byte数组返回
            return bos.toByteArray();
        } finally {
            bos.close();
        }
    }
}
