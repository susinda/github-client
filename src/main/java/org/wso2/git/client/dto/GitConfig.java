package org.wso2.git.client.dto;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GitConfig {

    private String repo;
    private String username;
    private String password;
    private String issuesFilePath;
    private static GitConfig instance;

    private GitConfig() {

    }

    public String getIssuesFilePath() {
        return issuesFilePath;
    }

    public void setIssuesFilePath(String filePath) {
        this.issuesFilePath = filePath;
    }

    public String getRepo() {
        return repo;
    }

    public void setRepo(String repo) {
        this.repo = repo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static GitConfig getInstance() {
        if (instance == null) {
            instance = new GitConfig();
            loadProperties();
        }
        return instance;
    }


    public static void loadProperties() {
        Properties props = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream("config.properties");
            props.load(input);
            instance.setRepo(props.getProperty("repo"));
            instance.setUsername(props.getProperty("username"));
            instance.setPassword(props.getProperty("password"));
            instance.setIssuesFilePath(props.getProperty("issuesFile"));
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
