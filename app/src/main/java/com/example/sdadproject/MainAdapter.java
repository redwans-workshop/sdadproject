package com.example.sdadproject;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder>{

    private List<MainData> dataList;
    private Activity context;
    private RoomDB database;


    public MainAdapter(Activity context, List<MainData> datalist){

        this.context = context;
        this.dataList = dataList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //view int
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_main,parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//int main data
        MainData data = dataList.get(position);
        database = RoomDB.getInstance(context);
        holder.textView.setText(data.getText());
        holder.btEdit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //int main data
                MainData d = dataList.get(holder.getAdapterPosition());
                //id fetch
                int sID= d.getID();
                // get text
                String sText = d.getText();
                //create dialog
                Dialog dialog = new Dialog(context);
                //set content view
                dialog.setContentView(R.layout.dialog_update);

                //int width
                int width = WindowManager.LayoutParams.MATCH_PARENT;
                //int hi8
                int height = WindowManager.LayoutParams.WRAP_CONTENT;
                //set lay
                dialog.getWindow().setLayout(width, height);
                //show dialog
                dialog.show();
                //int n assign var
                EditText editText = dialog.findViewById(R.id.edit_text);
                Button btUpdate = dialog.findViewById(R.id.bt_update);
                //set text
                editText.setText(sText);
                btUpdate.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        //Dismiss D
                        dialog.dismiss();
                        String uText = editText.getText().toString().trim();
                        //text update in db
                        database.mainDao().update(sID,uText);
                        //notify update
                        dataList.clear();
                        dataList.addAll(database.mainDao().getAll());
                        notifyDataSetChanged();
                    }
                });
            }
        });
        holder.btDelete.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //int main data
                MainData d = dataList.get(holder.getAdapterPosition());
                //delete text from db
                database.mainDao().delete(d);
                //notify when data deleteded
                int position = holder.getAdapterPosition();
                dataList.remove(position);
                notifyItemChanged(position);
                notifyItemRangeChanged(position,dataList.size());

            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //int var

        TextView textView;
        ImageView btEdit,btDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.text_view);
            btEdit = itemView.findViewById(R.id.bt_edit);
            btDelete = itemView.findViewById(R.id.bt_delete);
        }
    }
}
