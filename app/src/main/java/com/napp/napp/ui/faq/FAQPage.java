package com.napp.napp.ui.faq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import com.napp.napp.R;
import com.napp.napp.ui.faq.FAQ;
import java.util.ArrayList;
import java.util.List;

public class FAQPage extends AppCompatActivity {

    RecyclerView recyclerView;

    List<FAQ> faqList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqpage);
        recyclerView = findViewById(R.id.recyclerView);
        initData();
        initRecyclerView();
    }

    private void initRecyclerView() {
        FaqExpand faqExpand = new FaqExpand(faqList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(faqExpand);
    }

    private void initData() {
        faqList = new ArrayList<>();
        faqList.add(new FAQ("How does the booking application work?", "When you first install and sign up to nApp, your location is recorded and all you need to do is follow the steps of the booking process"));
        faqList.add(new FAQ("Can I change my booking once I submit the form?", "Yes. However, you are only able to change your booking information 24hours before the pick-up time."));
        faqList.add(new FAQ("What if I move villages and am residing in a new location?", "You can change your location from the home page."));
        faqList.add(new FAQ("How do I contact the operator?", "Click on the ‘contact us’ page where you can send a text message to the operator"));
        faqList.add(new FAQ("Where can I see my previous bookings?", "Please visit the ‘Booking History’ page found on the application"));
        faqList.add(new FAQ("What if I want to add special instructions on washing the nappy?", "There is an area where you can add special instructions for the operator to look at and wash"));
        faqList.add(new FAQ("I don’t where to start when booking a time for nappy washing", "Please visit the 'How To' page here for a clear step by step instruction on how to book on the app"));
    }


}