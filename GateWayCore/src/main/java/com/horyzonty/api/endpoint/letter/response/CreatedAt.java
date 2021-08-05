package com.horyzonty.api.endpoint.letter.response;

import java.util.Date;

public class CreatedAt {

    private Date createDate = new Date();

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
