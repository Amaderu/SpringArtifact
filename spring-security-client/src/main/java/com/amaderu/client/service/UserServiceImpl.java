package com.amaderu.client.service;

import com.amaderu.client.entity.PasswordResetToken;
import com.amaderu.client.entity.User;
import com.amaderu.client.entity.VerificationToken;
import com.amaderu.client.model.UserModel;
import com.amaderu.client.repository.PasswordResetTokenRepository;
import com.amaderu.client.repository.UserRepository;
import com.amaderu.client.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public User registerUser(UserModel userModel) {
        User user = new User();
        user.setEmail(userModel.getEmail());
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));
        user.setRole("USER");
        userRepository.save(user);
        return user;
    }

    @Override
    public void saveVerificationToken(String token, User user) {
        VerificationToken verificationToken = new VerificationToken(user, token);
        verificationTokenRepository.save(verificationToken);
    }

    @Override
    public String validateVerificationToken(String token) {
        VerificationToken verificationToken =
                verificationTokenRepository.findByToken(token);
        if(verificationToken == null){
            return "invalid token";
        }
        User user = verificationToken.getUser();
        Calendar calendar = Calendar.getInstance();

        if((verificationToken.getExpirationTime().getTime() - calendar.getTime().getTime()) <=0)
        {
            verificationTokenRepository.delete(verificationToken);
            return "Token has expired";
        }

        user.setEnabled(true);
        userRepository.save(user);
        return "valid token";
    }

    @Override
    public VerificationToken generateNewVerificationToken(String oldToken) {
        VerificationToken verificationToken
                = verificationTokenRepository.findByToken(oldToken);
        verificationToken.setToken(UUID.randomUUID().toString());
        verificationTokenRepository.save(verificationToken);
        return verificationToken;
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void createPasswordResetTokenForUser(User user, String token) {
        PasswordResetToken passwordResetToken
                = new PasswordResetToken(user,token);
        passwordResetTokenRepository.save(passwordResetToken);
    }

    @Override
    public String validatePasswordResetToken(String token) {
        PasswordResetToken passwordResetToken =
                passwordResetTokenRepository.findByToken(token);
        if(passwordResetToken == null){
            return "invalid token";
        }

        Calendar calendar = Calendar.getInstance();

        if((passwordResetToken.getExpirationTime().getTime() - calendar.getTime().getTime()) <=0)
        {
            passwordResetTokenRepository.delete(passwordResetToken);
            return "Token has expired";
        }
        return "valid token";
    }

    @Override
    public Optional<User> getUserByPasswordResetToken(String token) {
        return Optional.ofNullable(passwordResetTokenRepository.findByToken(token).getUser());
    }

    @Override
    public void changePassword(User user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    @Override
    public boolean checkIfValidOldPassword(User user, String oldPassword) {
        return passwordEncoder.matches(oldPassword, user.getPassword());
    }
}
