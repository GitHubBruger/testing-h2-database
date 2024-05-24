CREATE SCHEMA IF NOT EXISTS projectmanager;

DROP TABLE IF EXISTS projects CASCADE;

CREATE TABLE projects (
                          project_id INT AUTO_INCREMENT PRIMARY KEY,
                          parent_project_id INT DEFAULT NULL,
                          name VARCHAR(255) NOT NULL,
                          CONSTRAINT fk_project_parent FOREIGN KEY (parent_project_id)
                              REFERENCES projects (project_id)
                              ON DELETE CASCADE,
                          UNIQUE (name)
);

INSERT INTO projects (name) VALUES ('Project1'), ('Project2');
