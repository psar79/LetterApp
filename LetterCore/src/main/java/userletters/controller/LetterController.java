package userletters.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import userletters.api.letter.addLetter.request.AddLetterRequest;
import userletters.api.letter.getAll.response.LetterInfo;
import userletters.api.letter.getAll.response.LetterResponse;
import userletters.api.letter.getById.request.RequestById;
import userletters.api.letter.getByPhoneNumber.LettersByPhoneNumberResponse;
import userletters.api.letter.getByPhoneNumber.ByPhoneNumberRequest;
import userletters.dao.entity.Letter;
import userletters.dao.entity.Receiver;
import userletters.manager.LetterManager;
import userletters.mapper.LetterByPhoneNumberMapper;
import userletters.mapper.LetterInfoMapper;
import userletters.mapper.LetterRequestMapper;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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


//    @PostMapping("/letter")
    @PostMapping("/addLetter")
    public ResponseEntity<Void> addLetter(@RequestBody @Valid @NotNull AddLetterRequest request) {
        Letter letter = letterRequestMapper.mapToLetter(request);
        letterManager.add(letter);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/getAll")
    public ResponseEntity<LetterInfo> getAll() {

        Iterable<Letter> all = letterManager.findAll();

        List<Letter> result = new ArrayList<>();
        all.forEach(result::add);
//        all.forEach(e -> result.add(e));

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

    @PostMapping("/byPhoneNumber")
    public ResponseEntity<LettersByPhoneNumberResponse> getByPhoneNumber(@RequestBody @Valid ByPhoneNumberRequest byPhoneNumberRequest) {

        Letter letter = letterRequestMapper.mapToLetterByPhoneNumber(byPhoneNumberRequest);

//        Optional<Receiver> receiverByPhoneNumber = letterManager.findReceiverByPhoneNumber(letter.getReceiver().getPhoneNumber());
        Optional<Receiver> receiverByPhoneNumber = letterManager.findReceiverByPhoneNumber(byPhoneNumberRequest.getPhoneNumber());

        if (!receiverByPhoneNumber.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        LettersByPhoneNumberResponse lettersByPhoneNumberResponse = letterByPhoneNumberMapper.mapReceiverPhoneNumberToLetterByPhoneNumberResponse(Collections.singletonList(receiverByPhoneNumber.get()));
        return ResponseEntity.ok().body(lettersByPhoneNumberResponse);
    }




    @PostMapping("/byPhoneNumberr")
    public ResponseEntity<LettersByPhoneNumberResponse> getByPhoneNumberr(@RequestBody ByPhoneNumberRequest byPhoneNumberRequest) {
        
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
                .filter(list -> list.getReceiver().getPhoneNumber().equals(byPhoneNumberRequest.getPhoneNumber()))
                .collect(Collectors.toList());

        LettersByPhoneNumberResponse lettersByPhoneNumberResponse = letterByPhoneNumberMapper.mapToLetterByPhoneNumberResponse(letterByPhoneNumber);
        if (Objects.isNull(lettersByPhoneNumberResponse)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(lettersByPhoneNumberResponse);
    }

//    @PostMapping("/status")
//    public ResponseEntity<DeliveryStatus> changeStatus(@RequestBody RequestedId requestedId, @RequestBody RequestedStatus requestedStatus) {
//
//        if (Objects.isNull(requestedId) || Objects.isNull(letterManager.findById(requestedId.getId())) || Objects.isNull(requestedStatus)) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//        LetterStatus letterStatus = new LetterStatus();
//
//        if (Objects.isNull(letterStatus.getPresent())) {
//            letterStatus.setPresent(DeliveryStatus.PACZKA_NADANA);
//        }
//
//
//        Optional<Letter> letterById = letterManager.findById(requestedId.getId());
//        letterById==requestedId;
//
//
//        return ResponseEntity.ok();
//    }
//
//        return ResponseEntity.ok().
//
//    build();
//}
}

