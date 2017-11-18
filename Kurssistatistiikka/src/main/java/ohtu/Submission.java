package ohtu;

import java.util.List;

public class Submission {
    private int week;
    private double hours ;
    private List<Integer> exercises ; 
    
    public Submission() {
        
    }


    public void setWeek(int week) {
        this.week = week;
    }

    public int getWeek() {
        return week;
    }
    
    public double getHours() {
        return hours;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("viikko " + this.week);
        sb.append(": tehtyjä tehtäviä yhteensä: " + this.exercises.size());
        sb.append(", aikaa kului " + this.hours);
        sb.append(" tuntia, tehdyt tehtävät: ");
        for (int i = 0 ; i < this.exercises.size(); i ++) {
            sb.append(this.exercises.get(i) + " ");
        }
       
        return sb.toString() ;
    }

    public List getExercises() {
        return this.exercises ;
    }
    
}