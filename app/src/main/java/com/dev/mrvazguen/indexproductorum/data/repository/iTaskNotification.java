package com.dev.mrvazguen.indexproductorum.data.repository;

import java.util.List;

public interface iTaskNotification<E> {
    public void OnSucces(List<E> lista);
    public void OnFail(String msg);
}
