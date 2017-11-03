package com.example.f1.a08_database;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.f1.a08_database.model.Student;
import io.realm.RealmResults;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private RealmResults<Student> listOfStudents;

    public static class ViewHolder extends  RecyclerView.ViewHolder {

        public TextView tvName;
        public TextView tvAge;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tvName = (TextView) itemView.findViewById(R.id.student_name);
            this.tvAge = (TextView) itemView.findViewById(R.id.student_age);
        }
    }

    // Class constructor
    public MyAdapter(RealmResults<Student> list) {
        this.listOfStudents = list;
    }

    // Creates a new view (invoke by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View listItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_of_list, parent, false);
        final ViewHolder viewHolder = new ViewHolder(listItemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvName.setText(listOfStudents.get(position).getName());
        holder.tvAge.setText(String.valueOf(listOfStudents.get(position).getAge()));
    }

    // Returns the size of the list
    @Override
    public int getItemCount() {
        return listOfStudents.size();
    }
}