package ro.dorobantiu.gradis.DTOs;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

public record UserDTO(String email, String password, Boolean isEnabled) {
}
