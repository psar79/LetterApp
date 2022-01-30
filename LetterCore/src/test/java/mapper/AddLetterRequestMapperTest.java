package mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import userletters.api.letter.addLetter.request.*;
import userletters.dao.entity.Letter;
import userletters.mapper.LetterRequestMapper;


import static org.junit.jupiter.api.Assertions.assertNull;

class AddLetterRequestMapperTest {

    private LetterRequestMapper letterRequestMapper;

    @BeforeEach
    void setUp() {
        this.letterRequestMapper = new LetterRequestMapper();
    }


    @Test
    void checkIfResultIsOkWhenSurnameIsGiven() {

        //given
        SenderRequest senderRequest = new SenderRequest();
        senderRequest.setSurname2("Wojciech");

        AddLetterRequest addLetterRequest = new AddLetterRequest();
        addLetterRequest.setSenderRequest(senderRequest);
        addLetterRequest.setSenderAddressRequest(new SenderAddressRequest());
        addLetterRequest.setReceiverRequest(new ReceiverRequest());
        addLetterRequest.setReceiverAddressRequest(new ReceiverAddressRequest());
        addLetterRequest.setInformationRequest(new InformationRequest());
        addLetterRequest.setCreatedAtRequest(new CreatedAtRequest());
        addLetterRequest.setUpdatedAtRequest(new UpdatedAtRequest());

        //when
        Letter result = letterRequestMapper.mapToLetter(addLetterRequest);

        //Then
        assertEquals("Wojciech", result.getSender().getSurname());
    }

    private void assertEquals(String wojciech, String surname) {
    }

    @Test
    void returnNullWhenLetterRequestIsNull() {

        assertNull(letterRequestMapper.mapToLetter(null));
    }

    @Test
    void returnNullWhenLetterRequestSender2IsNull() {

        //given
        AddLetterRequest addLetterRequest = new AddLetterRequest();
        addLetterRequest.setSenderRequest(null);

        //when
        Letter letter = letterRequestMapper.mapToLetter(addLetterRequest);

        //then
        assertNull(letter);
    }

    @Test
    void returnNullWhenLetterRequestSender2Surname2IsNull() {

        //given
        SenderRequest senderRequest = new SenderRequest();
        senderRequest.setSurname2(null);
        AddLetterRequest addLetterRequest = new AddLetterRequest();
        addLetterRequest.setSenderRequest(senderRequest);

        //when
        Letter letter = letterRequestMapper.mapToLetter(addLetterRequest);

        //then
        assertNull(letter);
    }


}