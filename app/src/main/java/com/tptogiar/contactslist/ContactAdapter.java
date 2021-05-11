package com.tptogiar.contactslist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.DataViewHolder> {
    private Context mContext;
    private ArrayList<ContactInfo> contactList;

    public ContactAdapter(Context mContext, ArrayList<ContactInfo> contactList) {
        this.mContext = mContext;
        this.contactList = contactList;
    }

    public void setContactList(ArrayList<ContactInfo> contactList) {
        this.contactList = contactList;
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item, null);
        view.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        DataViewHolder holder = new DataViewHolder(view);
        return holder;
    }

    /**
     * @Author: Tptogiar
     * @Description: 将数据与界面进行绑定
     * @Date: 2021/4/25-16:46
     */
    @Override
    public void onBindViewHolder(DataViewHolder holder, int position) {
        holder.item_name.setText(contactList.get(position).getName());
        holder.item_Phone.setText(contactList.get(position).getPhone());
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    /**
     * @Author: Tptogiar
     * @Description: 创建ViewHolder
     * @Date: 2021/4/25-16:47
     */
    public static class DataViewHolder extends RecyclerView.ViewHolder {
        TextView item_name;
        TextView item_Phone;
        public DataViewHolder(View itemView) {
            super(itemView);
            item_name = (TextView) itemView.findViewById(R.id.itemName);
            item_Phone = (TextView) itemView.findViewById(R.id.itemPhone);
        }
    }
}