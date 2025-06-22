package org.BookMyShow.Controller;


import org.BookMyShow.Dto.Request.TheaterDTO;
import org.BookMyShow.Dto.Response.TheaterResponseDTO;
import org.BookMyShow.Entity.Theater;
import org.BookMyShow.Mapper.TheaterMapper;
import org.BookMyShow.Service.TheaterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/theater/")
public class TheaterController {

    private final TheaterService theaterService;

    public TheaterController(TheaterService theaterService){
        this.theaterService = theaterService;
    }

    @PostMapping("/create")
    ResponseEntity<String> createTheater(@RequestBody TheaterDTO dto){
        theaterService.addTheater(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Theater added Successfully");
    }

    @GetMapping("/{id}")
    ResponseEntity<TheaterResponseDTO> getTheaterById(@PathVariable Long id){

        Theater theater = theaterService.getTheaterById(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(TheaterMapper.toDTO(theater));
    }


}
