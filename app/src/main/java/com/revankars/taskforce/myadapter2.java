package com.revankars.taskforce;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class myadapter2 extends FirebaseRecyclerAdapter<communitymodel,myadapter2.myviewholder2> {



    public myadapter2(@NonNull FirebaseRecyclerOptions<communitymodel> options2) {
        super(options2);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder2 holder, int position, @NonNull communitymodel model) {
        holder.CommunityName.setText(model.getCommunityName());

        holder.CommunityName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity)v.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.wrapper,new PasswordEnterFragment(model.getCommunityName(),model.getPassword())).addToBackStack(null).commit();

                if(model.getCommunityName().equals("Public Community")){

                }
            }
        });




    }

    @NonNull
    @Override
    public myviewholder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowcommunityname,parent,false);
        return new myviewholder2(view);
    }

    class myviewholder2 extends RecyclerView.ViewHolder{
        TextView CommunityName,Password;
        public myviewholder2(@NonNull View itemView) {
            super(itemView);


            CommunityName = (TextView)itemView.findViewById(R.id.Communitynametextview);

        }
    }
}
