package com.horyzonty.api.endpoint.letter.response;

import java.util.Date;

public class UpdatedAt {

    private Date updateDate = new Date();

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
