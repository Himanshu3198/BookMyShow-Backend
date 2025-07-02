package org.BookMyShow.Service;
import org.BookMyShow.Entity.User;
import org.BookMyShow.Exception.ResourceNotFoundException;
import org.BookMyShow.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    public User createUser(User user){
        return userRepository.save(user);
    }

    public User getUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User not found with id:"+id));
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public void deleteById(Long id){
        if(!userRepository.existsById(id)){
            throw new ResourceNotFoundException("User not found id: "+id);
        }
        userRepository.deleteById(id);

    }
    public User getUserByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("User not found with email:"+email));
    }

}
