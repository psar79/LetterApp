package userletters.mapper;

import org.springframework.stereotype.Component;
import userletters.api.letter.getAll.response.*;
import userletters.dao.entity.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class LetterInfoMapper {

    public LetterInfo mapToResponse(List<Letter> letters) {

        List<LetterResponse> letterResponse = Optional.ofNullable(letters)
                .orElse(Collections.emptyList())
                .stream()
                .filter(Objects::nonNull)
                .map(this::toLetterResponse)
                .collect(Collectors.toList());

// Można to opcjonalnie z tym co powyżej
//        if (CollectionUtils.isEmpty(letters)) {
//            return null;
//        }
//        List<LetterResponse> letterResponse = letters.stream()
//                .filter(Objects::nonNull)
//                .map(this::toLetterResponse)
//                .collect(Collectors.toList());

        LetterInfo letterInfo = new LetterInfo();
        letterInfo.setLetterResponses(letterResponse);
        letterInfo.setQuantity(letterResponse.size());

        return letterInfo;

    }

    public LetterResponse toLetterResponse(Letter letter) {
        if (Objects.isNull(letter)) {
            return null;
        }
        LetterResponse letterResponse = new LetterResponse();
        Sender sender = letter.getSender();
        if (Objects.nonNull(sender)) {
            SenderResponse senderResponse = new SenderResponse();
            senderResponse.setSurnameResponse(sender.getSurname());
            senderResponse.setPhoneNumberResponse(sender.getPhoneNumber());
            senderResponse.setEmailResponse(sender.getEmail());
            letterResponse.setSenderResponse(senderResponse);
        }
        SenderAddress senderAddress = letter.getSenderAddress();
        if (Objects.nonNull(senderAddress)) {
            SenderAddressResponse senderAddressResponse = new SenderAddressResponse();
            senderAddressResponse.setBuildingNumberResponse(senderAddress.getBuildingNumber());
            senderAddressResponse.setCityResponse(senderAddress.getCity());
            senderAddressResponse.setFlatNumberResponse(senderAddress.getFlatNumber());
            senderAddressResponse.setPostcodeResponse(senderAddress.getPostcode());
            letterResponse.setSenderAddressResponse(senderAddressResponse);
        }
        Receiver receiver = letter.getReceiver();
        if (Objects.nonNull(receiver)) {
            ReceiverResponse receiverResponse = new ReceiverResponse();
            receiverResponse.setNameResponse(receiver.getName());
            receiverResponse.setSurnameResponse(receiver.getSurname());
            receiverResponse.setEmailResponse(receiver.getEmail());
            receiverResponse.setPhoneNumberResponse(receiver.getPhoneNumber());
            letterResponse.setReceiverResponse(receiverResponse);
        }
        ReceiverAddress receiverAddress = letter.getReceiverAddress();
        if (Objects.nonNull(receiverAddress)) {
            ReceiverAddressResponse receiverAddressResponse = new ReceiverAddressResponse();
            receiverAddressResponse.setBuildingNumberResponse(receiverAddress.getBuildingNumber());
            receiverAddressResponse.setCityResponse(receiverAddress.getCity());
            receiverAddressResponse.setFlatNumberResponse(receiverAddress.getFlatNumber());
            receiverAddressResponse.setPostCodeResponse(receiverAddress.getPostCode());
            letterResponse.setReceiverAddressResponse(receiverAddressResponse);
        }
        CreatedAt createdAt = letter.getCreatedAt();
        if (Objects.nonNull(createdAt)) {
            CreatedAtResponse createdAtResponse = new CreatedAtResponse();
            createdAtResponse.setCreateDateResponse(createdAt.getCreateDate());
            letterResponse.setCreatedAtResponse(createdAtResponse);
        }
        UpdatedAt updatedAt = letter.getUpdatedAt();
        if (Objects.nonNull(updatedAt)) {
            UpdatedAtResponse updatedAtResponse = new UpdatedAtResponse();
            updatedAtResponse.setUpdateDateResponse(updatedAt.getUpdateDate());
            letterResponse.setUpdatedAtResponse(updatedAtResponse);
        }

        Information information = letter.getInformation();
        if (Objects.nonNull(information)) {
            InformationResponse informationResponse = new InformationResponse();
            informationResponse.setSizeResponse(information.getSize());
            letterResponse.setInformationResponse(informationResponse);
        }
        return letterResponse;
    }
}
