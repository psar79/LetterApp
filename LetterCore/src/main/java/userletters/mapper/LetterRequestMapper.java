package userletters.mapper;

import org.springframework.stereotype.Component;
import userletters.api.letter.addLetter.request.*;
import userletters.api.letter.getAll.response.LetterResponse;
import userletters.api.letter.getAll.response.ReceiverResponse;
import userletters.api.letter.getAll.response.SenderAddressResponse;
import userletters.api.letter.getAll.response.SenderResponse;
import userletters.api.letter.getById.request.RequestById;
import userletters.api.letter.getByPhoneNumber.RequestByPhoneNumber;
import userletters.dao.entity.*;

import java.util.Objects;

@Component
public class LetterRequestMapper {

    public Letter mapToLetter(LetterRequest letterRequest) {
        if (Objects.isNull(letterRequest) || Objects.isNull(letterRequest.getSenderRequest()) || Objects.isNull(letterRequest.getSenderAddressRequest())
        || Objects.isNull(letterRequest.getReceiverRequest()) || Objects.isNull(letterRequest.getReceiverAddressRequest())
        || Objects.isNull(letterRequest.getCreatedAtRequest()) || Objects.isNull(letterRequest.getUpdatedAtRequest())
                || Objects.isNull(letterRequest.getInformationRequest()))  {
            return null;
        }

        SenderRequest senderRequest = letterRequest.getSenderRequest();

        Sender senderEntity = new Sender();
        senderEntity.setSurname(senderRequest.getSurname2());
        senderEntity.setPhoneNumber(senderRequest.getPhoneNumber2());
        senderEntity.setEmail(senderRequest.getEmail2());

        SenderAddressRequest senderAddressRequest = letterRequest.getSenderAddressRequest();

        SenderAddress senderAddress = new SenderAddress();
        senderAddress.setBuildingNumber(senderAddressRequest.getBuildingNumber());
        senderAddress.setCity(senderAddressRequest.getCity());
        senderAddress.setFlatNumber(senderAddressRequest.getFlatNumber());
        senderAddress.setPostcode(senderAddressRequest.getPostcode());

        ReceiverRequest receiverRequest = letterRequest.getReceiverRequest();

        Receiver receiver = new Receiver();
        receiver.setName(receiverRequest.getName());
        receiver.setSurname(receiverRequest.getSurname());
        receiver.setPhoneNumber(receiverRequest.getPhoneNumber());
        receiver.setEmail(receiverRequest.getEmail());

        ReceiverAddressRequest receiverAddressRequest = letterRequest.getReceiverAddressRequest();

        ReceiverAddress receiverAddress = new ReceiverAddress();
        receiverAddress.setPostCode(receiverAddressRequest.getPostCode());
        receiverAddress.setCity(receiverAddressRequest.getCity());
        receiverAddress.setStreet(receiverAddressRequest.getStreet());
        receiverAddress.setBuildingNumber(receiverAddressRequest.getBuildingNumber());
        receiverAddress.setFlatNumber(receiverAddressRequest.getFlatNumber());

        CreatedAtRequest createdAtRequest = letterRequest.getCreatedAtRequest();

        CreatedAt createdAt = new CreatedAt();
        createdAt.setCreateDate(createdAtRequest.getCreateDate());

        UpdatedAtRequest updatedAtRequest = letterRequest.getUpdatedAtRequest();

        UpdatedAt updatedAt = new UpdatedAt();
        updatedAt.setUpdateDate(updatedAtRequest.getUpdateDate());

        InformationRequest informationRequest = letterRequest.getInformationRequest();

        Information information = new Information();
        information.setSize(informationRequest.getSize());
//        information.setType(information2.getType2());

        Letter letter = new Letter();
        letter.setSender(senderEntity);
        letter.setSenderAddress(senderAddress);
        letter.setReceiver(receiver);
        letter.setReceiverAddress(receiverAddress);
        letter.setCreatedAt(createdAt);
        letter.setUpdatedAt(updatedAt);
        letter.setInformation(information);

        return letter;
    }

    public LetterResponse mapToLetterResponseById(RequestById requestById){
        if(Objects.isNull(requestById) || requestById.getId()<0){
            return null;
        }

        LetterResponse letterResponse = new LetterResponse();
        letterResponse.setId(requestById.getId());

        return letterResponse;
    }

    public LetterResponse mapToLetterResponseByPhoneNumber(RequestByPhoneNumber requestByPhoneNumber) {
        if(Objects.isNull(requestByPhoneNumber) || requestByPhoneNumber.getPhoneNumber().length() != 9){
            return null;
        }
        String phoneNumber = requestByPhoneNumber.getPhoneNumber();
        LetterResponse letterResponse = new LetterResponse();

        ReceiverResponse receiverResponse = new ReceiverResponse();
        receiverResponse.setPhoneNumberResponse(phoneNumber);
        return letterResponse;
    }
    public Letter mapToLetterByPhoneNumber(RequestByPhoneNumber requestByPhoneNumber) {
        if(Objects.isNull(requestByPhoneNumber) || requestByPhoneNumber.getPhoneNumber().length() != 9){
            return null;
        }
        String phoneNumber = requestByPhoneNumber.getPhoneNumber();
        Letter letter = new Letter();

        ReceiverResponse receiverResponse = new ReceiverResponse();
        receiverResponse.setPhoneNumberResponse(phoneNumber);
        return letter;
    }
}
