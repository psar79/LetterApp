package userletters.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import userletters.api.letter.addLetter.request.LetterRequest;
import userletters.api.letter.getAll.response.LetterInfo;
import userletters.api.letter.getAll.response.LetterResponse;
import userletters.api.letter.getById.request.RequestById;
import userletters.api.letter.getByPhoneNumber.RequestByPhoneNumber;
import userletters.dao.entity.Letter;
import userletters.manager.LetterManager;
import userletters.mapper.LetterByPhoneNumberMapper;
import userletters.mapper.LetterInfoMapper;
import userletters.mapper.LetterRequestMapper;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class LetterController {

    private final LetterManager letterManager;
    private final LetterRequestMapper letterRequestMapper;
    private final LetterInfoMapper letterInfoMapper;
    private LetterByPhoneNumberMapper letterByPhoneNumberMapper;

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
        LetterResponse letterResponse = letterRequestMapper.mapToLetterResponseById(requestById);
        Optional<Letter> byId = letterManager.findById(letterResponse.getId());

        if (!byId.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        LetterResponse letterResponseFromLetter = letterInfoMapper.toLetterResponse(byId.get());
        return ResponseEntity.ok().body(letterResponseFromLetter);
    }

    public LetterResponse getByPhoneNumber(@RequestParam String number) {

        return null;
    }

    //    @GetMapping("/byPhoneNumber")
//    public ResponseEntity<LetterResponse> getByPhoneNumber(@RequestBody RequestByPhoneNumber requestByPhoneNumber){
//
//        Letter letter = letterRequestMapper.mapToLetterByPhoneNumber(requestByPhoneNumber);
//        Letter byPhoneNumber = letterManager.findByPhoneNumber(letter.getReceiver().getPhoneNumber());
//
//        if(Objects.isNull(byPhoneNumber)){
//            return null;
//        }
//        letterInfoMapper.toLetterResponse(byPhoneNumber.get())
//     return
//    }
    @PostMapping("/byPhoneNumber")
    public ResponseEntity<LettersByPhoneNumberResponse> getByPhoneNumber(@RequestBody RequestByPhoneNumber requestByPhoneNumber) {

        Iterable<Letter> all = letterManager.findAll();

        List<Letter> result = new ArrayList<>();
        all.forEach(result::add);

        List<Letter> byPhoneNumber = result.stream()
                .filter(list -> list.getReceiver().getPhoneNumber().equals(requestByPhoneNumber.getPhoneNumber()))
                .collect(Collectors.toList());

        LettersByPhoneNumberResponse lettersByPhoneNumberResponse = letterByPhoneNumberMapper.mapToLetterByPhoneNumberResponse(byPhoneNumber);

        return ResponseEntity.ok(lettersByPhoneNumberResponse);
    }
}
