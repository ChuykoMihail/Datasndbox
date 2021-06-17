package com.example.datasndbox10;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringToTableField {
    public void convertToField(String input, ArrayList<TableField> table) {


        //хранит разные колонны в разных столбцах?


        int sum = 0;
        ArrayList<String> collumnsStrings = new ArrayList<String>();


        //Ищем text

        Pattern pattern = Pattern.compile("\\(.+\\)");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            String columns = input.subSequence(matcher.start() + 1, matcher.end()).toString();

            pattern = Pattern.compile("(.+?,|.+\\))");
            matcher = pattern.matcher(columns);

            while (matcher.find()) {
                if(columns.charAt(matcher.start())  == ' ') collumnsStrings.add(columns.substring(matcher.start()+1, matcher.end() - 1));
                else collumnsStrings.add(columns.substring(matcher.start(), matcher.end() - 1));
            }
            String delete = collumnsStrings.get(collumnsStrings.size()-1).substring(0,11);
            if(delete.equals("FOREIGN KEY")){
                collumnsStrings.remove(collumnsStrings.size()-1);
            }
            for (String i : collumnsStrings) {
                //если constraint - брейкаем
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
