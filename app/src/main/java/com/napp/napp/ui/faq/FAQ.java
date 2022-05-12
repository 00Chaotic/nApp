package com.napp.napp.ui.faq;

public class FAQ {

    private String question;
    private String answer;
    private boolean expanded;

    public FAQ(String question, String answer) {
        this.question = question;
        this.answer = answer;
        this.expanded = false;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    @Override
    public String toString() {
        return "FAQ{" +
                "question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", expanded=" + expanded +
                '}';
    }
}