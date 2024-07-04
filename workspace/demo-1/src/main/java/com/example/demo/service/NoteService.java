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

    @Autowired
    private NoteMapper noteMapper;


    /**
     기능 : 알림장 작성
     */
    public ResDTO saveNote(NoteDTO noteDTO) {

        // noteDTO -> noteEntity
        NoteEntity note = noteMapper.noteToEntity(noteDTO);

        // 작성 성공
        return ResDTO.builder()
                .code("200")
                .message("노트 작성 성공")
                .data(noteRepository.save(note))
                .build();
    }
}
