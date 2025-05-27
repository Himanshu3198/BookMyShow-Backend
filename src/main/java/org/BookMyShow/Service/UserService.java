package org.BookMyShow.Service;

import lombok.RequiredArgsConstructor;
import org.BookMyShow.Entity.User;
import org.BookMyShow.Exception.ResourceNotFoundException;
import org.BookMyShow.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user){
        return userRepository.save(user);
    }

    public User getUserById(Long id){
        return userRepository.findAllById(Collections.singleton(id))
                .orElseThrow(()-> new ResourceNotFoundException("User not found with id:"+id));
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public void deleteById(Long id){
        userRepository.deleteById(id)
                .orElseThrow(()->new ResourceNotFoundException("User not found with id:"+id));
    }

}
