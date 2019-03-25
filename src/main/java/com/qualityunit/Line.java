package com.qualityunit;

abstract public class Line {
    private static final byte MAX_NUMBER_OF_SERVICES = 10;
    private static final byte MAX_NUMBER_OF_SERVICE_VARIATIONS = 3;
    private static final byte MAX_NUMBER_OF_QUESTION_TYPES = 10;
    private static final byte MAX_NUMBER_OF_QUESTION_TYPE_CATEGORIES = 20;
    private static final byte MAX_NUMBER_OF_QUESTION_TYPE_CATEGORY_SUBTYPES = 5;
    private byte serviceId;
    private byte serviceVariationId;
    private byte questionTypeId;
    private byte questionCategoryId;
    private byte questionSubCategoryId;
    private char responseType;


    public void setServiceId(byte serviceId) {
        if (validateData(serviceId, MAX_NUMBER_OF_SERVICES)) {
            this.serviceId = serviceId;
        } else {
            throw new DataAnalyserException("Invalid service_id " + serviceId);
        }
    }

    public void setServiceVariationId(byte serviceVariationId) {
        if (validateData(serviceVariationId, MAX_NUMBER_OF_SERVICE_VARIATIONS)) {
            this.serviceVariationId = serviceVariationId;
        } else {
            throw new DataAnalyserException("Invalid service_variation_id " + serviceVariationId);
        }
    }

    public void setQuestionTypeId(byte questionTypeId) {
        if (validateData(questionTypeId, MAX_NUMBER_OF_QUESTION_TYPES)) {
            this.questionTypeId = questionTypeId;
        } else {
            throw new DataAnalyserException("Invalid question_type_id " + questionTypeId);
        }
    }

    public void setQuestionCategoryId(byte questionCategoryId) {
        if (validateData(questionCategoryId, MAX_NUMBER_OF_QUESTION_TYPE_CATEGORIES)) {
            this.questionCategoryId = questionCategoryId;
        } else {
            throw new DataAnalyserException("Invalid question_category_id " + questionCategoryId);
        }
    }

    public void setQuestionSubCategoryId(byte questionSubCategoryId) {
        if (validateData(questionSubCategoryId, MAX_NUMBER_OF_QUESTION_TYPE_CATEGORY_SUBTYPES)) {
            this.questionSubCategoryId = questionSubCategoryId;
        } else {
            throw new DataAnalyserException("Invalid question_sub-category_id " + questionSubCategoryId);
        }
    }

    public void setResponseType(char responseType) {
        if (responseType == 'P' || responseType == 'N') {
            this.responseType = responseType;
        } else {
            throw new DataAnalyserException("Invalid response type " + responseType);
        }
    }

    private boolean validateData(byte value, byte max_value) {
        return value > 0 && value <= max_value;

    }

    public byte getServiceId() {
        return serviceId;
    }

    public byte getServiceVariationId() {
        return serviceVariationId;
    }

    public byte getQuestionTypeId() {
        return questionTypeId;
    }

    public byte getQuestionCategoryId() {
        return questionCategoryId;
    }

    public byte getQuestionSubCategoryId() {
        return questionSubCategoryId;
    }

    public char getResponseType() {
        return responseType;
    }

    @Override
    public String toString() {
        return
                getServiceId() +
                        (getServiceVariationId() == 0 ? "" : "." + getServiceVariationId()) +
                        ", " + getQuestionTypeId() +
                        (getQuestionCategoryId() == 0 ? "" : "." + getQuestionCategoryId()) +
                        (getQuestionSubCategoryId() == 0 ? "" : "." + getQuestionSubCategoryId()) +
                        ", " + getResponseType();

    }

}
