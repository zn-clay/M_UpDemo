package com.zn.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * springMVC文件上传分解器:CommonsMultipartResolver 用于处理文件上传,当收的请求是DispatchServlet的
 * checkMultipart()方法会调用CommonsMultipartResolver
 * 的isMultipart()方法判断请求中是否含有文件.如果请求中有文件,则调用
 * CommonsMultipartResolver的resolveMutipater()方法
 * 进行文件解析并封装成MutipartHttpServletRequest(继承了HttpServletRequest) 并传递给controller
 * 
 * @author Administrator
 *
 */
@Controller
public class UploadController {

	@RequestMapping("/toDownImg.do")
	public String toDownImg(HttpServletRequest request) {
		List<String> imgNameList = new ArrayList<String>();
		String realPath = "D:\\eclipse\\A_M_Demo_up\\src\\main\\webapp\\images";
		
		File file = new File(realPath);
		if (file.isDirectory()) {
			String[] fileList = file.list();
			for (String string : fileList) {
				File readFile= new File(realPath+"\\"+string);
				if (!readFile.isDirectory()) {
					imgNameList.add(readFile.getName());
				}
			}			
		}
		System.out.println(imgNameList.size()+"第三方");
		request.setAttribute("imgNameList", imgNameList);
		for (String string : imgNameList) {
			System.out.println(string);
		}
		return "downImg";
	}
//上传
	@RequestMapping("/upload.do")
	public String upload(HttpServletRequest request, @RequestParam("description") String description,
			@RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {
		System.out.println(file);
		System.out.println(description);
		if (!file.isEmpty()) {
			String path = "D:\\eclipse\\A_M_Demo_up\\src\\main\\webapp\\images";
			String filename = file.getOriginalFilename();
			request.setAttribute("image", filename);
			// 判断路径是否存在,不存在则新建一个
			File file2 = new File(path, filename);
			if (file2.getParentFile().exists()) {
				file2.getParentFile().mkdirs();
			}
			// 将上传的文件保存到一个文件目录里
			file.transferTo(new File(path + File.separator + filename));

			return "OK";
		} else {
			return "error";
		}
	}

	@RequestMapping("/upload_html.do")
	public String upload_html(HttpServletRequest request, @RequestParam("inf_img") String inf_img,
			@RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {
		if (file == null) {
			return "error";
		}
		String path = "D:\\eclipse\\A_M_Demo_up\\src\\main\\webapp\\images";
		String fileName = file.getOriginalFilename();
		System.out.println(fileName);
		request.setAttribute("upImgName", fileName);
		File file2 = new File(path, fileName);
		if (file2.getParentFile().exists()) {
			file2.getParentFile().mkdirs();
		}
		file.transferTo(new File(path + File.separator + fileName));

		return "OK";
	}

	@RequestMapping("/ToDownImg.do")
	public void ToDownImg(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String path = "D:\\eclipse\\A_M_Demo_up\\src\\main\\webapp\\images\\9.jpg";
		InputStream bis = new BufferedInputStream(new FileInputStream(new File(path)));
		String filename = "下载文件.jpg";
		filename = URLEncoder.encode(filename, "UTF-8");
		response.addHeader("Content-Disposition", "attachment;filename=" + filename);
		response.setContentType("multipart/form-data");
		BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
		int len = 0;
		while ((len = bis.read()) != -1) {
			out.write(len);
			out.flush();
		}
		out.close();
	}

}
