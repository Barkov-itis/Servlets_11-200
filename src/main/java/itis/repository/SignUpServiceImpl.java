package itis.repository;

import itis.dto.SignUpForm;
import itis.models.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.SQLException;

public class SignUpServiceImpl implements SignUpService{

    private AccountsRepository accountsRepository;
    private PasswordEncoder passwordEncoder;

    public SignUpServiceImpl(AccountsRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }
    @Override
    public void signUp(SignUpForm form) throws SQLException {
        User user = User.builder()
                .nameOfUser(form.getFirstName())
                .surnameOfUser(passwordEncoder.encode(form.getPassword()))
                .ageOfUser(23)
                .build();
        System.out.println(user.getSurnameOfUser());

        accountsRepository.save(user);

        // функция проверки пароля
//        Boolean pass = passwordEncoder.matches(rawPass, encodePass);
    }
}
