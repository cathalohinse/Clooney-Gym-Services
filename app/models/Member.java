package models;

import play.db.jpa.Model;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member extends Model {

    private String email;
    private String name;
    private String address;
    private String password;
    private String gender;
    private float height;
    private float startWeight;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Assessment> assessments = new ArrayList<Assessment>();

    ///////////////////
    // Constructors  //
    ///////////////////
    //I put in the same validation here as specified for the corresponding parameters of the programming assignment.
    public Member(String email, String name, String address, String password,
                  String gender, float height, float startWeight) {
        this.email = email;
        this.address = address;
        this.password = password;

        if(name.length()<30){
            this.name = name;
        }
        else {
            this.name = name.trim().substring(0,30);
        }

        if(gender.equals("M") || gender.equals("F") || gender.equals("m") || gender.equals("f") ||
        gender.equals("Male") || gender.equals("Female") || gender.equals("male") || gender.equals("female")){
            this.gender = gender;
        }
        else {
            this.gender = "Unspecified";
        }

        if(height>=1.0&&height<=3.0){
            this.height = height;
        }
        else if(height<1.0){
            this.height = (float) 1.0;
        }
        else if(height>3.0){
            this.height = (float) 3.0;
        }

        if(startWeight>=35.0&&startWeight<=250.0) {
            this.startWeight = startWeight;
        }
        else if(startWeight<35.0){
            this.startWeight = (float) 35.0;
        }
        else if(startWeight>250.0){
            this.startWeight = (float) 250.0;
        }

    }

    public Member(){}

    //////////////
    // methods  //
    //////////////
    public static Member findByEmail(String email)
    {
        return find("email", email).first();
    }

    public boolean checkPassword(String password)
    {
        return this.password.equals(password);
    }

    private double BMICalculation(){
        if(assessments.size()==0) {
            return startWeight / Math.pow(height, 2);
        }
        else{
            return assessments.get(assessments.size()-1).getWeight() / Math.pow(height, 2);
        }
    }

    public String determineBMICategory(){
        String bmiCategory = "";
        double bmiValue = BMICalculation();
        if(bmiValue<16){
            bmiCategory = "SEVERELY UNDERWEIGHT";
        }
        else if(bmiValue>=16 && bmiValue<18.5){
            bmiCategory = "UNDERWEIGHT";
        }
        else if(bmiValue>=18.5 && bmiValue<25){
            bmiCategory = "NORMAL";
        }
        else if(bmiValue>=25 && bmiValue<30){
            bmiCategory = "OVERWEIGHT";
        }
        else if(bmiValue>=30 && bmiValue<35){
            bmiCategory = "MODERATELY OBESE";
        }
        else if(bmiValue>=35){
            bmiCategory = "SEVERELY OBESE";
        }
        return bmiCategory;
    }

    public String isIdealBodyWeight(){
        if(assessments.size()==0){
            return "No Assessments have been made";
        }
        else {
            Assessment assessment = assessments.get(assessments.size() - 1);
            boolean isIdealBodyWeight;
            if (getGender().equals("M") || getGender().equals("m") || getGender().equals("Male") || getGender().equals("male")) {
                double idealWeight = 50.0 + (2.3 * ((getHeight() - 1.524) / 0.0254));
                if (getHeight() <= 1.524 && Math.abs(assessment.getWeight() - 50.0) <= 0.2) {
                    isIdealBodyWeight = true;
                } else if (getHeight() > 1.524 && Math.abs(assessment.getWeight() - idealWeight) <= 0.2) {
                    isIdealBodyWeight = true;
                } else {
                    isIdealBodyWeight = false;
                }
            }
            // I'm defaulting to the ideal body weight of a female, as per the programming assignment, if no valid gender type is specified.
            else {
                double idealWeight = 45.5 + (2.3 * ((getHeight() - 1.524) / 0.0254));
                if (getHeight() <= 1.524 && Math.abs(assessment.getWeight() - 45.5) <= 0.2) {
                    isIdealBodyWeight = true;
                } else if (getHeight() > 1.524 && Math.abs(assessment.getWeight() - idealWeight) <= 0.4) {
                    isIdealBodyWeight = true;
                } else {
                    isIdealBodyWeight = false;
                }
            }

            if (isIdealBodyWeight) {
                return "Ideal Body Weight";
            } else {
                return "Not at the Ideal Body Weight";
            }
        }
    }

    ///////////////////////
    // Getters & Setters //
    ///////////////////////
    public float getHeight() {
        if(height>=1.0&&height<=3.0){
            this.height = height;
        }
        else if(height<1.0){
            this.height = (float) 1.0;
        }
        else if(height>3.0){
            this.height = (float) 3.0;
        }
        return height;
    }

    public void setHeight(float height) {
        if(height>=1.0&&height<=3.0){
            this.height = height;
        }
    }

    public float getStartWeight() {
        if(startWeight>=35.0&&startWeight<=250.0) {
            this.startWeight = startWeight;
        }
        else if(startWeight<35.0){
            this.startWeight = (float) 35.0;
        }
        else if(startWeight>250.0){
            this.startWeight = (float) 250.0;
        }
        return startWeight;
    }

    public void setStartWeight(float startWeight) {
        if(startWeight>=35&&startWeight<=250) {
            this.startWeight = startWeight;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        if(name.length()<30){
            this.name = name;
        }
        else {
            this.name = name.trim().substring(0,30);
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        if(gender.equals("M") || gender.equals("F") || gender.equals("m") || gender.equals("f") ||
                gender.equals("Male") || gender.equals("Female") || gender.equals("male") || gender.equals("female")){
            this.gender = gender;
        }
        else {
            this.gender = "Unspecified";
        }
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<Assessment> getAssessments() {
        return assessments;
    }

    public void setAssessments(List<Assessment> assessments) {
        this.assessments = assessments;
    }

}
