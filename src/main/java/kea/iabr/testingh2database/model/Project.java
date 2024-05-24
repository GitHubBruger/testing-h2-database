package kea.iabr.testingh2database.model;

public class Project {
    private int projectId;
    private String name;

    public Project() {
    }

    public Project(String name) {
        this.name = name;
    }

    public Project(int projectId, String name) {
        this.projectId = projectId;
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
