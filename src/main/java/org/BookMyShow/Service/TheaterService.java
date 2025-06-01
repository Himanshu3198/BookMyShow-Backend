package org.BookMyShow.Service;

import org.BookMyShow.Entity.Theater;
import org.BookMyShow.Exception.ResourceNotFoundException;
import org.BookMyShow.Repository.TheaterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheaterService {

    private final TheaterRepository theaterRepository;

    public TheaterService(TheaterRepository theaterRepository){
        this.theaterRepository = theaterRepository;
    }

    public Theater createTheater(Theater theater){
        return theaterRepository.save(theater);
    }

    public Theater getTheaterById(Long id){
        return theaterRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Theater not found with id:"+id));
    }

    public List<Theater> getAllTheater(){
        return theaterRepository.findAll();
    }

    public void deleteTheaterById(Long id){
        if(!theaterRepository.existsById(id)){
            throw new ResourceNotFoundException("Theater not found with id:"+id);
        }
    }
}
