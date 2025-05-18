package vn.hcmuaf.edu.vn.user_service.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.hcmuaf.edu.vn.user_service.exception.ResourceNotFoundException;
import vn.hcmuaf.edu.vn.user_service.model.User;
import vn.hcmuaf.edu.vn.user_service.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    public User createUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        // Hash password before saving
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        return userRepository.save(user);
    }

    public User updateUser(Long id, User userDetails) {
        User user = getUserById(id);

        user.setUsername(userDetails.getUsername());
        if (!userDetails.getPassword().equals(user.getPassword())) {
            user.setPassword(BCrypt.hashpw(userDetails.getPassword(), BCrypt.gensalt()));
        }
        user.setRole(userDetails.getRole());

        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        User user = getUserById(id);
        userRepository.delete(user);
    }
}