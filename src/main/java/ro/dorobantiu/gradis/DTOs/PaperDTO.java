package ro.dorobantiu.gradis.DTOs;

import java.util.List;

public record PaperDTO(String id, String title, List<AuthorWithIdAndNameDTO> authors) {

}
