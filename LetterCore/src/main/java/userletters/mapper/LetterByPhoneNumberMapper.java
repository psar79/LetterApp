package userletters.mapper;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import userletters.api.letter.getByPhoneNumber.*;
import userletters.dao.entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class LetterByPhoneNumberMapper {

    public LettersByPhoneNumberResponse mapToLetterByPhoneNumberResponse(List<Letter> letters) {

        if (CollectionUtils.isEmpty(letters)) {
            return null;
        }
        List<LetterByPhoneNumberResponse> letterByPhoneNumberResponses = letters.stream()
                .filter(Objects::nonNull)
//                .map(e->this.toLetterByPhoneNumber(e))
                .map(this::toLetterByPhoneNumber)
                .collect(Collectors.toList());

        LettersByPhoneNumberResponse response = new LettersByPhoneNumberResponse();
        response.setLetterByPhoneNumberReceiverList(letterByPhoneNumberResponses);

        return response;

    }

    public LettersByPhoneNumberResponse mapReceiverPhoneNumberToLetterByPhoneNumberResponse(List<Receiver> receivers) {
        List<LetterByPhoneNumberResponse> letters = Optional.ofNullable(receivers).orElse(new ArrayList<>()).stream()
                .filter(Objects::nonNull)
                .map(this::fromReceiverToLetter)
                .map(this::forLetterToLetterByPhoneNumberResponse)
                .collect(Collectors.toList());

        LettersByPhoneNumberResponse response = new LettersByPhoneNumberResponse();
        response.setLetterByPhoneNumberReceiverList(letters);
        return response;
    }

    private Letter fromReceiverToLetter(Receiver receiver) {
        if (Objects.isNull(receiver) || Objects.isNull(receiver.getPhoneNumber())) {
            return null;
        }
        Letter letter1 = new Letter();
        Receiver receiver2 = letter1.getReceiver();
        receiver2.setPhoneNumber(receiver.getPhoneNumber());

        Receiver receiver1 = new Receiver();
        receiver1.setPhoneNumber(receiver.getPhoneNumber());
        Letter letter = new Letter();
        letter.setReceiver(receiver1);
        return letter;
    }

    private LetterByPhoneNumberResponse forLetterToLetterByPhoneNumberResponse(Letter letter) {
        if(Objects.isNull(letter) || Objects.isNull(letter.getReceiver())){
            return null;
        }
        ReceiverByPhoneResponse receiverByPhoneResponse = new ReceiverByPhoneResponse();
        receiverByPhoneResponse.setPhoneNumberByPhoneResponse(letter.getReceiver().getPhoneNumber());
        LetterByPhoneNumberResponse letterByPhoneNumberResponse = new LetterByPhoneNumberResponse();
        letterByPhoneNumberResponse.setReceiverByPhoneResponse(receiverByPhoneResponse);
        return letterByPhoneNumberResponse;
    }
//    private LetterByPhoneNumberResponse toLetterByPhoneNumberResponseFromReceiver(Receiver receiver){
//        if (Objects.isNull(receiver) || Objects.isNull(receiver.getPhoneNumber())) {
//            return null;
//        }
//        ReceiverByPhoneResponse receiverByPhoneResponse = new ReceiverByPhoneResponse();
//        receiverByPhoneResponse.setPhoneNumberByPhoneResponse(receiver.getPhoneNumber());
//        LetterByPhoneNumberResponse letterByPhoneNumberResponse = new LetterByPhoneNumberResponse();
//        letterByPhoneNumberResponse.setReceiverByPhoneResponse(receiverByPhoneResponse);
//        return letterByPhoneNumberResponse;
//    }
//    private Letter fromReceiverToLetter(Receiver receiver) {
//        Letter letter = new Letter();
//        if (Objects.isNull(receiver) || Objects.isNull(receiver.getPhoneNumber()) || Objects.isNull(receiver.getEmail()) || Objects.isNull(receiver.getName())
//                || Objects.isNull(receiver.getSurname()) || Objects.isNull(letter.getReceiver())) {
//            return null;
//        }
//        Receiver letterReceiver = letter.getReceiver();
////        letterReceiver.setSurname(receiver.getSurname());
////        letterReceiver.setName(receiver.getName());
////        letterReceiver.setEmail(receiver.getEmail());
//        letterReceiver.setPhoneNumber(receiver.getPhoneNumber());
//        letter.setReceiver(letterReceiver);
//        return letter;
//    }
//
//
//    private LetterByPhoneNumberResponse forLetterToLetterByPhoneNumberResponse(Letter letter) {
//        if (Objects.isNull(letter) || Objects.isNull(letter.getReceiver())) {
//            return null;
//        }
//        LetterByPhoneNumberResponse letterByPhoneNumberResponse = new LetterByPhoneNumberResponse();
//        ReceiverByPhoneResponse receiverByPhoneResponse = letterByPhoneNumberResponse.getReceiverByPhoneResponse();
//        receiverByPhoneResponse.setPhoneNumberByPhoneResponse(letter.getReceiver().getPhoneNumber());
//
//        letterByPhoneNumberResponse.setReceiverByPhoneResponse(receiverByPhoneResponse);
//        return letterByPhoneNumberResponse;
//    }

    private LetterByPhoneNumberResponse toLetterByPhoneNumber(Letter letter) {

        if (Objects.isNull(letter) || Objects.isNull(letter.getSender()) || Objects.isNull(letter.getSenderAddress())
                || Objects.isNull(letter.getReceiver()) || Objects.isNull(letter.getReceiverAddress()) || Objects.isNull(letter.getCreatedAt())
                || Objects.isNull(letter.getInformation()) || Objects.isNull(letter.getUpdatedAt()) || Objects.isNull(letter.getLetterStatus())) {
            return null;
        }

        Sender sender = letter.getSender();
        SenderAddress senderAddress = letter.getSenderAddress();
        Receiver receiver = letter.getReceiver();
        ReceiverAddress receiverAddress = letter.getReceiverAddress();
        CreatedAt createdAt = letter.getCreatedAt();
        UpdatedAt updatedAt = letter.getUpdatedAt();
        Information information = letter.getInformation();

        SenderByPhoneResponse senderByPhoneResponse = new SenderByPhoneResponse();
        SenderAddressByPhoneResponse senderAddressByPhoneResponse = new SenderAddressByPhoneResponse();
        ReceiverByPhoneResponse receiverByPhoneResponse = new ReceiverByPhoneResponse();
        ReceiverAddressByPhoneResponse receiverAddressByPhoneResponse = new ReceiverAddressByPhoneResponse();
        CreatedAtByPhoneResponse createdAtByPhoneResponse = new CreatedAtByPhoneResponse();
        UpdatedAtByPhoneResponse updatedAtByPhoneResponse = new UpdatedAtByPhoneResponse();
        InformationByPhoneResponse informationByPhoneResponse = new InformationByPhoneResponse();

        senderByPhoneResponse.setPhoneNumberByPhoneResponse(sender.getPhoneNumber());
        senderByPhoneResponse.setEmailByPhoneResponse(sender.getEmail());
        senderByPhoneResponse.setSurnameByPhoneResponse(sender.getSurname());

        senderAddressByPhoneResponse.setBuildingNumberByPhoneResponse(senderAddress.getBuildingNumber());
        senderAddressByPhoneResponse.setCityByPhoneResponse(senderAddress.getCity());
        senderAddressByPhoneResponse.setFlatNumberByPhoneResponse(senderAddress.getFlatNumber());
        senderAddressByPhoneResponse.setPostcodeByPhoneResponse(senderAddress.getPostcode());

        receiverByPhoneResponse.setEmailByPhoneResponse(receiver.getEmail());
        receiverByPhoneResponse.setNameByPhoneResponse(receiver.getName());
        receiverByPhoneResponse.setPhoneNumberByPhoneResponse(receiver.getPhoneNumber());
        receiverByPhoneResponse.setSurnameByPhoneResponse(receiver.getSurname());

        receiverAddressByPhoneResponse.setBuildingNumberByPhoneResponse(receiverAddress.getBuildingNumber());
        receiverAddressByPhoneResponse.setCityByPhoneResponse(receiverAddress.getCity());
        receiverAddressByPhoneResponse.setFlatNumberByPhoneResponse(receiverAddress.getFlatNumber());
        receiverAddressByPhoneResponse.setPostCodeByPhoneResponse(receiverAddress.getPostCode());
        receiverAddressByPhoneResponse.setStreetByPhoneResponse(receiverAddress.getStreet());

        createdAtByPhoneResponse.setCreateDateByPhoneResponse(createdAt.getCreateDate());

        updatedAtByPhoneResponse.setUpdateDateByPhoneResponse(updatedAt.getUpdateDate());

        informationByPhoneResponse.setSize(information.getSize());
        informationByPhoneResponse.setType(information.getType());

        LetterByPhoneNumberResponse letterByPhoneNumberResponse = new LetterByPhoneNumberResponse();
        letterByPhoneNumberResponse.setSenderByPhoneResponse(senderByPhoneResponse);
        letterByPhoneNumberResponse.setSenderAddressByPhoneResponse(senderAddressByPhoneResponse);
        letterByPhoneNumberResponse.setReceiverByPhoneResponse(receiverByPhoneResponse);
        letterByPhoneNumberResponse.setReceiverAddressByPhoneResponse(receiverAddressByPhoneResponse);
        letterByPhoneNumberResponse.setCreatedAtByPhoneResponse(createdAtByPhoneResponse);
        letterByPhoneNumberResponse.setUpdatedAtByPhoneResponse(updatedAtByPhoneResponse);
        letterByPhoneNumberResponse.setInformationByPhoneResponse(informationByPhoneResponse);


//        LetterByPhoneNumberReceiver letterByPhoneNumberReceiver = new LetterByPhoneNumberReceiver();
//
//        letterByPhoneNumberReceiver.setEmailReceiver(receiver.getEmail());
//        letterByPhoneNumberReceiver.setNameReceiver(receiver.getName());
//        letterByPhoneNumberReceiver.setPhoneNumberReceiver(receiver.getPhoneNumber());
//        letterByPhoneNumberReceiver.setSurnameReceiver(receiver.getSurname());
//
//        LetterByPhoneNumber letterByPhoneNumber = new LetterByPhoneNumber();
//
//        letterByPhoneNumber.setLetterByPhoneNumberReceiver(letterByPhoneNumberReceiver);

        return letterByPhoneNumberResponse;

    }
}

