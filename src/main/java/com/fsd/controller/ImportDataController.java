package com.fsd.controller;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fsd.util.ExcelUtil;
import com.fsd.util.ResponseCode;
import com.fsd.util.ResponseResult;
import com.fsd.vo.SummaryInfo;
@CrossOrigin
@RestController
public class ImportDataController {

    @Autowired
    ExcelUtil excelUtil;

    @RequestMapping("/file/download")
	public ResponseEntity<InputStreamResource> downloadexcel(HttpServletRequest request) 
			throws IOException, ParseException {
        ResponseEntity<InputStreamResource> response = excelUtil.downloadExcel();
		return response;
    }

    @RequestMapping("/file/upload")
    public ResponseResult uploadexcel(@RequestParam("file")MultipartFile file) throws IOException, ParseException {
        String name=file.getOriginalFilename();
          if(name.length()<6|| !name.substring(name.length()-5).equals(".xlsx")){
              return ResponseResult.build(ResponseCode.FILE_FORMAT_ERROR, "Error File Format.");
          }
        SummaryInfo summaryInfo = (SummaryInfo) excelUtil.uploadExcel(file.getInputStream());
        System.out.println(summaryInfo);
        return ResponseResult.build(ResponseCode.SUCCESS, "Success!", summaryInfo);
        
    }

}