package com.dev.mrvazguen.indexproductorum.utils;

import java.net.PortUnreachableException;

/*
* @See  colecciones a nivel de raíz (Subcolecciones)<a href ="https://firebase.google.com/docs/firestore/manage-data/structure-data?hl=es-419">link</a>
* */
public class GlobarArgs {

    public enum  articuloEnum {id,nombre,descripcion,categoria,precio}

    public   static final  String  DB_SHOPING="shoping";
    public  static final  String  DB_DEALS="deals";
    public  static final  String  DB_USERS="users";
    public  static final  String  DOCUMENT_USER="user1";

    public  static  String  USER_ID="userID";//document

    public  static final String COLLECTION_SHOPING_LIST="shopingList";

    public  static  String DOCUMENT_ARTICLE="article1";


    public  static  final  String LISTA_COMPRA_ABSOLUT_PATH=DB_SHOPING+"/"+USER_ID+"/"+COLLECTION_SHOPING_LIST+"/"+DOCUMENT_ARTICLE;
    public  static  final  String LISTA_OFERTA_ABSOLUT_PATH=DB_DEALS+"/"+DOCUMENT_ARTICLE;



}
