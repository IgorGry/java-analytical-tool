package com.qualityunit;

import java.util.function.BooleanSupplier;

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
        if (validateData(serviceId, MAX_NUMBER_OF_SERVICES).getAsBoolean()) {
            this.serviceId = serviceId;
        } else {
            throw new DataAnalyserException("Invalid service_id " + serviceId);
        }
    }

    public void setServiceVariationId(byte serviceVariationId) {
        if (validateData(serviceVariationId, MAX_NUMBER_OF_SERVICE_VARIATIONS).getAsBoolean()) {
            this.serviceVariationId = serviceVariationId;
        } else {
            throw new DataAnalyserException("Invalid service_variation_id " + serviceVariationId);
        }
    }

    public void setQuestionTypeId(byte questionTypeId) {
        if (validateData(questionTypeId, MAX_NUMBER_OF_QUESTION_TYPES).getAsBoolean()) {
            this.questionTypeId = questionTypeId;
        } else {
            throw new DataAnalyserException("Invalid question_type_id " + questionTypeId);
        }
    }

    public void setQuestionCategoryId(byte questionCategoryId) {
        if (validateData(questionCategoryId, MAX_NUMBER_OF_QUESTION_TYPE_CATEGORIES).getAsBoolean()) {
            this.questionCategoryId = questionCategoryId;
        } else {
            throw new DataAnalyserException("Invalid question_category_id " + questionCategoryId);
        }
    }

    public void setQuestionSubCategoryId(byte questionSubCategoryId) {
        if (validateData(questionSubCategoryId, MAX_NUMBER_OF_QUESTION_TYPE_CATEGORY_SUBTYPES).getAsBoolean()) {
            this.questionSubCategoryId = questionSubCategoryId;
        } else {
            throw new DataAnalyserException("Invalid question_sub-category_id " + questionSubCategoryId);
        }
    }

    public void setResponseType(char responseType) {
        if (responseType == 'P' || responseType == 'N') {
            this.responseType = responseType;
        } else {
            throw new DataAnalyserException("Invalid response type" + responseType);
        }
    }

    private BooleanSupplier validateData(byte value, byte max_value) {
        return () -> max_value > 0 || value <= max_value;

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
}
