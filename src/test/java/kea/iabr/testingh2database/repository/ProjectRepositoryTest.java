package kea.iabr.testingh2database.repository;

import kea.iabr.testingh2database.model.Project;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
// NB Tests fail if the following line is not included as the h2 database is not reset between tests
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:h2schema.sql")
class ProjectRepositoryTest {

    @Autowired
    ProjectRepository projectRepository;

    @Test
    void getProjects() {
        int expectedResult = 2;
        int actualResult = projectRepository.getProjects().size();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void createProject() {
        Project project = new Project("Test Project");
        projectRepository.createProject(project);
        int expectedResult = 3;
        int actualResult = projectRepository.getProjects().size();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void createProjectWithNameAlreadyExists() {
        Project project = new Project("Test Project");
        projectRepository.createProject(project);
        try {
            projectRepository.createProject(project);
            fail("Expected RuntimeException was not thrown.");
        } catch (RuntimeException e) {
            assertNotNull(e.getMessage());
        } catch (Exception e) {
            fail("An unexpected exception type was thrown.");
        }
    }
}