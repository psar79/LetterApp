package userletters.api.letter.getByPhoneNumber;

import javax.validation.constraints.NotBlank;

public class ByPhoneNumberRequest {

    @NotBlank
    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ByPhoneNumberRequest(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ByPhoneNumberRequest() {
    }
}
