package com.kwave.android.testtest.domain;

/**
 * Created by kwave on 2017-07-07.
 */
public class UserInformation {
    public String currentFood;
    public String repeat;
    public String timeSprint;
    public String timeWalk;

    public UserInformation(){

    }
    public UserInformation(String currentFood, String repeat, String timeSprint, String timeWalk) {
        this.currentFood = currentFood;
        this.repeat = repeat;
        this.timeSprint = timeSprint;
        this.timeWalk = timeWalk;
    }
}
