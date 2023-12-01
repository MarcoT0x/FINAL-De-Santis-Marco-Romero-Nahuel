package com.dh.clinica.config.auth;


import com.dh.clinica.model.entity.AppUser;
import com.dh.clinica.repository.UserRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private UserRepositoy userRepositoy;

    @Autowired
    public DataLoader(UserRepositoy userRepositoy) {
        this.userRepositoy = userRepositoy;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
        String password= passwordEncoder.encode("admin");
        String password2= passwordEncoder.encode("user");

        userRepositoy.save(new AppUser("Admin", "admin", "admin@gmail.com",password, AppUserRoles.ROLE_ADMIN));
        userRepositoy.save(new AppUser("User", "user", "user@gmail.com",password2, AppUserRoles.ROLE_USER));

    }
}
