package com.example.demo.service;

import com.example.demo.config.SecurityUtil;
import com.example.demo.dto.NoteDTO;
import com.example.demo.dto.ResDTO;
import com.example.demo.entity.NoteEntity;
import com.example.demo.entity.PuppyEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.mapper.NoteMapper;
import com.example.demo.repository.NoteRepository;
import com.example.demo.repository.PuppyRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


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


    /**
     기능 : 알림장 목록 조회
     */
    public List<NoteDTO> note() {
        String currentUserId = SecurityUtil.getCurrentUserId();
        UserEntity user = userRepository.findByUserId(currentUserId);
        List<PuppyEntity> puppies = puppyRepository.findByUserId(user.getId());

        if (puppies.isEmpty()) {
            return List.of();  // 강아지가 없으면 빈 리스트 반환
        }

        List<NoteEntity> note = noteRepository.findAllByPuppyIn(puppies);

        return note.stream()
                .map(noteMapper::noteToDTO)
                .collect(Collectors.toList());
    }


    /**
     기능 : 알림장 상세 조회
     */
    public NoteDTO noteDetail(Long id) {
        NoteEntity note = noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found"));
        return noteMapper.noteToDTO(note);
    }

}