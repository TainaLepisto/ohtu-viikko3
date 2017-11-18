/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu;

import java.util.List;

/**
 *
 * @author tainalep
 */
public class Kurssi {

    private String _id;
    private String name;
    private String term;
    private String url;
    private int week;

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getV() {
        return _v;
    }

    public void setV(int _v) {
        this._v = _v;
    }

    public List<Integer> getExercises() {
        return exercises;
    }

    public void setExercises(List<Integer> exercises) {
        this.exercises = exercises;
    }
    private boolean enabled;
    private int _v;
    private List<Integer> exercises;

}
