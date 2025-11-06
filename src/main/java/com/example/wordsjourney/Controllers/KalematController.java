package com.example.wordsjourney.Controllers;
import com.example.wordsjourney.Services.smartpasteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:60612" , "http://localhost:58000" , "http://localhost:33924"})
@RestController
@RequestMapping("/api/bsmarter/ai")
public class KalematController  {
    private final smartpasteService smartPasteService;

    @Autowired
    public KalematController(smartpasteService smartPasteService) {
        this.smartPasteService = smartPasteService;
    }
    @GetMapping("/connect")
    public boolean connect() throws Exception {

        return true;
    }
    @PostMapping("/smartpaste")
    public Map<String, Object> smartPaste(@RequestBody Map<String, String> body) throws Exception {
        String inputText = body.get("text");
        String result = smartPasteService.extractStructuredData(inputText);
        return Map.of("result", result);
    }
}
