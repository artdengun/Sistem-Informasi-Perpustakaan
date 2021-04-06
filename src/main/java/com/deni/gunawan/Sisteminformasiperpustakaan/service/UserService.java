package com.deni.gunawan.Sisteminformasiperpustakaan.service;

import com.deni.gunawan.Sisteminformasiperpustakaan.model.Role;
import com.deni.gunawan.Sisteminformasiperpustakaan.model.User;
import com.deni.gunawan.Sisteminformasiperpustakaan.repository.RoleRepository;
import com.deni.gunawan.Sisteminformasiperpustakaan.repository.UserRepository;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class UserService {

    @Autowired  private UserRepository userRepository;
    @Autowired  private RoleRepository roleRepository;
    @Autowired  private PasswordEncoder passwordEncoder;
    @Autowired  private JavaMailSender javaMailSender;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder,
                       JavaMailSender javaMailSender){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.javaMailSender = javaMailSender;
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findUserByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> listAll(){
        return userRepository.findAll();
    }

    public void register(User user, String siteURL)
            throws UnsupportedEncodingException, MessagingException {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        String randomCode = RandomString.make(64);
        user.setVerificationCode(randomCode);
        user.setActive(false);
        Role userRole = roleRepository.findByRole("USER");
        user.setId_role(new HashSet<Role>(Arrays.asList(userRole)));

        userRepository.save(user);

        sendVerificationEmail(user, siteURL);
    }

    private void sendVerificationEmail(User user, String siteURL)
            throws MessagingException, UnsupportedEncodingException {
        String toAddress = user.getEmail();
        String fromAddress = "payangandev@gmail.com";
        String senderName = "payangandev";
        String subject = "Please verify your registration";
        String content = "Dear [[name]],<br>"
                + "Terimakasih Sudah Mendaftar di SISPUR "
                + "Silahkan Klik Link berikut Ini untuk proses verifikasi data Email Anda:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">Verifikasi</a></h3>"
                + "Terima Kasih,<br>"
                + "Regards : "
                + "Deni Gunawan - Owner";

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", user.getFullName());
        String verifyURL = siteURL + "/verify?code=" + user.getVerificationCode();

        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);

        javaMailSender.send(message);

        System.out.println("Email Berhasil Terkirim");
    }

    public boolean verify(String verificationCode) {
        User user = userRepository.findByVerificationCode(verificationCode);

        if (user == null || user.isActive()) {
            return false;
        } else {
            user.setVerificationCode(null);
            user.setActive(true);
            userRepository.save(user);

            return true;
        }

    }


}
