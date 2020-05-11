package com.fsd.util;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.fsd.domain.StockPriceDetail;
import com.fsd.repository.CompanyRepository;
import com.fsd.repository.StockPriceDetailRepository;
import com.fsd.vo.SummaryInfo;

@Component
public class ExcelUtil {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private StockPriceDetailRepository stockPriceDetailRepository;
    /**
     * 
     * @return
     * @throws GlobalException
     * @throws IOException
     */
    public ResponseEntity<InputStreamResource> downloadExcel() throws IOException {
        final String filePath = "static/sample_stock_data.xlsx";
        ClassPathResource classPathResource = new ClassPathResource(filePath);
        InputStream inputStream = classPathResource.getInputStream();

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", "sample_stock_data.xlsx");
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(new InputStreamResource(inputStream));
    }

    public SummaryInfo uploadExcel(InputStream inputStream) throws IOException, ParseException {
        List<StockPriceDetail> list = new ArrayList<>();
        Workbook workbook = null;
        workbook = WorkbookFactory.create(inputStream);
        inputStream.close();
        //工作表对象
        Sheet sheet = workbook.getSheetAt(0);
        //总行数
        int rowLength = sheet.getLastRowNum();
        System.out.println("总行数有多少行"+rowLength);
        //工作表的列
        Row row = sheet.getRow(1);
        //总列数
        int colLength = row.getLastCellNum();
        System.out.println("总列数有多少列"+colLength);
        
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

        //summaryinfo
        SummaryInfo summaryInfo = new SummaryInfo();
        summaryInfo.setNumofimported(rowLength);
        //this.companyRepository.findByCompanycode(row.getCell(0).getStringCellValue().trim());
        System.out.println(row.getCell(0).getStringCellValue().trim().replaceAll("\\u00A0",""));
        String companyname = this.companyRepository.getByCompanycode(row.getCell(0).getStringCellValue().trim().replaceAll("\\u00A0",""));
        System.out.println(companyname);
        summaryInfo.setCompanyname(companyname);
        summaryInfo.setStockexchange(row.getCell(1).getStringCellValue().trim());
        String fromDate = ""+new Date(row.getCell(3).getDateCellValue().getTime())+" "+new Time(timeFormat.parse(row.getCell(4).getStringCellValue().trim()).getTime());
        System.out.println("fromDate"+fromDate);
        summaryInfo.setFromdate(fromDate);
        String toDate = ""+new Date(sheet.getRow(rowLength).getCell(3).getDateCellValue().getTime())+" "+new Time(timeFormat.parse(sheet.getRow(rowLength).getCell(4).getStringCellValue().trim()).getTime());
        System.out.println("toDate"+toDate);
        summaryInfo.setTodate(toDate);
          
        for (int i = 1; i < rowLength+1; i++) {
            row = sheet.getRow(i);
            StockPriceDetail stockPriceDetail = new StockPriceDetail();
            stockPriceDetail.setCompanycode(row.getCell(0).getStringCellValue().trim().replaceAll("\\u00A0",""));
            stockPriceDetail.setStockexchange(row.getCell(1).getStringCellValue().trim());
            stockPriceDetail.setCurrentprice(BigDecimal.valueOf(row.getCell(2).getNumericCellValue()));
            stockPriceDetail.setDate(new Date(row.getCell(3).getDateCellValue().getTime()));
            stockPriceDetail.setTime(new Time(timeFormat.parse(row.getCell(4).getStringCellValue().trim()).getTime()));
            stockPriceDetail.setUpdatets(new Timestamp(System.currentTimeMillis()));
            System.out.println(stockPriceDetail);
            list.add(stockPriceDetail);
        }
        this.stockPriceDetailRepository.saveAll(list);
        return summaryInfo;
    }
}