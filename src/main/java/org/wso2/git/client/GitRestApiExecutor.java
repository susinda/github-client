package org.wso2.git.client;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.wso2.git.client.dto.Constants;
import org.wso2.git.client.dto.GitConfig;
import org.wso2.git.client.dto.HTTPResponse;
import org.wso2.git.client.utils.HTTPInvoker;
import org.apache.commons.codec.binary.Base64;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class GitRestApiExecutor {


    public static void main(String[] args) {

        System.out.println("Creating Issues ");
        createIssues();
        System.out.println("Exit");
    }


    public static boolean createIssues() {

        String gitRepo = GitConfig.getInstance().getRepo();
        String user = GitConfig.getInstance().getUsername();
        String password = GitConfig.getInstance().getPassword();
        String issuesCSV = GitConfig.getInstance().getIssuesFilePath();

        HashMap<String, String> headers = new HashMap<String, String>();
        String authStringEnc = new String(Base64.encodeBase64((user + ":" + password).getBytes()));
        headers.put(Constants.Header.AUTH, "Basic " + authStringEnc);
        headers.put(Constants.Header.CONTENT_TYPE, Constants.ContentType.APPLICATION_JSON);


        String line = "";
        String cvsSplitBy = ",";
        BufferedReader br = null;

        try {

            br = new BufferedReader(new FileReader(issuesCSV));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] fields = line.split(cvsSplitBy);
                String title = fields[1];
                String url = fields[0];
                String component = fields[2];
                String assignee = fields[3];
                String priority = fields[4];
                String created = fields[5];
                String body = "JIRA_url = " + url + "\ncomponent = " + component + "\nassignee = " + assignee
                        + "\npriority = " + priority + "\ncreated = " + created;

                JSONObject issue = getJsonObject(title, body);
                HTTPResponse httpResponse = HTTPInvoker.sendHTTPPost(gitRepo, issue.toJSONString(), headers);
                if (httpResponse.getResponseCode() == 201) {
                    System.out.println("Issue " + title + " created successfully" );
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        return true;
    }

    private static JSONObject getJsonObject(String title, String body) {
        JSONObject issue = new JSONObject();
        issue.put("title", title);
        issue.put("body", body);
        JSONArray labels = new JSONArray();
        labels.add("bug" );
        issue.put("labels", labels);
        return issue;
    }
}
