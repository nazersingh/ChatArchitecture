package com.nazer.saini.chatarchitecture.pojomodels;

/**
 * Wrapper class -  to wrap ant response from webservice
 */
public class ResponsePojo {

    private int statusCode;
    private String email;
    private int totalCount;
    private int count;
    private String message;
    private ErrorPojo error;
    public Result result;
    public int unreadNotifications;
    Object data;

    public int getUnreadNotifications() {
        return unreadNotifications;
    }

    public Object getData() {
        return data;
    }

    public int getCount() {
        return count;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setData(Object data) {
        this.data = data;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Result getResult() {
        return result;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public void setStatusCode(int status) {
        this.statusCode = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String description) {
        this.message = description;
    }

    public ErrorPojo getError() {
        return error;
    }

    public void setError(ErrorPojo error) {
        this.error = error;
    }

    public class Result {

        private String username;
        private String image;
        private String email;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }


        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }


        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }


    }

}
