package userletters.controller;

import java.util.List;

public class LettersByPhoneNumberResponse {

    private List<ReceiverLetter> receiverLetterList;

    public List<ReceiverLetter> getReceiverLetterList() {
        return receiverLetterList;
    }

    public void setReceiverLetterList(List<ReceiverLetter> receiverLetterList) {
        this.receiverLetterList = receiverLetterList;
    }
}
