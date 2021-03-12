package ch.noser.uek223.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(UUID id) {
        Optional<User> optional = userRepository.findById(id);

        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new NoSuchElementException(String.format("%s with ID '%s' not found", "User", id));
        }
    }

    @Override
    public User create(User entity) throws RuntimeException {
        Optional<User> optional = userRepository.findByEmail(entity.getEmail());

        if(optional.isPresent()) {
            throw new RuntimeException(String.format("%s with Email '%s' already exists", "User", entity.getEmail()));
        }

        entity.setId(null);

        entity.setPasswordHash(encoder.encode(entity.getPasswordHash()));

        entity = userRepository.saveAndFlush(entity);

        return entity;
    }

    @Override
    public final User save(User user) {
//        Optional<User> optional = userRepository.findByEmail(user.getEmail());
//
//        if (optional.isPresent()) {
//            throw new RuntimeException(String.format("%s with Email '%s' already exists", "User", user.getEmail()));
//        }
//
//        user.setId(null);
//        user.setPasswordHash(encoder.encode(user.getPasswordHash()));
//        user = userRepository.saveAndFlush(user);
//
//        return user;

        return userRepository.saveAndFlush(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).map(UserDetailsImpl::new).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
