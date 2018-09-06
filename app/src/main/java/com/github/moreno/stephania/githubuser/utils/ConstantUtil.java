package com.github.moreno.stephania.githubuser.utils;

/**
 * Clase que contiene las variables constantes y trasnversales a la aplicación
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno</a>
 */
public class ConstantUtil {

    /** String que contiene la url del web service */
    public static String BASEURL = "https://api.github.com";

    /** String que contiene parametro para la consulta de la url */
    public static String NAME_USER = "language";

    /** String que contiene mensaje de conexión exitosa **/
    public static  String ON_CONNECTION = "Tiene el dispositivo con internet";

    /** String que contiene mensaje de conexión no exitosa **/
    public static  String OFF_CONNECTION = "EL dispositivo requiere conexión a internet";

    /** String que contiene mensaje de conexión faillida a la petición **/
    public static  String NOT_DATA = "No se logró cargar datos del servidor";
}
