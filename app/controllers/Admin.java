package controllers;

import play.Logger;
import play.mvc.Controller;
import models.Assessment;
import java.util.List;

public class Admin extends Controller
{

  public static void index()
  {
    Logger.info("Rendering Admin");
    List<Assessment> assessments = Assessment.findAll();
    render("admin.html", assessments);
  }

}
