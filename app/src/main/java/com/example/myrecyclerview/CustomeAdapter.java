package com.example.myrecyclerview;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomeAdapter extends RecyclerView.Adapter<CustomeAdapter.MyViewHolder> {

    private ArrayList<DataModel> dataSet;

    public CustomeAdapter(ArrayList<DataModel> dataSet) {
        this.dataSet = dataSet;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textViewName;
        TextView textViewDescription;
        ImageView imageView;
        DataModel dataModel;
        Context context;

        public MyViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            this.textViewName = itemView.findViewById(R.id.textView);
            this.textViewDescription = itemView.findViewById(R.id.textView2);
            this.imageView = itemView.findViewById(R.id.imageView);
            this.context = context;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            showItemDetails(this.dataModel);
        }

        private void showItemDetails(DataModel dataModel) {
            if (dataModel != null) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
                LayoutInflater inflater = LayoutInflater.from(context);
                View dialogView = inflater.inflate(R.layout.popup_window, null);
                dialogBuilder.setView(dialogView);

                ImageView imageView = dialogView.findViewById(R.id.imageViewPopup);
                TextView textViewName = dialogView.findViewById(R.id.textViewNamePopup);
                TextView textViewDescription = dialogView.findViewById(R.id.textViewDescriptionPopup);

                imageView.setImageResource(dataModel.getImage());
                textViewName.setText(dataModel.getName());
                textViewDescription.setText(dataModel.getDescription());

                dialogBuilder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog dialog = dialogBuilder.create();
                dialog.show();
            } else {
                // Handle null dataModel
                Toast.makeText(context, "DataModel is null", Toast.LENGTH_SHORT).show();
            }
        }
    }
    
    @NonNull
    @Override
    public CustomeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view , parent.getContext());
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomeAdapter.MyViewHolder holder, int position) {
        DataModel currentDataModel = dataSet.get(position);
        holder.textViewName.setText(dataSet.get(position).getName());
        holder.textViewDescription.setText(dataSet.get(position).getDescription());
        holder.imageView.setImageResource(dataSet.get(position).getImage());
        holder.dataModel = currentDataModel;
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

}
