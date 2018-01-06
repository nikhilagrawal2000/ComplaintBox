package com.example.pc.complainform;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Dell on 04-01-2018.
 */

public class ComplaintsAdapter extends RecyclerView.Adapter<ComplaintsAdapter.ViewHolder> {

    public ComplaintsAdapter(List<Complaints> complaintsList) {
        this.complaintsList = complaintsList;
    }

    public List<Complaints> complaintsList;

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.date2.setText(complaintsList.get(position).getDate());
        holder.time2.setText(complaintsList.get(position).getTime());
        holder.complaint2.setText(complaintsList.get(position).getComplaint());
    }

    @Override
    public int getItemCount() {
        return complaintsList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);
        return new ViewHolder(view);

    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public TextView date2;
        public TextView time2;
        public TextView complaint2;
        public ViewHolder(View itemView){

            super(itemView);
            mView = itemView;

            date2  = (TextView)mView.findViewById(R.id.Date2);
            time2 = (TextView)mView.findViewById(R.id.Time2);
            complaint2 = (TextView)mView.findViewById(R.id.Complaint2);

        }
    }
}
