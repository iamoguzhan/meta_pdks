package tr.com.metasoft.meta_pdks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.metasoft.meta_pdks.model.User;
import tr.com.metasoft.meta_pdks.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    public User save(User user){
        return userRepository.save(user);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User getById(String id){
        return userRepository.findById(id).orElse(null);
    }

    public void delete(User user){
        userRepository.delete(user);
    }

    public User findUserByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }

}
