package kea.iabr.testingh2database.repository;

import kea.iabr.testingh2database.model.Project;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




    @Repository
    public class ProjectRepository {

        @Value("${spring.datasource.url}")
        private String db_url;

        @Value("${spring.datasource.username}")
        private String uid;

        @Value("${spring.datasource.password}")
        private String pwd;

        public List<Project> getProjects() {
            List<Project> projects = new ArrayList<>();
            Connection connection = ConnectionManager.getConnection(db_url, uid, pwd);
            String sqlSelectProjects = "SELECT * FROM projects";
            try (PreparedStatement ps = connection.prepareStatement(sqlSelectProjects)) {
                ResultSet rs = ps.executeQuery();
                while(rs.next()) {
                    int projectId = rs.getInt("project_id");
                    String projectName = rs.getString("name");
                    Project project = new Project(projectId, projectName);
                    projects.add(project);
                }
                return projects;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }
        }

        public void createProject(Project project) {
            Connection connection = ConnectionManager.getConnection(db_url, uid, pwd);
            String sqlInsertProject = "INSERT INTO projects (name) VALUES (?)";
            try (PreparedStatement ps = connection.prepareStatement(sqlInsertProject)) {
                ps.setString(1, project.getName());
                ps.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }
        }

    }
