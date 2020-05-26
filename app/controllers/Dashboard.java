package controllers;

import models.Trainer;
import models.Member;
import models.Assessment;
import play.Logger;
import play.mvc.Controller;
import java.util.List;

public class Dashboard extends Controller
{

  public static void index()
  {
    Logger.info("Rendering Admin");
    Trainer trainer = Accounts.getLoggedInTrainer();
    List<Member> members = Member.findAll();
    List<Assessment> assessments = Assessment.findAll();
    render("dashboard.html", trainer, members, assessments);
  }

  public static void addMember(String email, String name, String address, String password,
                               String gender, float height, float startWeight){
    Member member = new Member(email, name, address, password, gender, height, startWeight);
    member.save();
    Logger.info("Addfloat Assessment" + email, name, address, password, gender, height, startWeight);
    redirect("/dashboard/");
  }

  public static void addAssessment(Long id, float weight, float chest, float thigh, float upperArm, float waist, float hips, String comment){
    Member member = Accounts.getLoggedInMember();
    Assessment assessment = new Assessment(0, 0, 0, 0, 0, 0, "", "");
    member.assessments.add(assessment);
    member.save();
    Logger.info("Addfloat Assessment" + weight, chest, thigh, upperArm, waist, hips, comment);
    redirect("/dashboard/");
  }

}
