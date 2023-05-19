package makers.internship.tracker_project.dto;

import lombok.Data;

@Data
public class JwtRequest {
    private String username;
    private String password;
    private String email;
}