package com.dev.mrvazguen.indexproductorum.data.repository;

import java.util.Map;

public interface persistenciaDAO {
    public void create(String nombreCollecion, Map<String,Object> dato);
    public void delete();
    public void update();
    public void read();
}
