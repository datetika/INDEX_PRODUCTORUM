package com.dev.mrvazguen.indexproductorum.utils;

public class GlobarArgs {
    public static   final  String GLOBAL_Collection="global_collection";
    public static final String ARTICULO_COLLECTION="articulo_collection";
    public static final String ARTICULO_DOCUMENTO="articulos";
    public enum  articuloEnum {id,nombre,descripcion,precio}
    public  static  final String ARTICULO_COLLECTION_PATH= GLOBAL_Collection+"/"+ARTICULO_DOCUMENTO+"/"+ARTICULO_COLLECTION;///global_collection/articulos/articulo_collection/
}
