package controllers;

import models.Member;
import models.Assessment;
import play.Logger;
import play.mvc.Controller;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MemberCtrl extends Controller {

    public static void index(Long id){
        Member member = Accounts.getLoggedInMember();
        Logger.info("Member id = " + id);
        render("member.html", member);
    }

    public static void addAssessment(Long id, float weight, float chest, float thigh, float upperArm, float waist, float hips, String comment){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDateTime = now.format(formatter);
        Assessment assessment = new Assessment(weight, chest, thigh, upperArm, waist, hips, comment, formatDateTime);
        Member member = Member.findById(id);
        member.assessments.add(assessment);
        member.save();
        Logger.info("Addfloat Assessment" + weight, chest, thigh, upperArm, waist, hips, comment, formatDateTime);
        redirect("/members/"+id);
    }

    public static void settings(Long id){
        Member member = Accounts.getLoggedInMember();
        Logger.info("Member id = " + id);
        render("settings.html", member);
    }

    public static void updateMember(Long id, String email, String name, String address, String password,
                                 String gender, float height, float startWeight){
        Member member = Accounts.getLoggedInMember();
        if(!email.equals("")) {
            member.setEmail(email);
        }
        if(!name.equals("")) {
            member.setName(name);
        }
        if(!address.equals("")) {
            member.setAddress(address);
        }
        if(!password.equals("")){
            member.setPassword(password);
        }
        if(!gender.equals("")) {
            member.setGender(gender);
        }
        if(height !=0.0) {
            member.setHeight(height);
        }
        if(startWeight !=0.0) {
            member.setStartWeight(startWeight);
        }

        member.save();
        Logger.info("Member's Details updated" +id + email, name, address, password, gender, height, startWeight);
        //redirect("/dashboard/");
        redirect("/members/"+id);
        //Member member = Member.findById(id);
    }

    public static void deleteAssessment (Long id)
    {
        Member member = Accounts.getLoggedInMember();
        Assessment assessment = Assessment.findById(id);
        Logger.info ("Removing" + assessment);
        member.assessments.remove(assessment);
        member.save();
        assessment.delete();
        render("member.html", member);
    }

}

