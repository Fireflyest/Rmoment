package com.fireflyest.rmoment.fragment.note;

public class NoteItem {

    private String title;
    private String content;
    private long moment;
    private int type;

    public NoteItem(String title, String content, long moment, int type) {
        this.title = title;
        this.content = content;
        this.moment = moment;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getMoment() {
        return moment;
    }

    public void setMoment(long moment) {
        this.moment = moment;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
