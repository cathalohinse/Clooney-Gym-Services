package models;

import play.db.jpa.Model;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Assessment extends Model
{

    public float weight;
    private float chest;
    private float thigh;
    private float upperArm;
    private float waist;
    private float hips;
    private String comment;
    private String dateTime;
    public ArrayList<Assessment> assessments = new ArrayList<Assessment>();

    //////////////////
    // Constructor  //
    //////////////////
    public Assessment(float weight, float chest, float thigh, float upperArm, float waist, float hips, String comment, String dateTime)
    {
        this.weight = weight;
        this.chest = chest;
        this.thigh = thigh;
        this.upperArm = upperArm;
        this.waist = waist;
        this.hips = hips;
        this.comment = comment;
        this.dateTime = dateTime;
    }

    //////////////
    // methods  //
    //////////////
    public double BMICalculation(float height){
        return weight/Math.pow(height, 2);
    }

    public String trend(Member member, List<Assessment> assessments, Assessment assessment){
        String trend = "";
        if(assessments.indexOf(assessment)==0){
            if(assessments.get(assessments.indexOf(assessment)).getWeight()<member.getStartWeight()){
                trend = "Losing Weight";
            }
            else{
                trend = "Gaining Weight";
            }
        }
        else if(assessments.get(assessments.indexOf(assessment)).getWeight()<assessments.get(assessments.indexOf(assessment)-1).getWeight()){
            trend = "Losing Weight";
        }
        else{
            trend = "Gaining Weight";
        }
        return trend;
    }

    ////////////////////////
    // Getters & Setters  //
    ////////////////////////
    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getChest() {
        return chest;
    }

    public void setChest(float chest) {
        this.chest = chest;
    }

    public float getThigh() {
        return thigh;
    }

    public void setThigh(float thigh) {
        this.thigh = thigh;
    }

    public float getUpperArm() {
        return upperArm;
    }

    public void setUpperArm(float upperArm) {
        this.upperArm = upperArm;
    }

    public float getWaist() {
        return waist;
    }

    public void setWaist(float waist) {
        this.waist = waist;
    }

    public float getHips() {
        return hips;
    }

    public void setHips(float hips) {
        this.hips = hips;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public ArrayList<Assessment> getAssessments() {
        return assessments;
    }

    public void setAssessments(ArrayList<Assessment> assessments) {
        this.assessments = assessments;
    }

}
