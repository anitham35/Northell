package com.example.Northell.Controller;

import com.example.Northell.Service.NarthellaSetvice;
import com.example.Northell.Util.JsonComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/excel")
public class NarthellaController {
    @Autowired
    NarthellaSetvice narthellaSetvice;

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
       return narthellaSetvice.uploadFile(file);
    }

    @PostMapping("/compare")
    public ResponseEntity<?> compareFiles(
            @RequestParam("file1") MultipartFile file1,
            @RequestParam("file2") MultipartFile file2) {

        try {
            String json1 = new String(file1.getBytes());
            String json2 = new String(file2.getBytes());

            List<String> differences = JsonComparator.compareJson(json1, json2);

            if (differences.isEmpty()) {
                return ResponseEntity.ok("JSON files are equal.");
            } else {
                return ResponseEntity.ok(differences);
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Comparison failed: " + e.getMessage());
        }
    }

  /*  @GetMapping("/getJson")
    public Map<String,Object> getNorthellaJson(){
        return narthellaSetvice.getNorthellaJson();
    }
*/
   /* @PostMapping("/compare/raw")
    public String northellaJsonCompare(@Validated @RequestBody RawOrderLine rawOrderLine){

        return narthellaSetvice.northellaJsonCompare(rawOrderLine);
    }*/
}
