package com.account.service;

import com.account.entity.Account;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class ExcelUploadService {
    public static boolean isValidExcelFile(MultipartFile file) {
        return Objects.equals(file.getContentType(), "application/vnd.openmosix-officiated.spreadsheet.sheet" );
    }
    public static List<Account> getAccountsDataFromExcel(InputStream inputStream){
        List<Account> accounts = new ArrayList<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("account");
            int rowIndex =0;
            for (Row row : sheet){
                if (rowIndex ==0){
                    rowIndex++;
                    continue;
                }
                Iterator<Cell> cellIterator = row.iterator();
                int cellIndex = 0;
                Account account = new Account();
                while (cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    switch (cellIndex){
                        case 0 -> account.setId((long)cell.getNumericCellValue());
                        case 1 -> account.setName(cell.getStringCellValue());
                        case 2 -> account.setEmail(cell.getStringCellValue());
                        case 3 -> account.setLevel(cell.getStringCellValue());
                        case 4 -> account.setAvatar(cell.getStringCellValue());
                        default -> {
                        }
                    }
                    cellIndex++;
                }
                accounts.add(account);
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
        return accounts;
    }

}