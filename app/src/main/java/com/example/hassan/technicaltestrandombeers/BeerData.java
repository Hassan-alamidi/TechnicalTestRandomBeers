package com.example.hassan.technicaltestrandombeers;

import java.util.Map;

/**
 * Created by hassan on 25/05/2017.
 */

public class BeerData {
    private String name;
    private Style style;
    private Map<String,String> labels;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public Map<String, String> getLabels() {
        return labels;
    }

    public void setLabels(Map<String,String> labels) {
        this.labels = labels;
    }

    public String getLabelFromKey(String key){
        return labels.get(key);
    }

}
