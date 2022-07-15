package com.dev.mrvazguen.indexproductorum.data.repository;

public interface iFirebaseResult <E> {
    public void OnSuccess(E msg);
    public void OnFailure(String errorMSG);
}
