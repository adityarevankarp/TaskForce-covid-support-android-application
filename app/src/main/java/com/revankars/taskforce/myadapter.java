package com.revankars.taskforce;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class myadapter extends FirebaseRecyclerAdapter<model,myadapter.myviewholder>

{

    public myadapter(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull model model) {

        holder.Address.setText(model.getAddress());
        holder.Age.setText(model.getAge());
        holder.AlsoHelp.setText(model.getAlsoHelp());
        holder.Attender.setText(model.getAttender());
        holder.BUnum6digi.setText(model.getBUnum6digi());
        holder.BloodGroup.setText(model.getBloodGroup());
        holder.Dependents.setText(model.getDependents());
        holder.Gender.setText(model.getGender());
        holder.HelpOn.setText(model.getHelpOn());
        holder.Name.setText(model.getName());
        holder.Phone.setText(model.getPhone());
        holder.PlaceOfStay.setText(model.getPlaceOfStay());
        holder.SRFID13Nos.setText(model.getSRFID13Nos());
        holder.Surname.setText(model.getSurname());
        holder.Zone.setText(model.getZone());
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowxml,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder{


        TextView Address,Age,AlsoHelp,Attender,BUnum6digi,BloodGroup,Dependents,Gender,HelpOn,Name,Phone,PlaceOfStay,SRFID13Nos,Surname,Zone;


        public myviewholder(@NonNull View itemView) {

            super(itemView);

            Address = (TextView)itemView.findViewById(R.id.addresstext);
            Age = (TextView)itemView.findViewById(R.id.agetext);
            AlsoHelp = (TextView)itemView.findViewById(R.id.AlsoHelpTextText) ;
            Attender = (TextView)itemView.findViewById(R.id.attendertext);
            BUnum6digi = (TextView)itemView.findViewById(R.id.BUtext);
            BloodGroup = (TextView)itemView.findViewById(R.id.bloodtext);
            Dependents = (TextView)itemView.findViewById(R.id.dependentstext);
            Gender = (TextView)itemView.findViewById(R.id.gendertext);
            HelpOn = (TextView)itemView.findViewById(R.id.helptext);
            Name = (TextView)itemView.findViewById(R.id.nametext);
            Phone = (TextView)itemView.findViewById(R.id.phonetext);
            PlaceOfStay = (TextView)itemView.findViewById(R.id.placetext);
            SRFID13Nos = (TextView)itemView.findViewById(R.id.SRFtext);
            Surname = (TextView)itemView.findViewById(R.id.surnametext);
            Zone = (TextView)itemView.findViewById(R.id.zonestext);

        }

    }
}
