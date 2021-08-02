package userletters.api.letter.getByPhoneNumber;

public class LetterByPhoneNumberResponse {

    private Long id;
    private SenderByPhoneResponse senderByPhoneResponse;
    private SenderAddressByPhoneResponse senderAddressByPhoneResponse;
    private ReceiverByPhoneResponse receiverByPhoneResponse;
    private ReceiverAddressByPhoneResponse receiverAddressByPhoneResponse;
    private CreatedAtByPhoneResponse createdAtByPhoneResponse;
    private UpdatedAtByPhoneResponse updatedAtByPhoneResponse;
    private InformationByPhoneResponse informationByPhoneResponse;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SenderByPhoneResponse getSenderByPhoneResponse() {
        return senderByPhoneResponse;
    }

    public void setSenderByPhoneResponse(SenderByPhoneResponse senderByPhoneResponse) {
        this.senderByPhoneResponse = senderByPhoneResponse;
    }

    public SenderAddressByPhoneResponse getSenderAddressByPhoneResponse() {
        return senderAddressByPhoneResponse;
    }

    public void setSenderAddressByPhoneResponse(SenderAddressByPhoneResponse senderAddressByPhoneResponse) {
        this.senderAddressByPhoneResponse = senderAddressByPhoneResponse;
    }

    public ReceiverByPhoneResponse getReceiverByPhoneResponse() {
        return receiverByPhoneResponse;
    }

    public void setReceiverByPhoneResponse(ReceiverByPhoneResponse receiverByPhoneResponse) {
        this.receiverByPhoneResponse = receiverByPhoneResponse;
    }

    public ReceiverAddressByPhoneResponse getReceiverAddressByPhoneResponse() {
        return receiverAddressByPhoneResponse;
    }

    public void setReceiverAddressByPhoneResponse(ReceiverAddressByPhoneResponse receiverAddressByPhoneResponse) {
        this.receiverAddressByPhoneResponse = receiverAddressByPhoneResponse;
    }

    public CreatedAtByPhoneResponse getCreatedAtByPhoneResponse() {
        return createdAtByPhoneResponse;
    }

    public void setCreatedAtByPhoneResponse(CreatedAtByPhoneResponse createdAtByPhoneResponse) {
        this.createdAtByPhoneResponse = createdAtByPhoneResponse;
    }

    public UpdatedAtByPhoneResponse getUpdatedAtByPhoneResponse() {
        return updatedAtByPhoneResponse;
    }

    public void setUpdatedAtByPhoneResponse(UpdatedAtByPhoneResponse updatedAtByPhoneResponse) {
        this.updatedAtByPhoneResponse = updatedAtByPhoneResponse;
    }

    public InformationByPhoneResponse getInformationByPhoneResponse() {
        return informationByPhoneResponse;
    }

    public void setInformationByPhoneResponse(InformationByPhoneResponse informationByPhoneResponse) {
        this.informationByPhoneResponse = informationByPhoneResponse;
    }

    //    private Long id;
//    private LetterByPhoneNumberReceiver letterByPhoneNumberReceiver;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public LetterByPhoneNumberReceiver getLetterByPhoneNumberReceiver() {
//        return letterByPhoneNumberReceiver;
//    }
//
//    public void setLetterByPhoneNumberReceiver(LetterByPhoneNumberReceiver letterByPhoneNumberReceiver) {
//        this.letterByPhoneNumberReceiver = letterByPhoneNumberReceiver;
//    }
}
