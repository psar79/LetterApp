package userletters.mapper;

import org.springframework.stereotype.Component;
import userletters.api.letter.addLetter.request.*;
import userletters.api.letter.getAll.response.LetterResponse;
import userletters.api.letter.getAll.response.ReceiverResponse;
import userletters.api.letter.getById.request.RequestById;
import userletters.api.letter.getByPhoneNumber.ByPhoneNumberRequest;
import userletters.dao.entity.*;

import java.util.Objects;

@Component
public class LetterRequestMapper {

    public Letter mapToLetter(LetterRequest request) {
//        if (Objects.isNull(request) || Objects.isNull(request.getSenderRequest()) || Objects.isNull(request.getSenderAddressRequest())
//        || Objects.isNull(request.getReceiverRequest()) || Objects.isNull(request.getReceiverAddressRequest())
//        || Objects.isNull(request.getCreatedAtRequest()) || Objects.isNull(request.getUpdatedAtRequest())
//                || Objects.isNull(request.getInformationRequest()))  {
//            return null;
//        }

        SenderRequest senderRequest = request.getSenderRequest();

        Sender senderEntity = new Sender();
        senderEntity.setSurname(senderRequest.getSurname2());
        senderEntity.setPhoneNumber(senderRequest.getPhoneNumber2());
        senderEntity.setEmail(senderRequest.getEmail2());

        SenderAddressRequest senderAddressRequest = request.getSenderAddressRequest();

        SenderAddress senderAddress = new SenderAddress();
        senderAddress.setBuildingNumber(senderAddressRequest.getBuildingNumber());
        senderAddress.setCity(senderAddressRequest.getCity());
        senderAddress.setFlatNumber(senderAddressRequest.getFlatNumber());
        senderAddress.setPostcode(senderAddressRequest.getPostcode());

        ReceiverRequest receiverRequest = request.getReceiverRequest();

        Receiver receiver = new Receiver();
        receiver.setName(receiverRequest.getName());
        receiver.setSurname(receiverRequest.getSurname());
        receiver.setPhoneNumber(receiverRequest.getPhoneNumber());
        receiver.setEmail(receiverRequest.getEmail());

        ReceiverAddressRequest receiverAddressRequest = request.getReceiverAddressRequest();

        ReceiverAddress receiverAddress = new ReceiverAddress();
        receiverAddress.setPostCode(receiverAddressRequest.getPostCode());
        receiverAddress.setCity(receiverAddressRequest.getCity());
        receiverAddress.setStreet(receiverAddressRequest.getStreet());
        receiverAddress.setBuildingNumber(receiverAddressRequest.getBuildingNumber());
        receiverAddress.setFlatNumber(receiverAddressRequest.getFlatNumber());

        CreatedAtRequest createdAtRequest = request.getCreatedAtRequest();

        CreatedAt createdAt = new CreatedAt();
        createdAt.setCreateDate(createdAtRequest.getCreateDate());

        UpdatedAtRequest updatedAtRequest = request.getUpdatedAtRequest();

        UpdatedAt updatedAt = new UpdatedAt();
        updatedAt.setUpdateDate(updatedAtRequest.getUpdateDate());

        InformationRequest informationRequest = request.getInformationRequest();

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

    public LetterResponse mapToLetterResponseByPhoneNumber(ByPhoneNumberRequest byPhoneNumberRequest) {
        if(Objects.isNull(byPhoneNumberRequest) || byPhoneNumberRequest.getPhoneNumber().length() != 9){
            return null;
        }
        String phoneNumber = byPhoneNumberRequest.getPhoneNumber();
        LetterResponse letterResponse = new LetterResponse();

        ReceiverResponse receiverResponse = new ReceiverResponse();
        receiverResponse.setPhoneNumberResponse(phoneNumber);
        return letterResponse;
    }
    public Letter mapToLetterByPhoneNumber(ByPhoneNumberRequest byPhoneNumberRequest) {
        if(Objects.isNull(byPhoneNumberRequest) || byPhoneNumberRequest.getPhoneNumber().length() != 9){
            return null;
        }
//        String phoneNumber = requestByPhoneNumber.getPhoneNumber();
        Letter letter = new Letter();

//        ReceiverResponse receiverResponse = new ReceiverResponse();
//        receiverResponse.setPhoneNumberResponse(phoneNumber);
        return letter;
    }
}
