package com.example.Hotel;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/hotels")
@CrossOrigin(origins = "http://localhost:5173")
public class HotelController {
private final String uploadDir = "uploads/";
    @Autowired
    private HotelRepository hotelRepository;

    @PostMapping
    public Hotel addHotel(@RequestBody Hotel hotel) {
        System.out.println("i am hotel");
        return hotelRepository.save(hotel);
    }
@GetMapping("/{id}")

    public ResponseEntity<Hotel> getHotelById(@PathVariable String id) {
         System.out.println(id);
        List<Hotel> hotels = hotelRepository.findAll();
        for( Hotel h:hotels){
            if(h.getId().equals(id))
             return ResponseEntity.ok(h);
        }
         return ResponseEntity
                      .status(HttpStatus.INTERNAL_SERVER_ERROR)
                       .body(null);
    }
     @GetMapping("/file")
     public List<Hotel> getAllHotels() {
         return hotelRepository.findAll();
     }
     @PostMapping("/gallery")
    public List<String> createPost( 
            @RequestParam("file") List<MultipartFile> photos,
            @RequestParam(value = "caption", required = false) String caption
           
    ) throws IOException {
        System.out.println("i am inside controller");
        List<String> uploadedFileNames = new ArrayList<>();

        for (MultipartFile file : photos) {
           
                // File dest = new File(uploadDir + photo.getOriginalFilename());
                // dest.getParentFile().mkdirs();
                //  System.out.println("i am inside try");
                // photo.transferTo(dest);
                // //  System.out.println("i am inside try");
                  String originalFilename = file.getOriginalFilename();
 String fileExtension = originalFilename.substring(originalFilename.lastIndexOf('.') + 1);
            //   String fileExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());
             String filename = file.getOriginalFilename();
     Path filepath = Paths.get("uploads", filename);
             Files.write(filepath, file.getBytes());

              
             String imageUrl = "http://localhost:8080/uploads/" + filename;
             uploadedFileNames.add(imageUrl);
            }
        
             return uploadedFileNames;
            }

                // uploadedFileNames.add("http://localhost:8080/uploads/"+photo.getOriginalFilename());
            // } catch (IOException e) {
            //      System.out.println("i am inside catch");
            //     return ResponseEntity
            //             .status(HttpStatus.INTERNAL_SERVER_ERROR)
            //             .body("Failed to upload: " + photo.getOriginalFilename());
            // }
        

        // Here you would normally save post metadata (caption, filenames) to the database
        // e.g., postRepository.save(new Post(caption, uploadedFileNames));

        // return ResponseEntity.ok( uploadedFileNames);
    
     @GetMapping
     public String getAllPosts() {
         File uploadD = new File(uploadDir);
          String file = uploadD.toString();
          return file;
     }
    // public List<String> getAllPosts() {
    //     // Just return photo file names for now.
    //     File uploadD = new File(uploadDir);
    //     String[] files = uploadD.list();
    //     return files != null ? List.of(files) : List.of();
    

    @PostMapping("/image")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file,
            @RequestParam(value = "caption", required = false) String caption) throws IOException {
       
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("No file selected");
        }

        try {
                              String originalFilename = file.getOriginalFilename();
 String fileExtension = originalFilename.substring(originalFilename.lastIndexOf('.') + 1);

             String filename = file.getOriginalFilename();
     Path filepath = Paths.get("uploads", filename);
             Files.write(filepath, file.getBytes());

              
             String imageUrl = "http://localhost:8080/uploads/" + filename;
         return ResponseEntity.ok( imageUrl);
            }
           
        catch (IOException e) {
            return ResponseEntity
                  .status(HttpStatus.INTERNAL_SERVER_ERROR)
                  .body("Failed to upload: ");
        }
       
    }
  @PostMapping("/roomImage")
    public ResponseEntity<String> roomImage(@RequestParam("file") MultipartFile file,
            @RequestParam(value = "caption", required = false) String caption) throws IOException {
       
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("No file selected");
        }

        try {
                              String originalFilename = file.getOriginalFilename();
 String fileExtension = originalFilename.substring(originalFilename.lastIndexOf('.') + 1);

             String filename = file.getOriginalFilename();
     Path filepath = Paths.get("uploads", filename);
             Files.write(filepath, file.getBytes());

              
             String imageUrl = "http://localhost:8080/uploads/" + filename;
         return ResponseEntity.ok( imageUrl);
            }
           
        catch (IOException e) {
            return ResponseEntity
                  .status(HttpStatus.INTERNAL_SERVER_ERROR)
                  .body("Failed to upload: ");
        }
       
    }
}

