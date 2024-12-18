package com.servPet;

import java.sql.*;
import java.io.*;

class PhotoWrite {

	public static void main(String argv[]) {
		Connection con = null;
		PreparedStatement pstmt = null;
		InputStream fin = null;
		String url = "jdbc:mysql://localhost:3306/CIA103_G3?serverTimezone=Asia/Taipei";
		String userid = "root";
		String passwd = "cia10348";
		
		String adminPhotos = "src/main/resources/static/DBimages/admin"; //測試用圖片已置於【專案錄徑】底下的【resources/DBimages】目錄內
		String adminUpdate = "update admin set upFiles =? where ADMIN_ID=?";
		int adminCount = 1001;
		
		String pgComplPhotos = "src/main/resources/static/DBimages/pgCompl"; // pgCompl 圖片
		String pgComplUpdate = "update pet_groomer_complaint set PG_COMPL_UPFILES_1 =? where PG_COMPL_ID=?";
		int pgComplCount = 19001;
		
		String psComplPhotos = "src/main/resources/static/DBimages/psCompl"; // pgCompl 圖片
		String psComplUpdate = "update pet_sitter_complaint set PS_COMPL_UPFILES_1 =? where PS_COMPL_ID=?";
		int psComplCount = 20001;
		
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(adminUpdate);
			File[] photoFiles = new File(adminPhotos).listFiles();
			for (File f : photoFiles) {
				fin = new FileInputStream(f);
				pstmt = con.prepareStatement(adminUpdate);
				pstmt.setInt(2, adminCount);
				pstmt.setBinaryStream(1, fin);
				pstmt.executeUpdate();
				adminCount++;
				System.out.print(" update the database...");
				System.out.println(f.toString());
			}

			fin.close();
			pstmt.close();
			System.out.println("加入admin圖片-更新成功.........");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(pgComplUpdate);
			File[] photoFiles = new File(pgComplPhotos).listFiles();
			for (File f : photoFiles) {
				fin = new FileInputStream(f);
				pstmt = con.prepareStatement(pgComplUpdate);
				pstmt.setInt(2, pgComplCount);
				pstmt.setBinaryStream(1, fin);
				pstmt.executeUpdate();
				pgComplCount++;
				System.out.print(" update the database...");
				System.out.println(f.toString());
			}

			fin.close();
			pstmt.close();
			System.out.println("加入pgCompl圖片-更新成功.........");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(psComplUpdate);
			File[] photoFiles = new File(psComplPhotos).listFiles();
			for (File f : photoFiles) {
				fin = new FileInputStream(f);
				pstmt = con.prepareStatement(psComplUpdate);
				pstmt.setInt(2, psComplCount);
				pstmt.setBinaryStream(1, fin);
				pstmt.executeUpdate();
				psComplCount++;
				System.out.print(" update the database...");
				System.out.println(f.toString());
			}

			fin.close();
			pstmt.close();
			System.out.println("加入psCompl圖片-更新成功.........");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
