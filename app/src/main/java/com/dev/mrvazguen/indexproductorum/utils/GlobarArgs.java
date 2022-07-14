package com.dev.mrvazguen.indexproductorum.utils;

/*
* @See  colecciones a nivel de raíz (Subcolecciones)<a href ="https://firebase.google.com/docs/firestore/manage-data/structure-data?hl=es-419">link</a>
* */
public class GlobarArgs {

    public enum  articuloEnum {id,nombre,descripcion,categoria,precio}
    public  enum  userEnum{};

    public   static final  String  DB_SHOPING="shoping";
    public  static  String  USER_ID="userID";//document
    public  static final String COLLECTION_SHOPING_LIST="shopingList";
    public  static  String DOCUMENT_ARTICLE="article1";

    //DB LISTA COMPRA
    public  static  final  String LISTA_COMPRA_ABSOLUT_PATH=DB_SHOPING+"/"+USER_ID+"/"+COLLECTION_SHOPING_LIST+"/"+DOCUMENT_ARTICLE;

    //DB USERS
    public  static  final  String DB_USER_COLLECTION ="usuariosCollection";
    public static String CORREO_USUARIO="random";
    public  static  final String DB_USERS_LIST = DB_USER_COLLECTION +"/"+ CORREO_USUARIO;
    public static  String NOM_USUARI_ACTUAL="DEFAULT_USER";
    //DB OFERTA
    public  static final  String  DB_DEALS="deals";
    public  static  final  String LISTA_OFERTA_ABSOLUT_PATH=DB_DEALS+"/"+DOCUMENT_ARTICLE;

}
