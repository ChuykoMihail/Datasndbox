package com.example.datasndbox10;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


//нужно расширить не прото адаптер ресайклера. Нужен адаптер ресайклера, который содержит в себе внутренний класс нашего нового класса - т.е. ViewHolder

public class InsertRowRecyclerAdapter extends RecyclerView.Adapter<InsertRowRecyclerAdapter.ViewHolder> {



    LayoutInflater inflater;   //создаёт вью
    ArrayList<TableField> fieldsOfSelectedTable;//хранит информацию которую положим в создаваемые вью

    //передаём контекст, в котором будут создаваться вью(from(context)) и информацию, которой их заполнять
    InsertRowRecyclerAdapter(Context context,ArrayList<TableField> tableFields){
        this.fieldsOfSelectedTable = tableFields;
        this.inflater = LayoutInflater.from(context);
    }


    //это вызывается, когда создаётся очередной вьюхолдер => нужно инфлейтнуть(создать вью) и вернуть вью холдер, с принятым вью
    //т.е. создаём какое-то вью по шаблону, отдаём в конструктор вьюхолдера. Вьюхолдер находит виджеты в шаблоне и может их изменять.
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.insert_row_field_data,parent,false);
        return new ViewHolder(view);
    }



    //связываем вьюхолдер и элемент массива(ArrayList). Здесь есть инфлейтнутый нами холдер и позиция элемента в массиве
    //Здесь у нас есть вью холдер, созданный по шаблону и ориентирующийся в своих элементах. Передаём в эти элементы инфу из массива данных
    @Override
    public void onBindViewHolder(InsertRowRecyclerAdapter.ViewHolder holder, int position) {
        TableField field = fieldsOfSelectedTable.get(position);
//        holder.dataOfNewRow.setText(field.getType());
        holder.dataOfNewRow.setHint(field.getType());
    }

    @Override
    public int getItemCount() {
        return fieldsOfSelectedTable.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView dataOfNewRow;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dataOfNewRow = itemView.findViewById(R.id.insert_row_field_EditText);
        }
    }
}
