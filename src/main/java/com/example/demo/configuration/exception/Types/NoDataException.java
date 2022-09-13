package com.example.demo.configuration.exception.Types;

public class NoDataException extends RuntimeException  {
    public NoDataException() {
        super("No data found");
    }
}
