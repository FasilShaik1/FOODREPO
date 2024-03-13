package foodapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import foodapp.AuthConfig.JwtUtil;
import foodapp.dto.Admin_Login_DTO;
import foodapp.dto.Admin_Register_DTO;
import foodapp.model.Admin_Register;
import foodapp.response.Admin_Login_Message;
import foodapp.services.Admin_Register_ServicesImpl;

@RestController
@RequestMapping("api/v1/admin")
public class Admin_Register_Controller {
    @Autowired
    private Admin_Register_ServicesImpl ars;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping(path = "/register", consumes = { "application/json", "application/xml", "application/yaml", "text/csv" })
    public ResponseEntity<?> addAdmin(@Valid @RequestBody Admin_Register_DTO adminDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Invalid input for adding admin");
        }

        Admin_Register registeredAdmin = ars.addAdmin(adminDTO);

        Admin_Register responseAdmin = new Admin_Register(
            registeredAdmin.getAdmin_id(),
            registeredAdmin.getFirstName(),
            registeredAdmin.getLastName(),
            registeredAdmin.getEmail(),
            null,                 
            registeredAdmin.getContactNumber()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(responseAdmin);
    }

    @PostMapping(path = "/login", consumes = { "application/json", "application/xml", "application/yaml", "text/csv" })
    public ResponseEntity<?> loginAdmin(@Validated @RequestBody Admin_Login_DTO adminDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Invalid input for admin login");
        }
        
        Admin_Login_Message alm = ars.loginAdmin(adminDTO);
        if (alm.getStatus()) {
            String token = jwtUtil.generateToken(adminDTO.getFirstName(), "admin");
            alm.setToken(token);
            return ResponseEntity.ok(alm);
        } else {
            if (alm.getMessage().contains("not found")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(alm);
            } else if (alm.getMessage().contains("Password is incorrect")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(alm);
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(alm);
            }
        }
    }

}
