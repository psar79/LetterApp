package userletters.api.letter.getByPhoneNumber;

import java.util.Date;

public class CreatedAtByPhoneResponse {

    private Date createDateByPhoneResponse = new Date();

    public Date getCreateDateByPhoneResponse() {
        return createDateByPhoneResponse;
    }

    public void setCreateDateByPhoneResponse(Date createDateByPhoneResponse) {
        this.createDateByPhoneResponse = createDateByPhoneResponse;
    }
}
