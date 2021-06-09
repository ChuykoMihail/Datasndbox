package com.example.datasndbox10;


import android.widget.Spinner;
import android.widget.TextView;

public class TableField {


    private String flagResource; // ресурс флага
    private String type;
    private boolean checkBox1;
    private boolean checkBox2;
    private boolean checkBox3;


    public TableField(String flag, String type,boolean check1,boolean check2,boolean check3){

        this.type = type;
        this.flagResource=flag;
        this.checkBox1 = check1;
        this.checkBox1 = check2;
        this.checkBox1 = check3;
    }



    public String getFlagResource() {
        return this.flagResource;
    }

    public void setFlagResource(String flagResource) {
        this.flagResource = flagResource;
    }

    public String getType(){
        return type;
    }
    public void setType(String type){
        this.type = type;
    }

    public boolean getCheckBox1Resource() {
        return this.checkBox1;
    }

    public void setCheckBox1Resource(boolean flagResource1) {
        this.checkBox1 = flagResource1;
    }
    public boolean getCheckBox2Resource() {
        return this.checkBox2;
    }

    public void setCheckBox2Resource(boolean flagResource2) {
        this.checkBox2 = flagResource2;
    }
    public boolean getCheckBox3Resource() {
        return this.checkBox3;
    }

    public void setCheckBox3Resource(boolean flagResource3) {
        this.checkBox3 = flagResource3;
    }
}
