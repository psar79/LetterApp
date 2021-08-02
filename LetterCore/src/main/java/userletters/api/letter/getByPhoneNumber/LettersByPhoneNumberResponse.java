package userletters.api.letter.getByPhoneNumber;

import java.util.List;

public class LettersByPhoneNumberResponse {

    private List<LetterByPhoneNumberResponse> letterByPhoneNumberResponseReceiverList;

    public List<LetterByPhoneNumberResponse> getLetterByPhoneNumberReceiverList() {
        return letterByPhoneNumberResponseReceiverList;
    }

    public void setLetterByPhoneNumberReceiverList(List<LetterByPhoneNumberResponse> letterByPhoneNumberResponseReceiverList) {
        this.letterByPhoneNumberResponseReceiverList = letterByPhoneNumberResponseReceiverList;
    }
}

