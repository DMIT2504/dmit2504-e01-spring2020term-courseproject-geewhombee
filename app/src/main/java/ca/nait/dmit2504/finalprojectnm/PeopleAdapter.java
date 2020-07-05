package ca.nait.dmit2504.finalprojectnm;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.List;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.PersonViewHolder> {

    private List<People> mPeople;

    public PeopleAdapter(List<People> teamList) {
        mPeople = teamList;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater infalter = LayoutInflater.from(parent.getContext());
        final View itemView = infalter.inflate(R.layout.list_item_people, parent, false);
        PersonViewHolder teamsViewHolder = new PersonViewHolder(itemView);
//possible on click listener
        //        teamsViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        return teamsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        People currentTeam = mPeople.get(position);
        holder.personID.setText(currentTeam.getId()+ "");
        holder.personFullName.setText(currentTeam.getFullName());


    }

    @Override
    public int getItemCount() {
        return mPeople.size();
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        public TextView personID;
        public TextView personFullName;

        public PersonViewHolder(View itemView) {
            super(itemView);

            personID = itemView.findViewById(R.id.list_item_people_id);
            personFullName = itemView.findViewById(R.id.list_item_people_fullname);
        }
    }


}
