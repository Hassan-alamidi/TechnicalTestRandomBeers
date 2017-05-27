package com.example.hassan.technicaltestrandombeers;

import android.util.Base64;

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

        public void setStatus(String status) {
            this.status = status;
        }

        public BeerData getData() {
            return data;
        }

        public void setData(BeerData data) {
            this.data = data;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }


}
