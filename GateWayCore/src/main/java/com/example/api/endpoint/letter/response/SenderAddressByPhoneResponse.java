package com.example.api.endpoint.letter.response;

public class SenderAddressByPhoneResponse {

    private String postcodeByPhoneResponse;
    private String cityByPhoneResponse;
    private String buildingNumberByPhoneResponse;
    private String flatNumberByPhoneResponse;

    public String getPostcodeByPhoneResponse() {
        return postcodeByPhoneResponse;
    }

    public void setPostcodeByPhoneResponse(String postcodeByPhoneResponse) {
        this.postcodeByPhoneResponse = postcodeByPhoneResponse;
    }

    public String getCityByPhoneResponse() {
        return cityByPhoneResponse;
    }

    public void setCityByPhoneResponse(String cityByPhoneResponse) {
        this.cityByPhoneResponse = cityByPhoneResponse;
    }

    public String getBuildingNumberByPhoneResponse() {
        return buildingNumberByPhoneResponse;
    }

    public void setBuildingNumberByPhoneResponse(String buildingNumberByPhoneResponse) {
        this.buildingNumberByPhoneResponse = buildingNumberByPhoneResponse;
    }

    public String getFlatNumberByPhoneResponse() {
        return flatNumberByPhoneResponse;
    }

    public void setFlatNumberByPhoneResponse(String flatNumberByPhoneResponse) {
        this.flatNumberByPhoneResponse = flatNumberByPhoneResponse;
    }
}
