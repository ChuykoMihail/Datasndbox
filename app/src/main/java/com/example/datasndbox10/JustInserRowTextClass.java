package com.example.datasndbox10;

public class JustInserRowTextClass {
    String dataForInsert;

    JustInserRowTextClass(String someText){
        this.dataForInsert = someText;
    }

    public void setDataForInsert(String someText){
        this.dataForInsert = someText;
    }
    public String getDataForInsert(){
        return this.dataForInsert;
    }
}
