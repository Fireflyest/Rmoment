package com.fireflyest.rmoment.fragment.matter;

public class MatterItem {

    private String type;
    private String title;
    private String content;
    private long moment;
    private long time;

    public MatterItem(String type, String title, String content, long moment, long time) {
        this.type = type;
        this.title = title;
        this.content = content;
        this.moment = moment;
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
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

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
