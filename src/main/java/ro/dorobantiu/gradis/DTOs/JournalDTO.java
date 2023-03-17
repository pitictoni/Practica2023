package ro.dorobantiu.gradis.DTOs;

import ro.dorobantiu.gradis.entities.Paper;

import java.util.Collection;

public record JournalDTO(String id, String title, float impactFactor, String indexing, String woSCathegory, String quartil, String ISSN, String eISSN) {
}
