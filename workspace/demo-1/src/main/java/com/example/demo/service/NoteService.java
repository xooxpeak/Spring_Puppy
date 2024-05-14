package com.example.demo.service;

import com.example.demo.dto.NoteDTO;
import com.example.demo.dto.ResDTO;
import com.example.demo.entity.NoteEntity;
import com.example.demo.mapper.NoteMapper;
import com.example.demo.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class NoteService {
	
	@Autowired
	private NoteRepository noteRepository;

    // 알림장 작성
    public ResDTO createNote(NoteDTO noteDTO) {
        // noteDTO -> noteEntity 로 변형하는 맵핑이 필요함.
        // mapper mapstruct
        NoteEntity noteEntity = NoteMapper.instance.noteDTOToNoteEntity(noteDTO);

        // 작성 성공
        return ResDTO.builder()
                .code("200")
                .message("노트 작성 성공")
                .data(noteRepository.save(noteEntity))
                .build();
    }
}
