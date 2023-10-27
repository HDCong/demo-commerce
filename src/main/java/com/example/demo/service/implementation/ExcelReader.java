package com.example.demo.service.implementation;

import com.example.demo.domain.dto.CustomerDto;
import com.example.demo.domain.dto.ProductDto;
import com.example.demo.domain.dto.ShopDto;
import com.example.demo.service.IFileReader;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
public class ExcelReader implements IFileReader {

  @Override
  public List<CustomerDto> readImportCustomerFile(MultipartFile file) {
    if (file != null && file.getOriginalFilename() != null) {
      try (InputStream excelFile = file.getInputStream()) {
        final List<CustomerDto> dataList = new ArrayList<>();
        try (Workbook workbook = new XSSFWorkbook(excelFile)) {
          final Sheet sheet = workbook.getSheetAt(0);

          for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
            final Row row = sheet.getRow(rowIndex);
            Iterator<Cell> cellIterator = row.cellIterator();

            final CustomerDto data = new CustomerDto();

            int cellIndex = 0;
            while (cellIterator.hasNext()) {
              final Cell cell = cellIterator.next();

              switch (cellIndex) {
                case 0 -> data.setName(cell.getStringCellValue());
                case 1 -> {
                  try {
                    Date cellValue = cell.getDateCellValue();
                    data.setDob(cellValue.getTime());
                  } catch (Exception e) {
                    log.error("Error when read row {}, index {}", rowIndex, cellIndex);
                  }
                }
                case 2 -> data.setEmail(cell.getStringCellValue());
                default -> {}
              }
              cellIndex++;
            }
            dataList.add(data);
          }
        }
        return dataList;
      } catch (IOException e) {
        log.error("Can not read customer file", e);
      }
    }
    return new ArrayList<>();
  }

  @Override
  public List<ShopDto> readImportShopFile(MultipartFile file) {
    if (file != null && file.getOriginalFilename() != null) {
      try (InputStream excelFile = file.getInputStream()) {
        final List<ShopDto> dataList = new ArrayList<>();
        try (Workbook workbook = new XSSFWorkbook(excelFile)) {
          final Sheet sheet = workbook.getSheetAt(0);

          for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
            final Row row = sheet.getRow(rowIndex);
            Iterator<Cell> cellIterator = row.cellIterator();

            ShopDto data = new ShopDto();

            int cellIndex = 0;
            while (cellIterator.hasNext()) {
              final Cell cell = cellIterator.next();

              switch (cellIndex) {
                case 0 -> data.setName(cell.getStringCellValue());
                case 1 -> data.setLocation(cell.getStringCellValue());
                default -> {}
              }
              cellIndex++;
            }
            dataList.add(data);
          }
        }
        return dataList;
      } catch (IOException e) {
        log.error("Can not read shop file", e);
      }
    }
    return new ArrayList<>();
  }

  @Override
  public List<ProductDto> readImportProductFile(MultipartFile file, String shopId) {
    if (file != null && file.getOriginalFilename() != null) {
      try (InputStream excelFile = file.getInputStream()) {
        final List<ProductDto> dataList = new ArrayList<>();
        try (Workbook workbook = new XSSFWorkbook(excelFile)) {
          final Sheet sheet = workbook.getSheetAt(0);

          for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
            final Row row = sheet.getRow(rowIndex);
            Iterator<Cell> cellIterator = row.cellIterator();

            final ProductDto data = new ProductDto();

            int cellIndex = 0;
            while (cellIterator.hasNext()) {
              final Cell cell = cellIterator.next();

              switch (cellIndex) {
                case 0 -> data.setName(cell.getStringCellValue());
                case 1 -> {
                  try {
                    data.setPrice(BigDecimal.valueOf(cell.getNumericCellValue()));
                  } catch (Exception e) {
                    log.error("Error when parse row {} cell {}", rowIndex, cellIndex);
                  }
                }
                case 2 -> data.setShopId(StringUtils.defaultIfBlank(cell.getStringCellValue(), shopId));
                default -> {}
              }
              cellIndex++;
            }
            dataList.add(data);
          }
        }
        return dataList;
      } catch (IOException e) {
        log.error("Can not read product file", e);
      }
    }
    return new ArrayList<>();
  }
}
