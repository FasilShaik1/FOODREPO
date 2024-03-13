package foodapp.services;

import foodapp.dto.Admin_Login_DTO;
import foodapp.dto.Admin_Register_DTO;
import foodapp.model.Admin_Register;
import foodapp.response.Admin_Login_Message;

public interface Admin_Register_services {
	public Admin_Register addAdmin(Admin_Register_DTO ard);
    public Admin_Login_Message loginAdmin(Admin_Login_DTO ald);
}
