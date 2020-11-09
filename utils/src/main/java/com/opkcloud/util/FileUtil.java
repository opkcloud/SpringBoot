package com.opkcloud.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.ParseException;
import java.util.Date;

/**
 * @desc   文件工具类
 */
public class FileUtil {

    /**
     * 创建一级目录
     *
     * @param path
     */
    public static void mkDirIfNotExist(String path){
        File file = new File(path);
        if(!file.exists()){
            file.mkdir();
        }
    }

    /**
     * 创建一级或多级目录
     *
     * @param path
     */
    public static void mkMultiDirIfNotExist(String path){
        File directory = new File(path);
        if(!directory.exists()){
            directory.mkdirs();
        }
    }

    public static void emptyDir(String dir) {
        File file = new File(dir);
        if (!file.exists()) {
            return;
        }

        String[] files = file.list();
        for (String name : files) {
            File temp = new File(dir, name);
            if (temp.isDirectory()) {
                emptyDir(temp.getAbsolutePath());
                temp.delete();
                continue;
            }
            temp.delete();

        }
    }

    /**
     * 删除指定目录某个时间点之前的文件
     *
     * @param dirs
     * @param dateTime
     * @throws ParseException
     */
    public static void deleteDirFilesBeforeTime(String dirs, Date dateTime) {
        File dir = new File(dirs);
        File[] list = dir.listFiles();
        for (File file : list) {
            if (file.isDirectory()) {
                if (file.list().length > 0) {
                    deleteDirFilesBeforeTime(file.getAbsolutePath(), dateTime);
                }
            } else {
                Path p = Paths.get(file.getPath());
                //通过文件的属性来获取文件的创建时间
                BasicFileAttributeView basicview = Files.getFileAttributeView(p, BasicFileAttributeView.class,
                        LinkOption.NOFOLLOW_LINKS);
                BasicFileAttributes attr;
                try {
                    attr = basicview.readAttributes();
                    Date createTime = new Date(attr.creationTime().toMillis());
                    if (createTime.getTime() <= dateTime.getTime()) {
                        file.delete();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 判断文件大小
     *
     * @param len
     *            文件长度
     * @param size
     *            限制大小
     * @param fileSizeUnit
     *            限制单位（B,K,M,G）
     * @return
     */
    /*public static boolean checkFileSize(Long len, int size, FileSizeUnit fileSizeUnit) {
        double fileSize = 0;

        switch (fileSizeUnit) {
            case B:
                fileSize = (double) len;
                break;
            case K:
                fileSize = (double) len / 1024;
                break;
            case M:
                fileSize = (double) len / 1048576;
                break;
            case G:
                fileSize = (double) len / 1073741824;
                break;
            default:
                break;
        }

        if (fileSize > size) {
            return false;
        }

        return true;
    }*/

}
