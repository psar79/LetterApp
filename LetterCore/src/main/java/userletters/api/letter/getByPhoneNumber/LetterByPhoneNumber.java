package userletters.api.letter.getByPhoneNumber;

import userletters.api.letter.getAll.response.SenderResponse;

public class LetterByPhoneNumber {

    private Long id;
    private LetterByPhoneNumberReceiver letterByPhoneNumberReceiver;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LetterByPhoneNumberReceiver getLetterByPhoneNumberReceiver() {
        return letterByPhoneNumberReceiver;
    }

    public void setLetterByPhoneNumberReceiver(LetterByPhoneNumberReceiver letterByPhoneNumberReceiver) {
        this.letterByPhoneNumberReceiver = letterByPhoneNumberReceiver;
    }
}
