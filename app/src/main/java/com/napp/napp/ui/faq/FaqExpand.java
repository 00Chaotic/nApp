package com.napp.napp.ui.faq;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.napp.napp.R;
import com.napp.napp.ui.faq.FAQ;

import java.util.List;

public class FaqExpand extends RecyclerView.Adapter<FaqExpand.FaqVH> {

    private static final String TAG = "FAQExpand";
    List<FAQ> faqList;

    public FaqExpand(List<FAQ> faqList) {
        this.faqList = faqList;
    }

    @NonNull
    @Override
    public FaqVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.faq_questions, parent, false);
        return new FaqVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FaqVH holder, int position) {

        FAQ faq = faqList.get(position);
        holder.questionTextView.setText(faq.getQuestion());
        holder.answerTextView.setText(faq.getAnswer());

        boolean isExpanded = faqList.get(position).isExpanded();
        holder.expandableLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);

    }

    @Override
    public int getItemCount() {
        return faqList.size();
    }

    class FaqVH extends RecyclerView.ViewHolder {

        private static final String TAG = "FaqVH";

        ConstraintLayout expandableLayout;
        TextView questionTextView, answerTextView;

        public FaqVH(@NonNull final View itemView) {
            super(itemView);
            questionTextView = itemView.findViewById(R.id.questionTextView);
            answerTextView = itemView.findViewById(R.id.answerTextView);
            expandableLayout = itemView.findViewById(R.id.expandableLayout);


            questionTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    FAQ faq = faqList.get(getAdapterPosition());
                    faq.setExpanded(!faq.isExpanded());
                    notifyItemChanged(getAdapterPosition());

                }
            });
        }
    }
}