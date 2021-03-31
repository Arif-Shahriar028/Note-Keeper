package com.example.notekeeper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    ArrayList<String> listTitle;
    ArrayList<String> listDate;
    ArrayList<String> listDesc;
    Context context;

    private LayoutInflater inflater;

    CustomAdapter(Context context, ArrayList<String> listTitle, ArrayList<String> listDate, ArrayList<String> listDesc){
        this.context = context;
        this.listTitle = listTitle;
        this.listDate = listDate;
        this.listDesc = listDesc;
    }

    @Override
    public int getCount() {
        return listTitle.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_view, parent, false);
        }

        TextView titleTextView = convertView.findViewById(R.id.titleId);
        TextView dateTextView = convertView.findViewById(R.id.dateId);
        TextView descTextView = convertView.findViewById(R.id.descId);


        dateTextView.setText(listDate.get(position));
        String text1 = modifyText(listTitle.get(position));
        String text2 = modifyText(listDesc.get(position));
        titleTextView.setText(text1);
        descTextView.setText(text2);

        return convertView;
    }

    public String modifyText(String s){
        int len = s.length();
        String text;
        if(len>20) {
            text = s.substring(0,20)+"...";
        }
        else{
            return s;
        }
        return text;
    }
}
