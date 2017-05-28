package com.example.hassan.technicaltestrandombeers;

import java.util.Map;

/**
 * Created by hassan on 23/05/2017.
 */

public class Beer {

        private String status;
        private BeerData data;
        private String message;

        public String getStatus() {
            return status;
        }

        public String getBeerName(){
            return data.getName();
        }

        public String getDescription(){
            return data.getDescription();
        }

        public String getLabelFromKey(String key){
            return data.getLabelFromKey(key);
        }

        public String getMessage() {
            return message;
        }

        //creating objects in this manner to keep gson happy and since the objects are so small I see no reason to have separate
        private class BeerData{
            private Style style;
            private Map<String,String> labels;

            public String getName() {
                return style.getName();
            }

            public String getDescription(){
                return style.getDescription();
            }


            public String getLabelFromKey(String key){
                return labels.get(key);
            }



            private class Style {

                public String description;
                private String name;

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public String getName() {
                    return name;
                }

            }
        }

}
