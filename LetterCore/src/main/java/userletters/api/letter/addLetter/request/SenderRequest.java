package userletters.api.letter.addLetter.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class SenderRequest {

    @NotBlank                      // " " to jest Blank ale też jest @NoteEmpty i @NotNull ,   "" to jest Empty i Blank ale nie jest @NotNull  ,  null - to jest Null, Blank i Empty ale nie jest @NotNull, @NotEmpty i @NotBlank
    private String surname2;
    @NotBlank
    private String phoneNumber2;
    @Email
    private String email2;

    public String getSurname2() {
        return surname2;
    }

    public void setSurname2(String surname2) {
        this.surname2 = surname2;
    }

    public String getPhoneNumber2() {
        return phoneNumber2;
    }

    public void setPhoneNumber2(String phoneNumber2) {
        this.phoneNumber2 = phoneNumber2;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }
}




