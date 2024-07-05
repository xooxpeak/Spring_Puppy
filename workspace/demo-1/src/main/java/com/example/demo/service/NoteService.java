package com.example.demo.service;

import com.example.demo.dto.NoteDTO;
import com.example.demo.dto.ResDTO;
import com.example.demo.entity.NoteEntity;
import com.example.demo.entity.PuppyEntity;
import com.example.demo.mapper.NoteMapper;
import com.example.demo.repository.NoteRepository;
import com.example.demo.repository.PuppyRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class NoteService {
	
	@Autowired
	private NoteRepository noteRepository;

    @Autowired
    private NoteMapper noteMapper;

    @Autowired
    private PuppyRepository puppyRepository;

    @Autowired
    private UserRepository userRepository;


    /**
     기능 : 알림장 작성
     */
    public ResDTO saveNote(NoteDTO noteDTO) {

        // noteDTO -> noteEntity
        NoteEntity note = noteMapper.noteToEntity(noteDTO);

        PuppyEntity puppy = puppyRepository.findById(noteDTO.getPuppyId()).orElseThrow(() -> new RuntimeException("Puppy not found"));
        note.setPuppy(puppy);
        note.setPuppyId(noteDTO.getPuppyId());
//        note.setNoteDate(new Date());     // 날짜 note_date 값 설정

        // note_date 값이 설정되었는지 확인하고, 없으면 기본값으로 현재 날짜를 설정
        if (noteDTO.getNoteDate() != null) {
            note.setNoteDate(noteDTO.getNoteDate());
        } else {
            note.setNoteDate(new Date()); // 기본값 설정
        }

        // 작성 성공
        return ResDTO.builder()
                .code("200")
                .message("노트 작성 성공")
                .data(noteRepository.save(note))
                .build();
    }

}