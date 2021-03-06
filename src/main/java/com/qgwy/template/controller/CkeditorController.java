package com.qgwy.template.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Controller
@RequestMapping("/ckeditor")
@Api(tags = {"ckeditor测试"})
public class CkeditorController {

	//图片上传路径
	final String  imageFilePath = System.getProperty("user.dir") + "/src/main/resources/static/upload/marketing/images/";
	//视频上传路径
	final String  videoFilePath = System.getProperty("user.dir") + "/src/main/resources/static/upload/marketing/videos/";
	final String  videoName = "marketing_video";
	final String  imageName = "marketing_image";

	/**
	 * 进入编辑器页面
	 * @return
	 */
	@GetMapping("/edit")
	@ApiOperation("编辑器页面")
	public String editor() {
		return "html/edit";
	}

	/**
	 * 编辑器图片上传实现
	 * @param file
	 * @param CKEditorFuncNum
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@PostMapping("/imageUpload")
	@ApiOperation("图片上传")
	//名字upload是固定的
	public String ckeditorUpload(@RequestParam("upload") MultipartFile file, String CKEditorFuncNum) throws Exception {
		// 获取文件名
		String fileName = file.getOriginalFilename();
		// 获取文件的后缀名
		String suffixName = fileName.substring(fileName.lastIndexOf("."));
		String newFileName = this.imageName+ System.currentTimeMillis()+fileName;
		FileUtils.copyInputStreamToFile(file.getInputStream(), new File(this.imageFilePath + newFileName));
		//实现图片回显
		StringBuffer sb = new StringBuffer();
		sb.append("<script type=\"text/javascript\">");
		sb.append("window.parent.CKEDITOR.tools.callFunction(" + CKEditorFuncNum + ",'" + "/upload/marketing/images/" + newFileName
				+ "','')");
		sb.append("</script>");

		return sb.toString();
	}
	/**
	 * 编辑器视频上传实现
	 * @param file
	 * @param CKEditorFuncNum
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@PostMapping("/videoUpload")
	@ApiOperation("视频上传")
	//名字upload是固定的
	public String videoUpload(@RequestParam("upload") MultipartFile file, String CKEditorFuncNum) throws Exception {
		// 获取文件名
		String fileName = file.getOriginalFilename();
		// 获取文件的后缀名
		String suffixName = fileName.substring(fileName.lastIndexOf("."));
		String newFileName = this.videoName+ System.currentTimeMillis() +fileName;
		FileUtils.copyInputStreamToFile(file.getInputStream(), new File(this.videoFilePath + newFileName));
		//实现视频回显
		StringBuffer sb = new StringBuffer();
		sb.append("<script type=\"text/javascript\">");
		sb.append("window.parent.CKEDITOR.tools.callFunction(" + CKEditorFuncNum + ",'" + "/upload/marketing/videos/" + newFileName
				+ "','')");
		sb.append("</script>");

		return sb.toString();
	}

	@GetMapping("showHtml")
	@ApiOperation("跳转到目标页")
	public String receiveHtml(String editor1){
		System.out.println(editor1);
		return "redirect:edit";
	}

}
