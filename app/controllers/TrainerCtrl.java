package controllers;
import java.util.List;

import models.Assessment;
import models.Trainer;
import models.Member;
import play.Logger;
import play.mvc.Controller;

public class TrainerCtrl extends Controller {

    public static void index(Long id){
        Trainer trainer = Accounts.getLoggedInTrainer();
        List<Member> members = Member.findAll();
        List<Assessment> assessments = Assessment.findAll();
        Logger.info("Trainer id = " + id);
        render("trainer.html", trainer, members, assessments);
    }

    public static void trainerMember(Long id){
        Member member = Member.findById(id);
        Trainer trainer = Accounts.getLoggedInTrainer();
        List<Assessment> assessments = Assessment.findAll();
        Logger.info("Trainer id = " + id);
        render("trainermember.html", trainer, member, assessments);
    }

    public static void writeComment(Long id){
        Trainer trainer = Accounts.getLoggedInTrainer();
        List<Member> members = Member.findAll();
        List<Assessment> assessments = Assessment.findAll();
        Member member = Member.findById(id);
        Assessment assessment = Assessment.findById(id);
        Logger.info("Assessment id= " + id);
        render("writecomment.html", member, assessment);
    }

    public static void addComment(Long id, String comment){
        Assessment assessment = Assessment.findById(id);
        Trainer trainer = Accounts.getLoggedInTrainer();
        assessment.setComment(comment);
        trainer.save();
        assessment.save();
        Logger.info("AddString Comment" + comment);
        redirect("/trainers/{id}");
    }

    public static void deleteMember (Long id)
    {
        Member member = Member.findById(id);
        Trainer trainer = Accounts.getLoggedInTrainer();
        Logger.info ("Removing" + member);
        trainer.save();
        member.delete();
        redirect("/trainers/{id}");
    }

}

