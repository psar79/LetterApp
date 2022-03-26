package userletters.api.letter.getByPhoneNumber;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class ByPhoneNumberRequest {

    @NotBlank
    @Length(min = 9, max = 9)
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
