package ru.stqa.pft.mantis.model;

public class MailMessage {

    public String to;
    public String text;

    public MailMessage (String to, String text) {
        this.to = to;
        this.text = text;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
