package com.ning.tool.execute;

import com.ning.tool.config.MainConfig;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MdExecute {
    public String getMd5(String filePath) throws NoSuchAlgorithmException, IOException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        FileInputStream fis = new FileInputStream(filePath);

        byte[] buffer = new byte[8192];
        int bytesRead;
        while ((bytesRead = fis.read(buffer)) != -1) {
            // 处理输入数据
            md.update(buffer, 0, bytesRead);
        }

        fis.close();
        // 生成最终的哈希值
        byte[] digest = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            // 一个字节（byte）值转换为两位的十六进制字符串表示形式
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }

    public String changeMd5(String orgPath,String nulPath) throws IOException, InterruptedException {
        String outputFilePath = createOutputFile(orgPath);
        String command = "copy /b "+orgPath+"+ "+nulPath+" "+outputFilePath+"\\output.mp4";
            ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", command);
            builder.redirectErrorStream(true);
            Process process = builder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            System.out.println("Exit Code: " + exitCode);
            return outputFilePath+"\\output.mp4";
    }

    private String createOutputFile(String filePath){
        File file = new File(filePath);
        String fileName = file.getName();
        String fileNameWithoutExtension = fileName.substring(0, fileName.lastIndexOf("."));

        String folderPath = file.getParent() + File.separator + fileNameWithoutExtension;

        File folder = new File(folderPath);
        if (!folder.exists()) {
            boolean created = folder.mkdir();
            if (created) {
                System.out.println("文件夹创建成功：" + folderPath);
            } else {
                System.out.println("文件夹创建失败：" + folderPath);
            }
        } else {
            System.out.println("文件夹已存在：" + folderPath);
        }
        return folderPath;
    }

    /**
     *  待续
     * @param orgPath
     * @return
     * @throws URISyntaxException
     */
    public String getAbsolutionFilePath(String orgPath) throws URISyntaxException {
        String absolutePath ="";
        return absolutePath;
    }
}
