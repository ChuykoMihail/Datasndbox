package com.example.datasndbox10;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringToTableField {
    public void convertToField(String input, ArrayList<TableField> table) {


        //хранит разные колонны в разных столбцах?


        int sum = 0;
        ArrayList<String> collumnsStrings = new ArrayList<String>();

        ArrayList<String> fieldsINT = new ArrayList<String>();
        ArrayList<String> fieldsTEXT = new ArrayList<String>();
        ArrayList<String> fieldsPK = new ArrayList<String>();
        ArrayList<String> fieldsFK = new ArrayList<String>();
        ArrayList<String> fieldsNN = new ArrayList<String>();
        String buffer;
        int helpIndex = 0;
        int helpIndexEnd = 0;
        int amountInt = 0;
        int amountText = 0;
        int amountFK = 0;
        int amountPK = 0;
        int amountNN = 0;
        //Ищем text

        Pattern pattern = Pattern.compile("\\(.+\\)");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            String columns = input.subSequence(matcher.start() + 1, matcher.end()).toString();
            pattern = Pattern.compile("(.+?,|.+\\))");
            matcher = pattern.matcher(columns);
            while (matcher.find()) {
                collumnsStrings.add(columns.substring(matcher.start(), matcher.end() - 1));
            }
            for (String i : collumnsStrings) {
                TableField tempField = new TableField("", "", false, false, false);
                pattern = Pattern.compile(".+?\\s|.+\\z");
                matcher = pattern.matcher(i);
                matcher.find();
                tempField.setFlagResource(i.substring(matcher.start(), matcher.end() - 1));
                matcher.find();
                if (i.charAt(matcher.end() - 1) != ' ') {
                    tempField.setType(i.substring(matcher.start(), matcher.end()));
                } else {
                    tempField.setType(i.substring(matcher.start(), matcher.end() - 1));
                    matcher.find();
                    //Если сначала указан primary key
                    if (i.charAt(matcher.start()) == 'P') {
                        tempField.setCheckBox2Resource(true);
                    } else if (i.charAt(matcher.start()) == 'N') {  //Если сначала Not null
                        tempField.setCheckBox1Resource(true);
                    } else if (i.charAt(matcher.start()) == 'F') { //Если сначала Foreign
                        tempField.setCheckBox3Resource(true);
                        // здесь нужно будет доработать
                    }
                }
                table.add(tempField);
            }
        }
    }
}
