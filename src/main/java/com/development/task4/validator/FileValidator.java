package com.development.task4.validator;

public class FileValidator {
    private static final FileValidator instance = new FileValidator();
    
    private FileValidator(){
    }
    
    public static FileValidator getInstance(){
        return instance;
    }

    public boolean checkFile(String filename){
        return getClass().getClassLoader().getResource(filename) != null;
    }
}
