package userletters.mapper;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import userletters.api.letter.getByPhoneNumber.*;
import userletters.dao.entity.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class LetterByPhoneNumberMapper {

    public LettersByPhoneNumberResponse mapToLetterByPhoneNumberResponse (List<Letter> letters){

        if(CollectionUtils.isEmpty(letters)){
            return null;
        }
        List<LetterByPhoneNumberResponse> letterByPhoneNumberResponses = letters.stream()
                .filter(Objects::nonNull)
                .map(this::toLetterByPhoneNumber)
                .collect(Collectors.toList());

        LettersByPhoneNumberResponse response = new LettersByPhoneNumberResponse();
        response.setLetterByPhoneNumberReceiverList(letterByPhoneNumberResponses);

        return response;

    }

    private LetterByPhoneNumberResponse toLetterByPhoneNumber(Letter letter) {

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

