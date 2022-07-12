package br.com.via.explojista.dojo.grpc.service;

import br.com.via.explojista.dojo.grpc.Company;
import br.com.via.explojista.dojo.grpc.dto.Companies;
import br.com.via.explojista.dojo.grpc.dto.StockPrice;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

import static java.lang.Double.valueOf;
import static java.time.LocalDate.parse;
import static java.time.format.DateTimeFormatter.ofPattern;
import static org.apache.commons.csv.CSVFormat.EXCEL;

@Service
public class DataService {

    public List<StockPrice> getData(Company company) throws IOException {
        return this.getDataFromFile(company);
    }

    private List<StockPrice> getDataFromFile(Company company) throws IOException {
        String path = Companies.valueOf(company.name()).getQuotationPath();
        Reader in = new FileReader(getClass().getClassLoader().getResource(path).getPath());
        CSVFormat format = EXCEL.builder()
                .setSkipHeaderRecord(true)
                .setHeader("Date", "Close/Last", "Volume", "Open", "High", "Low")
                .build();
        return format.parse(in)
                .stream()
                .map(rec -> this.buildStockObject(company, rec))
                .toList();
    }

    private StockPrice buildStockObject(Company company, CSVRecord rec) {
        return new StockPrice(
                Companies.valueOf(company.name()),
                parse(rec.get("Date"), ofPattern("MM/dd/yyyy")),
                valueOf(rec.get("Close/Last")),
                valueOf(rec.get("Open")),
                valueOf(rec.get("High")),
                valueOf(rec.get("Low")),
                valueOf(rec.get("Volume"))
        );
    }
}
