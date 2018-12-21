package com.fgi.ftp.client;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.*;
import java.net.InetAddress;

public class ClientTest {

    private static final String IP = "127.0.0.1";
    private static final int PORT = 21;
    private static final String LOGIN_NAME = "huang";
    private static final String PASSWORD = "123456";
    private static String path = "e:";
    private static String fileName = "test.txt";

    public void upload() throws Exception {
        FTPClient client = new FTPClient();
        client.connect(InetAddress.getByName(IP), PORT);
        if (!client.login(LOGIN_NAME, PASSWORD)) {
            System.out.println("login failed");
            return;
        }
        File file = new File(path + File.separator + fileName);
        InputStream in = new FileInputStream(file);
        // active
        client.setBufferSize(1024 * 1024 * 10);
        client.setFileType(FTP.BINARY_FILE_TYPE);
        client.changeWorkingDirectory("/");
//        System.out.println(client.stor("f:/test.zip"));
//        FTPFile[] files = client.listFiles("e:/upload/");
//        long remoteSize = 0;
//        if (file.length() == 1) { //服务器已存在一个MD5相同的文件
//            remoteSize = files[0].getSize(); //远程文件大小
//            if (remoteSize == file.length()) {
//                System.out.println("秒传完成");
//            }
//        }
        client.storeFile(fileName, in);
        System.out.println(client.getReplyCode());
        System.out.println(client.getReplyString());
        client.logout();
        client.disconnect();
    }

    public void download() throws Exception {
        FTPClient client = new FTPClient();
        client.connect(InetAddress.getByName(IP), PORT);
        if (!client.login(LOGIN_NAME, PASSWORD)) {
            System.out.println("login failed");
            return;
        }
        File file = new File("f:/download/test.txt");
        OutputStream out = new FileOutputStream(file);
        client.setFileType(FTP.BINARY_FILE_TYPE);
        client.changeWorkingDirectory("f:");
        client.retrieveFile("test.txt", out);
        client.logout();
        client.disconnect();
    }

    public static void main(String[] args) throws Exception {
        new ClientTest().download();
    }
}
