package userletters.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import userletters.api.letter.addLetter.request.LetterRequest;
import userletters.api.letter.getAll.response.LetterInfo;
import userletters.api.letter.getAll.response.LetterResponse;
import userletters.api.letter.getById.request.RequestById;
import userletters.api.letter.getByPhoneNumber.LettersByPhoneNumberResponse;
import userletters.api.letter.getByPhoneNumber.RequestByPhoneNumber;
import userletters.dao.entity.Letter;
import userletters.manager.LetterManager;
import userletters.mapper.LetterByPhoneNumberMapper;
import userletters.mapper.LetterInfoMapper;
import userletters.mapper.LetterRequestMapper;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class LetterController {

    private final LetterManager letterManager;
    private final LetterRequestMapper letterRequestMapper;
    private final LetterInfoMapper letterInfoMapper;
    private final LetterByPhoneNumberMapper letterByPhoneNumberMapper;

    @Autowired
    public LetterController(LetterManager letterManager, LetterRequestMapper letterRequestMapper, LetterInfoMapper letterInfoMapper, LetterByPhoneNumberMapper letterByPhoneNumberMapper) {
        this.letterManager = letterManager;
        this.letterRequestMapper = letterRequestMapper;
        this.letterInfoMapper = letterInfoMapper;
        this.letterByPhoneNumberMapper = letterByPhoneNumberMapper;
    }

    @PostMapping("/addLetter")
    public ResponseEntity<Void> addLetter(@RequestBody @Valid LetterRequest letterRequest) {
        if (Objects.isNull(letterRequest)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        Letter letter = letterRequestMapper.mapToLetter(letterRequest);
        letterManager.add(letter);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/getAll")
    public ResponseEntity<LetterInfo> getAll() {

        Iterable<Letter> all = letterManager.findAll();

        List<Letter> result = new ArrayList<>();
        all.forEach(result::add);

        LetterInfo letterInfo = letterInfoMapper.mapToResponse(result);

        return ResponseEntity.status(HttpStatus.OK).body(letterInfo);
    }

    @GetMapping("/byId")
    public ResponseEntity<LetterResponse> getById(@RequestBody RequestById requestById) {
        LetterResponse letterResponse = letterRequestMapper.mapToLetterResponseById(requestById); // To chyba nie potrzbne
        Optional<Letter> LetterbyId = letterManager.findById(letterResponse.getId());             // A to chyba powinno być tak    Optional<Letter> LetterbyId = letterManager.findById(requestById.getId());

        if (!LetterbyId.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        LetterResponse letterResponseFromLetter = letterInfoMapper.toLetterResponse(LetterbyId.get());
        return ResponseEntity.ok().body(letterResponseFromLetter);
    }

    public LetterResponse getByPhoneNumber(@RequestParam String number) {
        return null;
    }

    @PostMapping("/byPhoneNumber")
    public ResponseEntity<LettersByPhoneNumberResponse> getByPhoneNumber(@RequestBody RequestByPhoneNumber requestByPhoneNumber) {

        Iterable<Letter> all = letterManager.findAll();

        List<Letter> result = new ArrayList<>();
        all.forEach(result::add);
//        for (Letter str : all){    ???
//            result.add(str);       ???
//        }                          ???
        if (result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        List<Letter> letterByPhoneNumber = result.stream()
                .filter(Objects::nonNull)
                .filter(list -> Objects.nonNull(list.getReceiver()))
                .filter(list -> Objects.nonNull(list.getReceiver().getPhoneNumber()))
                .filter(list -> list.getReceiver().getPhoneNumber().equals(requestByPhoneNumber.getPhoneNumber()))
                .collect(Collectors.toList());

        LettersByPhoneNumberResponse lettersByPhoneNumberResponse = letterByPhoneNumberMapper.mapToLetterByPhoneNumberResponse(letterByPhoneNumber);
        if (Objects.isNull(lettersByPhoneNumberResponse)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(lettersByPhoneNumberResponse);
    }
}

