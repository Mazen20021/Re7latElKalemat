package com.example.wordsjourney.Services;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
@Service
public class smartpasteService {
    public String extractStructuredData(String inputText) throws Exception {
        String prompt = """
    You are a data extraction assistant.
    Given the following pasted text, extract and format it as a JSON array of objects.

    Each object should contain the following fields:
      - department
      - machine
      - reported_to
      - email
      - fault_description
      - detailed_explanation
      - severity_level
      - fault_status
      - responsible_team
      - contractor

    Example:
    Input:
    Department: Maintenance
    Machine: Machine 2
    Reported To: Mohamed Galal – Maintenance Lead
    Email: mohamed@example.com
    Fault Description: refresh every 10 min
    Detailed Explanation: A big problem with the radio activity, patterns unmatched. nonfunctional machine.
    Severity Level: Critical – Immediate action required
    Fault Status: Down – Machine is completely down
    Responsible Team: External Contractor
    Contractor: Industrial Maintenance Pro

    Output:
    [
      {
        "department": "Maintenance",
        "machine": "Machine 2",
        "reported_to": "Mohamed Galal – Maintenance Lead",
        "email": "mohamed@example.com",
        "fault_description": "refresh every 10 min",
        "detailed_explanation": "A big problem with the radio activity, patterns unmatched. nonfunctional machine.",
        "severity_level": "Critical – Immediate action required",
        "fault_status": "Down – Machine is completely down",
        "responsible_team": "External Contractor",
        "contractor": "Industrial Maintenance Pro"
      }
    ]

    Text:
    %s
    """.formatted(inputText);

        String ollamaRequest = new JSONObject()
                .put("model", "llama3.2")
                .put("prompt", prompt)
                .toString();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:11434/api/generate"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(ollamaRequest))
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());

        // Ollama streams multiple JSON lines — extract the "response" fields
        StringBuilder content = new StringBuilder();
        Arrays.stream(response.body().split("\n")).forEach(line -> {
            try {
                JSONObject json = new JSONObject(line);
                if (json.has("response")) {
                    content.append(json.getString("response"));
                }
            } catch (Exception ignored) {}
        });

        return content.toString().trim();
    }
}
