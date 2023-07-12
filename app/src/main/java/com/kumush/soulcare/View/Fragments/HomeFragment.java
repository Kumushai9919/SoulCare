package com.kumush.soulcare.View.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kumush.soulcare.Model.Quote;
import com.kumush.soulcare.Model.QuoteAdapter;
import com.kumush.soulcare.R;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private FirebaseAuth firebaseAuth;

    private DatabaseReference quotesRef;

    private RecyclerView rvQuotes;

    private QuoteAdapter quoteAdapter;

    private List<Quote> quoteList = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        quotesRef = FirebaseDatabase.getInstance().getReference("Quotes");

        Quote quote = new Quote("it's okay not ot be okay", "me");
        Quote quote1 = new Quote("Movement inspires", "Ali");
        Quote quote2 = new Quote("Everything is for the best", "Kumush");
        String quoteKey = quotesRef.push().getKey();
        quotesRef.child(quoteKey).setValue(quote);
        quotesRef.child(quoteKey).setValue(quote1);
        quotesRef.child(quoteKey).setValue(quote2);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        firebaseAuth = FirebaseAuth.getInstance();

        rvQuotes = rootView.findViewById(R.id.rvQuotes);

        quoteAdapter = new QuoteAdapter(getContext(), quoteList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvQuotes.setLayoutManager(linearLayoutManager);

        rvQuotes.setAdapter(quoteAdapter);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Retrieve quotes from Firebase database
        retrieveQuotes();
    }

    private void retrieveQuotes() {


        //Creating a new reference to the Firebase database node that contains the quotes data && Retrieve the quotes data from the database using a ValueEventListener:
//        DatabaseReference quotesRef = FirebaseDatabase.getInstance().getReference("Quotes");
        quotesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                quoteList.clear();

                // Iterate through the dataSnapshot to retrieve each quote
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    String text = snapshot.child("text").getValue(String.class);
                    String author = snapshot.child("author").getValue(String.class);

                    // Create a new Quote object and add it to the list
                    Quote quote = new Quote(text, author);
                    quoteList.add(quote);
                }

                quoteAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}