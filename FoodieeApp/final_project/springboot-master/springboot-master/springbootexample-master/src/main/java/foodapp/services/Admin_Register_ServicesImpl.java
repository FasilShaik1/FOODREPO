package foodapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import foodapp.AuthConfig.JwtUtil;
import foodapp.dto.Admin_Login_DTO;
import foodapp.dto.Admin_Register_DTO;
import foodapp.model.Admin_Register;
import foodapp.repo.Admin_Register_Repo;
import foodapp.response.Admin_Login_Message;

import java.util.Optional;

@Service
public class Admin_Register_ServicesImpl implements Admin_Register_services {

    @Autowired
    private Admin_Register_Repo adminRegisterRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @Override
    public Admin_Register addAdmin(Admin_Register_DTO adminRegisterDTO) {
        Admin_Register admin = new Admin_Register(
            adminRegisterDTO.getAdmin_id(),
            adminRegisterDTO.getFirstName(),
            adminRegisterDTO.getLastName(),
            adminRegisterDTO.getEmail(),
            passwordEncoder.encode(adminRegisterDTO.getPassword()),
            adminRegisterDTO.getContactNumber()
        );
        
        Admin_Register savedAdmin = adminRegisterRepo.save(admin);
        
        savedAdmin.setPassword(null);

        return savedAdmin;
    }

    @Override
    public Admin_Login_Message loginAdmin(Admin_Login_DTO adminLoginDTO) {
        Optional<Admin_Register> adminOptional = adminRegisterRepo.findOneByFirstName(adminLoginDTO.getFirstName());
        if (adminOptional.isPresent()) {
            Admin_Register admin = adminOptional.get();
            String encodedPassword = admin.getPassword();
            if (passwordEncoder.matches(adminLoginDTO.getPassword(), encodedPassword)) {
                String token = jwtUtil.generateToken(adminLoginDTO.getFirstName(), "admin");
                return new Admin_Login_Message("Login Success", true, "admin", token);
            } else {
                return new Admin_Login_Message("Login Failed: Password is incorrect", false,"admin", null);
            }
        } else {
            return new Admin_Login_Message("Login Failed: not found", false,"admin", null);
        }
    }
}
