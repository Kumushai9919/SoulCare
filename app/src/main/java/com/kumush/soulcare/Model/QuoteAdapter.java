package com.kumush.soulcare.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kumush.soulcare.R;

import java.util.List;

public class QuoteAdapter extends RecyclerView.Adapter<QuoteAdapter.QuoteViewHolder> {

    private Context context;
    private List<Quote> quoteList;
    //Quote для представления данных для каждой цитаты

    public QuoteAdapter(Context context, List<Quote> quoteList){
        this.context = context;
        this.quoteList = quoteList;
    }

    @NonNull
    @Override
    public QuoteAdapter.QuoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_quote, parent, false);

        return new QuoteViewHolder(view);  //QuoteViewHolder отвечает за хранение ссылок на представления в каждой карточке цитаты..
    }

    @Override
    public void onBindViewHolder(@NonNull QuoteAdapter.QuoteViewHolder holder, int position) {
        Quote quote = quoteList.get(position);

        holder.textQuote.setText(quote.getText());
        holder.author.setText(quote.getAuthor());

    }

    @Override
    public int getItemCount() {
        return quoteList.size();
    }

    public class QuoteViewHolder extends RecyclerView.ViewHolder{

        public TextView textQuote;
        public TextView author;


        public QuoteViewHolder(@NonNull View itemView) {
            super(itemView);
            textQuote = itemView.findViewById(R.id.textQuote);
            author = itemView.findViewById(R.id.author);
        }
    }
}
