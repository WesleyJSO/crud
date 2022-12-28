package br.com.crud.controller;

import br.com.crud.UseCase.EmployeeUseCases;
import br.com.crud.UseCase.ProjectUseCases;
import br.com.crud.entity.Project;
import br.com.crud.entity.Status;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("projects")
public record ProjectController(ProjectUseCases projectUseCases,
                                EmployeeUseCases employeeUseCases) {

  public ModelAndView getAllProjects() {
    return this.getAllProjects(new ModelAndView());
  }

  @GetMapping
  public ModelAndView getAllProjects(ModelAndView mv) {

    mv.setViewName("projects");

    var result = projectUseCases.findAll();
    if (result.isError()) {
      mv.addObject("message", result.getMessage());
    }

    mv.addObject("projects", result.getResults("projects"));
    return mv;
  }

  @GetMapping("new")
  public ModelAndView newProject() {
    ModelAndView mv = new ModelAndView();

    addEmployees(mv);

    mv.addObject("statusList", Status.values());
    mv.addObject("project", new Project());
    mv.setViewName("project");
    return mv;
  }


  @GetMapping("{id}")
  public ModelAndView editProject(@PathVariable Integer id) {

    ModelAndView mv = new ModelAndView();
    mv.setViewName("project");
    addEmployees(mv);
    mv.addObject("statusList", Status.values());

    var result = projectUseCases.findById(id);
    if (result.isError()) {
      mv.addObject("message", result.getMessage());
    }

    mv.addObject("project", result.getResult("project"));
    return mv;
  }

  @PostMapping
  public ModelAndView saveProject(@ModelAttribute Project project) {

    ModelAndView mv = new ModelAndView();

    var result = projectUseCases.save(project);
    if (result.isError()) {
      mv.addObject("message", result.getMessage());
      mv.setViewName("project");
      addEmployees(mv);
      return mv;
    }

    return this.getAllProjects();
  }

  @PostMapping("{id}") // can't use delete in html forms, that's why I'm using a post instead of delete
  public ModelAndView deleteProject(@PathVariable Integer id) {

    ModelAndView mv = new ModelAndView();
    var result = projectUseCases.delete(id);

    mv.addObject("message", result.getMessage());
    return this.getAllProjects(mv);
  }

  private void addEmployees(ModelAndView mv) {
    var result = employeeUseCases.findByRole("funcionário");
    if (result.isSuccess()) {
      mv.addObject("employees", result.getResults("employees"));
    }
  }
}
