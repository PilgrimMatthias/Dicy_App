package com.example.dicyapp;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class DiceAdapter extends RecyclerView.Adapter<DiceAdapter.ViewHolder> {
    private List<Dice> mDice;
    private DiceRoller mDiceRoller;

    public DiceAdapter(DiceRoller diceRoller, List<Dice> dice){
        mDiceRoller = diceRoller;
        mDice = dice;
    }

    @NonNull
    @Override
    public DiceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View diceView = inflater.inflate(R.layout.dice_list,parent,false);
        return new ViewHolder(diceView);
    }

    @Override
    public void onBindViewHolder(@NonNull DiceAdapter.ViewHolder holder, int position) {
        Dice dice = mDice.get(position);
        TextView nameOfDice = holder.diceName;
        TextView rollResult = holder.diceResult;


        nameOfDice.setText("K"+ dice.getNumOfLoop() + " result = ");
        rollResult.setText(""+ dice.rollDice());


    }

    @Override
    public int getItemCount() {
        return mDice.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView diceName;
        public TextView diceResult;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            diceName = (TextView) itemView.findViewById(R.id.diceName);
            diceResult = (TextView) itemView.findViewById(R.id.diceRollResult);
        }

    }
    public void setAdapterList(List<Dice> diceList){
        this.mDice = mDice;
        this.mDice.clear();
        this.mDice.addAll(diceList);
        notifyDataSetChanged();
    }
}
