package com.se14.controller;

import com.se14.domain.*;
import com.se14.dto.IssueDTO;
import com.se14.dto.ProjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import jakarta.servlet.http.HttpSession;

import com.se14.service.ProjectService;
import com.se14.service.IssueService;
import com.se14.service.UserService;


@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    private final ProjectService projectService;
    private final IssueService issueService;
    private final UserService userService;

    @Autowired
    public ProjectController(ProjectService projectService, IssueService issueService, UserService userService) {
        this.projectService = projectService;
        this.issueService = issueService;
        this.userService = userService;
    }

    @GetMapping
    public List<ProjectDTO> projectList(HttpSession session) {
        User user = (User) session.getAttribute("USER");
        List<Project> projects = projectService.findProjectByUser(user);

        return projects.stream().map(ProjectDTO::new).toList();

    }
    //ProjectService쪽 수정되었을 때 추가 작업 필요 => ProjectService에 findProjectByUser()추가됨, 그에 따라 수정 했음

    @PostMapping
    public ResponseEntity<Void> createProject(@RequestBody Map<String, String> params, HttpSession session) {

        User creator = (User) session.getAttribute("USER");
        String title = params.get("title");
        String description = params.get("description");

        Project newProject = projectService.createProject(creator, title, description);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{projectId}/issues")
    public List<IssueDTO> issueList(@PathVariable("projectId") int projectId) {
        Project project = projectService.findProjectById(projectId);
        List<Issue> issues = issueService.searchIssues(project,null);

        return issues.stream().map(IssueDTO::new).toList();
    }

    @PostMapping("/{projectId}/issues")
    public ResponseEntity<Void> createIssue(@PathVariable("projectId") int projectId, @RequestBody Map<String, String> params, HttpSession session) {

        Project currentProject = projectService.findProjectById(projectId);
        User user = (User) session.getAttribute("USER");

        String title = params.get("title");
        String description = params.get("description");
        IssuePriority priority = IssuePriority.valueOf(params.get("priority").toUpperCase());

        /*Issue newIssue = */issueService.reportIssue(currentProject, user, title, description, priority);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{projectId}/roles")
    public List<UserRole> roleList(@PathVariable("projectId") int projectId, HttpSession session) {
        Project project = projectService.findProjectById(projectId);
        User user = (User) session.getAttribute("USER");

        return project.getMembers().get(user);
    }

//

//

//
//    @GetMapping("/{projectId}/users")
//    public List<User> userList(@PathVariable("projectId") int projectId, @RequestBody Map<String, String> params) {
//        Project currentProject = projectService.findProjectById(projectId);
//        UserRole role = (UserRole)params.get("userRole");
//
//        List<User> usersInProject = projectService.listUser(currentProject, role);
//        //프론트 엔드 쪽 수정 필요함
//
//        return usersInProject;
//        //프로젝트에 참가한 유저들 반환
//    }
//
//    @GetMapping("/{projectId}/users/join")
//    public List<User> userJoinList(@PathVariable("projectId") int projectId) {
//        Project currentProject = projectService.findProjectById(projectId);
//
//        List<User> allUsers = userService.listAllUser();
//        List<User> notJoinedUsers = new ArrayList<>();
//
//        for(User user : allUsers){
//            if(!projectService.hasUser(currentProject, user))
//                notJoinedUsers.add(user);
//        }
//
//        return notJoinedUsers;
//        //프로젝트에 참가해 있지 않은 유저들 반환
//    }
//
//    @PostMapping("/{projectId}/users")
//    public ResponseEntity<Void> addUser(@PathVariable("projectId") int projectId, @RequestBody Map<String, String> params) {
//
//        Project currentProject = projectService.findProjectById(projectId);
//        List<Issue> issues = issueService.searchIssues(currentProject, null);
//        Issue detailedIssue;
//        for (Issue issue : issues) {
//            if (issue.getTitle().equals(issueTitle)) {
//                detailedIssue = issue;
//                break;
//            }
//        }
//        User user = userService.findByUsername(params.get("username"));
//        UserRole role = UserRole.valueOf(params.get("role"));
//
//        //void addMemberToProject(Project project,User user, UserRole role);
//        projectService.addMemberToProject(currentProject, user, role);
//        //Vue쪽에서 role 처리 해야 함
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

}