package org.example.service.impl;

import org.example.service.ProcessingResultsPrinter;

public class ProcessingResultsPrinterImpl implements ProcessingResultsPrinter {
    @Override
    public void printResults(String resultString) {
        System.out.println(resultString);
    }
}
