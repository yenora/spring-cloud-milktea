package com.example.common.util;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;

public class FtpUtil {
    public FtpUtil() {
    }

    public static boolean uploadFile(String host, int port, String username, String password, String basePath, String filePath, String filename, InputStream input) {
        boolean result = false;
        FTPClient ftp = new FTPClient();

        try {
            ftp.connect(host, port);
            ftp.login(username, password);
            int reply = ftp.getReplyCode();
            boolean var32;
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                var32 = result;
                return var32;
            }

            if (!ftp.changeWorkingDirectory(basePath + filePath)) {
                String[] dirs = filePath.split("/");
                String tempPath = basePath;
                String[] var13 = dirs;
                int var14 = dirs.length;

                for(int var15 = 0; var15 < var14; ++var15) {
                    String dir = var13[var15];
                    if (null != dir && !"".equals(dir)) {
                        tempPath = tempPath + "/" + dir;
                        if (!ftp.changeWorkingDirectory(tempPath)) {
                            if (!ftp.makeDirectory(tempPath)) {
                                boolean var17 = result;
                                return var17;
                            }

                            ftp.changeWorkingDirectory(tempPath);
                        }
                    }
                }
            }

            ftp.setFileType(2);
            if (!ftp.storeFile(filename, input)) {
                var32 = result;
                return var32;
            }

            input.close();
            ftp.logout();
            result = true;
        } catch (IOException var30) {
            var30.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException var29) {
                }
            }

        }

        return result;
    }

    public static boolean downloadFile(String host, int port, String username, String password, String remotePath, String fileName, String localPath) {
        boolean result = false;
        FTPClient ftp = new FTPClient();

        boolean var10;
        try {
            ftp.connect(host, port);
            ftp.login(username, password);
            int reply = ftp.getReplyCode();
            if (FTPReply.isPositiveCompletion(reply)) {
                ftp.changeWorkingDirectory(remotePath);
                FTPFile[] fs = ftp.listFiles();
                FTPFile[] var11 = fs;
                int var12 = fs.length;

                for(int var13 = 0; var13 < var12; ++var13) {
                    FTPFile ff = var11[var13];
                    if (ff.getName().equals(fileName)) {
                        File localFile = new File(localPath + "/" + ff.getName());
                        OutputStream is = new FileOutputStream(localFile);
                        ftp.retrieveFile(ff.getName(), is);
                        is.close();
                    }
                }

                ftp.logout();
                result = true;
                return result;
            }

            ftp.disconnect();
            var10 = result;
        } catch (IOException var26) {
            var26.printStackTrace();
            return result;
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException var25) {
                }
            }

        }

        return var10;
    }

    public static boolean deleteFile(String host, int port, String username, String password, String basePath, String filePath, String filename) {
        boolean result = false;
        FTPClient ftp = new FTPClient();

        try {
            ftp.connect(host, port);
            ftp.login(username, password);
            int reply = ftp.getReplyCode();
            boolean var32;
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                var32 = result;
                return var32;
            }

            if (!ftp.changeWorkingDirectory(basePath + filePath)) {
                String[] dirs = filePath.split("/");
                String tempPath = basePath;
                String[] var13 = dirs;
                int var14 = dirs.length;

                for(int var15 = 0; var15 < var14; ++var15) {
                    String dir = var13[var15];
                    if (null != dir && !"".equals(dir)) {
                        tempPath = tempPath + "/" + dir;
                        if (!ftp.changeWorkingDirectory(tempPath)) {
                            if (!ftp.makeDirectory(tempPath)) {
                                boolean var17 = result;
                                return var17;
                            }

                            ftp.changeWorkingDirectory(tempPath);
                        }
                    }
                }
            }

            ftp.setFileType(2);
            if (!ftp.deleteFile(filename)) {
                var32 = result;
                return var32;
            }

            ftp.logout();
            result = true;
        } catch (IOException var30) {
            var30.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException var29) {
                }
            }

        }

        return result;
    }

    public static void main(String[] args) {
        try {
            FileInputStream in = new FileInputStream(new File("C:\\Users\\Mr.yao\\Desktop\\1.jpg"));
            boolean flag = uploadFile("127.0.0.1", 21, "mMnDzq9", "123456", "/", "/upload/", "2.jpg", in);
//            boolean flag = downloadFile("127.0.0.1", 21, "milktea", "123456", "upload", "1.jpg", "F:\\");
            System.out.println(flag);
        } catch (FileNotFoundException var3) {
            var3.printStackTrace();
        }

        boolean del = deleteFile("127.0.0.1", 21, "milktea", "123456", "/", "/upload/", "2.jpg");
        System.out.println(del);
    }
}
