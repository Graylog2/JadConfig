package com.github.joschi.jadconfig.documentation.printers;

import com.github.joschi.jadconfig.documentation.ConfigurationEntry;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.lang3.BooleanUtils;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

public class CsvDocsPrinter implements DocsPrinter {

    public static final String HEADER_PARAMETER = "Parameter";
    public static final String HEADER_TYPE = "Type";
    public static final String HEADER_REQUIRED = "Required";
    public static final String HEADER_DEFAULT_VALUE = "Default value";
    public static final String HEADER_DESCRIPTION = "Description";
    public static final String[] HEADERS = {HEADER_PARAMETER, HEADER_TYPE, HEADER_REQUIRED, HEADER_DEFAULT_VALUE, HEADER_DESCRIPTION};
    private final CSVPrinter csvPrinter;

    public CsvDocsPrinter(OutputStreamWriter streamWriter) throws IOException {
        this.csvPrinter = new CSVPrinter(streamWriter, CSVFormat.EXCEL);
    }

    @Override
    public void write(List<ConfigurationSection> sections) {
        printHeaders();
        sections.forEach(this::doWriteSection);
    }

    private void printHeaders() {
        try {
            csvPrinter.printRecord(HEADERS);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void doWriteSection(ConfigurationSection section) {
        section.entries().forEach(this::writeField);
        section.sections().forEach(this::doWriteSection);
    }


    private void writeField(ConfigurationEntry f) {
        try {
            this.csvPrinter.printRecord(f.configName(), f.type(), BooleanUtils.toStringYesNo(f.required()), f.defaultValue(), f.documentation());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws IOException {
        csvPrinter.close();
    }

    @Override
    public void flush() throws IOException {
        csvPrinter.flush();
    }
}
