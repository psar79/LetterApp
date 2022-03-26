package userletters.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import userletters.api.letter.getByPhoneNumber.LettersByPhoneNumberResponse;
import userletters.dao.entity.*;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LetterByPhoneNumberMapperTest {

    private LetterByPhoneNumberMapper letterByPhoneNumberMapper;

    @BeforeEach
    void setUp() {
        this.letterByPhoneNumberMapper = new LetterByPhoneNumberMapper();
    }

    @Test
    void checkIfResultIsOkWhenSomeDateIsGiven() {

        //given
        Sender sender = new Sender();
//        sender.setEmail("email@interia.pl");
//        sender.setPhoneNumber("234234");

        Receiver receiver = new Receiver();
        receiver.setEmail("wew@werwe.com");
        receiver.setPhoneNumber("3213");

        ReceiverAddress receiverAddress = new ReceiverAddress();
        receiverAddress.setBuildingNumber("2wr2er2e");
        receiverAddress.setCity("Warsaw");

        Letter letter = new Letter();
        letter.setSender(sender);
        letter.setReceiver(receiver);
        letter.setReceiverAddress(receiverAddress);
        letter.setSenderAddress(new SenderAddress());
        letter.setCreatedAt(new CreatedAt());
        letter.setUpdatedAt(new UpdatedAt());
        letter.setInformation(new Information());
        letter.setLetterStatus(new LetterStatus());

        List<Letter> letters = Collections.singletonList(letter);

        //when
        LettersByPhoneNumberResponse lettersByPhoneNumberResponse = letterByPhoneNumberMapper.mapToLetterByPhoneNumberResponse(letters);

        //then
        assertEquals("Warsaw", lettersByPhoneNumberResponse.getLetterByPhoneNumberReceiverList().get(0)
                .getReceiverAddressByPhoneResponse().getCityByPhoneResponse());
//        assertEquals("Warsaw", letter.getReceiverAddress().getCity());
//        assertEquals("");

    }

    @Test
   void checkIfResultIsGiven(){
        //given
        Receiver receiver = new Receiver();
        receiver.setPhoneNumber("444");
        receiver.setEmail("34");
        receiver.setName("324");
        receiver.setSurname("rre");
        List<Receiver> receivers = Collections.singletonList(receiver);

        //when
        LettersByPhoneNumberResponse lettersByPhoneNumberResponse = letterByPhoneNumberMapper
                .mapReceiverPhoneNumberToLetterByPhoneNumberResponse(receivers);

        //then
        assertEquals("444", lettersByPhoneNumberResponse.getLetterByPhoneNumberReceiverList().get(0)
                .getReceiverByPhoneResponse()
                .getPhoneNumberByPhoneResponse());
    }

}