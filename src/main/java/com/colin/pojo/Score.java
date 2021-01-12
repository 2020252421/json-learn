package com.colin.pojo;

import java.io.Serializable;
import java.util.Map;

public class Score implements Serializable {
    private Map<String, Object> score;


    public Map<String, Object> getScore() {
        return score;
    }

    public void setScore(Map<String, Object> score) {
        this.score = score;
    }


}
