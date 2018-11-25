package com.taotao.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import com.taotao.common.utils.FtpUtil;

public class FTPTest {
	
	@Test
	public void testFtpClient() throws SocketException, IOException{
		FTPClient client = new FTPClient();
		client.connect("172.16.16.100", 21);
		client.login("ftpuserliyu", "ftpuserliyu");
		FileInputStream inputStream = new FileInputStream(new File("/Users/liyu/liyu_work/resources/test1.jpg"));
		client.changeWorkingDirectory("/home/ftpuserliyu/www/images");
		client.setFileType(FTP.BINARY_FILE_TYPE);
		client.storeFile("hello1.jpg", inputStream);
		client.logout();
	}
	
	@Test
	public void testFtpUtil() throws FileNotFoundException{
		FileInputStream inputStream = new FileInputStream(new File("/Users/liyu/liyu_work/resources/test1.jpg"));
		FtpUtil.uploadFile("172.16.16.100", 21, "ftpuserliyu", "ftpuserliyu", "/home/ftpuserliyu/www/images", "/2017/01/17", "hello.jpg", inputStream);
	}

}
