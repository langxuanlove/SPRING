package com.util;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.util.qrcode.QrCodeDeWrapper;
import com.util.qrcode.QrCodeGenWrapper;
import com.util.qrcode.QrCodeOptions;

public class GenQrCodeTest {
	public static void main(String[] args) {
		testDeCode();
	}
//	@Test
	public static void testDeCode(){
		// 以下路径为classes中的资源文件目录
		String path="gen.png";
		// 以下是linux的绝对路径
		String path1="/usr/local/demo.png";
		String pathwindow="E:/eclipse-workspace/Spring_Boot/src/test/qrcode/gen.png";
		// 以下是互联网的路径
		String path2="http://XXX.XXX/log.png";
		try {
			System.out.println(QrCodeDeWrapper.decode(pathwindow));
		} catch (FormatException | ChecksumException | NotFoundException
				| IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * 测试二维码
	 * url变为二维码
	 */
//	@Test
	public void testGenQrCode() {
	    String msg = "https://my.oschina.net/u/566591/blog/1359432";
//	    try {
//	        boolean ans = QrCodeGenWrapper.of(msg).asFile("src/test/qrcode/gen.png");
//	        System.out.println(ans);
//	    } catch (Exception e) {
//	        System.out.println("create qrcode error! e: " + e);
//	        Assert.assertTrue(false);
//	    }


	    //生成红色的二维码 300x300, 无边框
//	    try {
//	        boolean ans = QrCodeGenWrapper.of(msg)
//	                .setW(300)
//	                .setPreColor(0xffff0000)
//	                .setBgColor(0xffffffff)
//	                .setPadding(0)
//	                .asFile("src/test/qrcode/gen_300x300.png");
//	        System.out.println(ans);
//	    } catch (Exception e) {
//	        System.out.println("create qrcode error! e: " + e);
//	        Assert.assertTrue(false);
//	    }


	    // 生成带logo的二维码
//	    try {
//	        String logo = "https://static.oschina.net/uploads/user/283/566591_100.jpeg";
//	        boolean ans = QrCodeGenWrapper.of(msg)
//	                .setW(300)
//	                .setPreColor(0xffff0000)
//	                .setBgColor(0xffffffff)
//	                .setPadding(0)
//	                .setLogo(logo)
//	                .setLogoStyle(QrCodeOptions.LogoStyle.ROUND)
//	                .asFile("src/test/qrcode/gen_300x300_logo.png");
//	        System.out.println(ans);
//	    } catch (Exception e) {
//	        System.out.println("create qrcode error! e: " + e);
//	        Assert.assertTrue(false);
//	    }


	    // 根据本地文件生成待logo的二维码
//	    try {
//	        String logo = "logo.jpg";
//	        boolean ans = QrCodeGenWrapper.of(msg)
//	                .setW(300)
//	                .setPreColor(0xffff0000)
//	                .setBgColor(0xffffffff)
//	                .setPadding(0)
//	                .setLogo(logo)
//	                .asFile("src/test/qrcode/gen_300x300_logo_v2.png");
//	        System.out.println(ans);
//	    } catch (Exception e) {
//	        System.out.println("create qrcode error! e: " + e);
//	        Assert.assertTrue(false);
//	    }
	}

}

